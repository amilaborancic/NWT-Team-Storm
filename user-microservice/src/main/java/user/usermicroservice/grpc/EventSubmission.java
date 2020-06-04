package user.usermicroservice.grpc;

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

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;
    public void addEvent(Long idKorisnik, Events.ActionType tipAkcije, String nazivResursa) {

        try {
            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("system-events", false);
            ManagedChannel channel= ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort()).usePlaintext().build();

            actionGrpc.actionBlockingStub stub=actionGrpc.newBlockingStub(channel);
            Calendar c=Calendar.getInstance();
            String ts=c.getTime().toString();

            Events.Response response=stub.logAction(Events.Request.newBuilder()
                    .setTimestamp(ts)
                    .setNazivServisa("user-service")
                    .setIdKorisnik(idKorisnik)
                    .setTipAkcije(tipAkcije)
                    .setNazivResursa(nazivResursa)
                    .build()
            );
            System.out.println(response.getResponseTypeValue());
            System.out.println(response.getResponseContent());
            channel.shutdown();
        }
        catch(Exception e) {
            System.out.println("Greska u grpc komunikaciji!");
        }

    }
}