package com.springBoot.OrderService.External.client.Request;


import com.springBoot.OrderService.Model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	
	private long orderId;
	private long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;
	
	
	
	
}
