package catalogue.microsservice.cataloguemicroservice.grpc;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class EventSubmission {

    private String serviceName = "catalogue-service";

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    public Events.ActionType action(String actionType){
        switch (actionType){
            case "GET":
                return Events.ActionType.GET;
            case "POST":
                return Events.ActionType.CREATE;
            case "PUT":
                return Events.ActionType.UPDATE;
            case "DELETE":
                return Events.ActionType.DELETE;
            default:
                return null;
        }
    }

    public void submitEvent(Long idKorisnik, Events.ActionType actionType, String nazivResursa){
        //event dio
        try{
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-events", false);
            //otvorimo konekciju
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(),instanceInfo.getPort()).usePlaintext().build();
            //napravimo stub
            actionGrpc.actionBlockingStub stub =  actionGrpc.newBlockingStub(channel);
            //trenutno vrijeme
            Calendar cal = Calendar.getInstance();
            String timestamp = cal.getTime().toString();

            //formiramo response
            Events.Response response = stub.logAction(Events.Request.newBuilder()
                    .setTimestamp(timestamp)
                    .setIdKorisnik(idKorisnik)
                    .setNazivResursa(nazivResursa)
                    .setNazivServisa(serviceName)
                    .setTipAkcije(actionType)
                    .build()
            );
            System.out.println(response.getResponseType());
            System.out.println(response.getResponseContent());

            channel.shutdown();
        }
        catch(Exception e){
            System.out.println("Doslo je do greske u grpc komunikaciji!");
        }
    }

}
