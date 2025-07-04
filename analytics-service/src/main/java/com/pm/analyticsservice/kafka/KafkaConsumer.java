package com.pm.analyticsservice.kafka;

import analytics.events.PatientEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
	@KafkaListener(topics = "patient", groupId = "analytics-service")
	public void consumeEvent(byte[] event) {
		try {
			PatientEvent patientEvent = PatientEvent.parseFrom(event);
			// Perform any business related analytics here
			log.info("Received PatientEvent: [patientId={}, fullName={}, email={}, eventType={}]",
				patientEvent.getPatientId(), patientEvent.getFullName(), patientEvent.getEmail(), patientEvent.getEventType());
		} catch (InvalidProtocolBufferException e) {
			log.error("Error parsing PatientEvent: {}", e.getMessage());
		}
	}
}
