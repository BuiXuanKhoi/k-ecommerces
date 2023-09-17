package com.ecommerce.inventoryservice.exceptions;

import io.grpc.*;


public class GlobalExceptionHandler implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        ServerCall.Listener<ReqT> listener = serverCallHandler.startCall(serverCall, metadata);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                }catch (ResourceNotFoundException exception){
                    serverCall.close(Status.NOT_FOUND.withCause(exception).withDescription(exception.getMessage()), metadata);
                } catch (ProductOutOfStockException exception) {

                }
            }
        };
    }
}
