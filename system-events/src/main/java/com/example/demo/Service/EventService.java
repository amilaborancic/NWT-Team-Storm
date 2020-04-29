package com.example.demo.Service;

import com.example.demo.Models.Event;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Stubs.Events;
import com.example.demo.Stubs.Events.Request;
import com.example.demo.Stubs.Events.Response;
import com.example.demo.actionGrpc.actionImplBase;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EventService extends actionImplBase {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void logAction(Request request, StreamObserver<Response> responseObserver) {
        System.out.println("dosli u log action!");
        System.out.println(request.getNazivResursa());
        System.out.println(request.getNazivServisa());

        Response.Builder response =  Response.newBuilder();
        try {
            Event event = new Event(request.getTimestamp(), request.getIdKorisnik(), request.getNazivServisa(), request.getTipAkcijeValue(),request.getNazivResursa());
            //spasimo event u bazu
            eventRepository.save(event);
            response.setResponseContent("Spasen event").setResponseType(Events.ResponseType.SUCCESS);
        }
        catch (Exception e) {
            //doslo je do greske
            response.setResponseContent("Doslo je to greske!").setResponseType(Events.ResponseType.ERROR);
        }
        //zatvorimo konekciju
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
