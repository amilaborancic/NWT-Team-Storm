package com.example.demo.Service;

import com.example.demo.DatabaseConnection;
import com.example.demo.Models.Event;
import com.example.demo.Stubs.Events;
import com.example.demo.Stubs.Events.Request;
import com.example.demo.Stubs.Events.Response;
import com.example.demo.actionGrpc.actionImplBase;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;


@Component
public class EventService extends actionImplBase {

    private DatabaseConnection connectionConfig = new DatabaseConnection();

    @Override
    public void logAction(Request request, StreamObserver<Response> responseObserver) {

        Response.Builder response =  Response.newBuilder();
        try {
            Event event = new Event(request.getTimestamp(), request.getIdKorisnik(), request.getNazivServisa(), request.getTipAkcijeValue(),request.getNazivResursa());
            //spasimo event u bazu
            PreparedStatement preparedStatement = save(event);
            preparedStatement.executeUpdate();

            response.setResponseContent("Spasen event").setResponseType(Events.ResponseType.SUCCESS);
        }
        catch (Exception e) {
            //doslo je do greske
            response.setResponseContent("Doslo je to greske!").setResponseType(Events.ResponseType.ERROR);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        //zatvorimo konekciju
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    private PreparedStatement save(Event event) throws Exception {
        String sql = "INSERT INTO event(timestamp, naziv_servisa, id_korisnik, tip_akcije, naziv_resursa) VALUES(?,?,?,?,?)";
        ArrayList<String> connectionProps = connectionConfig.getConnectionInfo();

        try{
            Connection connection = DriverManager.getConnection(connectionProps.get(0), connectionProps.get(1), connectionProps.get(2));

            PreparedStatement noviEvent = connection.prepareStatement(sql);

            noviEvent.setString(1, event.getTimestamp());
            noviEvent.setString(2, event.getNazivServisa());
            noviEvent.setLong(3, event.getIdKorisnik());
            noviEvent.setInt(4, event.getTipAkcije());
            noviEvent.setString(5, event.getNazivResursa());

            return noviEvent;
        }
        catch (Exception e) {
            throw e;
        }
    }

}
