package com.pm.billingservice.grpc;

import billing.*;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import com.pm.billingservice.model.BillingAccount;
import com.pm.billingservice.repository.BillingRepository;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	private final BillingRepository billingRepository;

	BillingGrpcService(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}

	@Override
	public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {

		log.info("Received request to create billing account for patient: {}", request.getPatientId());

		BillingAccount billingAccount = BillingAccount.builder()
			.patientId(UUID.fromString(request.getPatientId()))
			.status("CREATED")
			.build();

		billingRepository.save(billingAccount);

		BillingResponse response = BillingResponse.newBuilder()
			.setAccountId(billingAccount.getAccountId().toString())
			.setStatus(billingAccount.getStatus())
			.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
