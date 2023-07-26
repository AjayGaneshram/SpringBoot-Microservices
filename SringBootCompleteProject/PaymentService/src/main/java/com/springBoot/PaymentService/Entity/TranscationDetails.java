package com.springBoot.PaymentService.Entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRANSCATION_DETAILS")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TranscationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="ORDER_ID")
	private long orderId;
	@Column(name="PAYMENT_MODE")
	private String paymentMode;
	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;
	@Column(name="PAYMENT_DATE")
	private Instant paymentDate;
	@Column(name="PAYMENT_STATUS")
	private String paymentStatus;
	@Column(name="AMOUNT")
	private long amount;
	
	
	
}
