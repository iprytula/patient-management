package com.pm.billingservice.grpc;

import billing.*;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

	@Override
	public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {

		log.info("Received request to create billing account for patient: {}", request.getPatientId());

		// TODO: implement logic to create a billing account

		BillingResponse response = BillingResponse.newBuilder().setAccountId("123456").setStatus("CREATED").build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
