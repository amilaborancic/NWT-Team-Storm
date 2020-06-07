package com.example.demo.Service;

import com.example.demo.GRPC.Events;
import com.example.demo.GRPC.Events.Request;
import com.example.demo.GRPC.Events.Response;
import com.example.demo.GRPC.actionGrpc.actionImplBase;
import com.example.demo.Models.Event;
import com.example.demo.Repository.GrpcRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@GRpcService
public class EventService extends actionImplBase {

    @Autowired
    GrpcRepository grpcRepository;

    @Override
    public void logAction(Request request, StreamObserver<Response> responseObserver) {

        Response.Builder response =  Response.newBuilder();
        try {
            Event event = new Event(request.getTimestamp(), request.getIdKorisnik(), request.getNazivServisa(), request.getTipAkcijeValue(),request.getNazivResursa());
            //spasimo event u bazu
            grpcRepository.save(event);
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

}
