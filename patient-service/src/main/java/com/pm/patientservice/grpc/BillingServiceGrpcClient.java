package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.grpc.ManagedChannelBuilder;

@Service
@Log4j2
public class BillingServiceGrpcClient {
	private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

	// localhost:9001/BillingService/CreateBillingAccount
	public BillingServiceGrpcClient(
		@Value("${billing.service.address:billing-service}") String billingServiceAddress,
		@Value("${billing.service.port:9001}") int billingServicePort
	) {
		log.info("Creating BillingServiceGrpcClient at {}:{}", billingServiceAddress, billingServicePort);
		billingServiceBlockingStub =
			BillingServiceGrpc.newBlockingStub(
				ManagedChannelBuilder.forAddress(
					billingServiceAddress, billingServicePort
				).usePlaintext().build());
	}

	public BillingResponse createBillingAccount(String patientId, String fullName, String email) {
		BillingRequest billingRequest = BillingRequest.newBuilder()
			.setPatientId(patientId)
			.setFullName(fullName)
			.setEmail(email)
			.build();

		BillingResponse billingResponse = billingServiceBlockingStub.createBillingAccount(billingRequest);

		log.info("Billing account created via gRPC: {}", billingResponse);

		return billingResponse;
	}
}
