package catalogue.microsservice.cataloguemicroservice.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: events.proto")
public final class actionGrpc {

  private actionGrpc() {}

  public static final String SERVICE_NAME = "action";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Events.Request,
      Events.Response> getLogActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logAction",
      requestType = Events.Request.class,
      responseType = Events.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Events.Request,
      Events.Response> getLogActionMethod() {
    io.grpc.MethodDescriptor<Events.Request, Events.Response> getLogActionMethod;
    if ((getLogActionMethod = actionGrpc.getLogActionMethod) == null) {
      synchronized (actionGrpc.class) {
        if ((getLogActionMethod = actionGrpc.getLogActionMethod) == null) {
          actionGrpc.getLogActionMethod = getLogActionMethod =
              io.grpc.MethodDescriptor.<Events.Request, Events.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "logAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Events.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Events.Response.getDefaultInstance()))
              .setSchemaDescriptor(new actionMethodDescriptorSupplier("logAction"))
              .build();
        }
      }
    }
    return getLogActionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static actionStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<actionStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<actionStub>() {
        @Override
        public actionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new actionStub(channel, callOptions);
        }
      };
    return actionStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static actionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<actionBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<actionBlockingStub>() {
        @Override
        public actionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new actionBlockingStub(channel, callOptions);
        }
      };
    return actionBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static actionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<actionFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<actionFutureStub>() {
        @Override
        public actionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new actionFutureStub(channel, callOptions);
        }
      };
    return actionFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class actionImplBase implements io.grpc.BindableService {

    /**
     */
    public void logAction(Events.Request request,
        io.grpc.stub.StreamObserver<Events.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getLogActionMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogActionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Events.Request,
                Events.Response>(
                  this, METHODID_LOG_ACTION)))
          .build();
    }
  }

  /**
   */
  public static final class actionStub extends io.grpc.stub.AbstractAsyncStub<actionStub> {
    private actionStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected actionStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new actionStub(channel, callOptions);
    }

    /**
     */
    public void logAction(Events.Request request,
        io.grpc.stub.StreamObserver<Events.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogActionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class actionBlockingStub extends io.grpc.stub.AbstractBlockingStub<actionBlockingStub> {
    private actionBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected actionBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new actionBlockingStub(channel, callOptions);
    }

    /**
     */
    public Events.Response logAction(Events.Request request) {
      return blockingUnaryCall(
          getChannel(), getLogActionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class actionFutureStub extends io.grpc.stub.AbstractFutureStub<actionFutureStub> {
    private actionFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected actionFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new actionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Events.Response> logAction(
        Events.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getLogActionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG_ACTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final actionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(actionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG_ACTION:
          serviceImpl.logAction((Events.Request) request,
              (io.grpc.stub.StreamObserver<Events.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class actionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    actionBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Events.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("action");
    }
  }

  private static final class actionFileDescriptorSupplier
      extends actionBaseDescriptorSupplier {
    actionFileDescriptorSupplier() {}
  }

  private static final class actionMethodDescriptorSupplier
      extends actionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    actionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (actionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new actionFileDescriptorSupplier())
              .addMethod(getLogActionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
