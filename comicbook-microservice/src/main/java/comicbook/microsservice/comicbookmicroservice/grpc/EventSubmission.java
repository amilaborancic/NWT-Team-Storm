package comicbook.microsservice.comicbookmicroservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Calendar;

public class EventSubmission {

    private static String serviceName = "comicbook-service";

    public static void submitEvent(Long idKorisnik, Events.ActionType actionType, String nazivResursa){
        //event dio
        try{
            //otvorimo konekciju
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8084).usePlaintext().build();
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
