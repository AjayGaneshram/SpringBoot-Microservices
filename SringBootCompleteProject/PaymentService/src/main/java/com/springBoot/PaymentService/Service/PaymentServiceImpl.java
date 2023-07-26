package com.springBoot.PaymentService.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.PaymentService.Entity.TranscationDetails;
import com.springBoot.PaymentService.Modal.PaymentMode;
import com.springBoot.PaymentService.Modal.PaymentRequest;
import com.springBoot.PaymentService.Modal.PaymentResponse;
import com.springBoot.PaymentService.Repository.TranscationRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl  implements PaymentService{

	@Autowired
	private TranscationRepository transcationRepository;
	
	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording Payment Details : {}",paymentRequest);
		TranscationDetails transcationDetails
		=TranscationDetails.builder()
		.paymentDate(Instant.now())
		.paymentMode(paymentRequest.getPaymentMode().name())
		.paymentStatus("SUCCESS").referenceNumber(paymentRequest.getReferenceNumber())
		.orderId(paymentRequest.getOrderId()).amount(paymentRequest.getAmount()).build();
		
		transcationRepository.save(transcationDetails);
		log.info("Transcation completed with ID :{}",transcationDetails.getId());
		return transcationDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
		// TODO Auto-generated method stub
		log .info("Getting Payment Details from order ID : "+orderId);
		TranscationDetails transactionDetails
         = transcationRepository.findByOrderId(Long.valueOf(orderId));
       
 PaymentResponse paymentResponse
         = PaymentResponse.builder()
         .paymentId(transactionDetails.getId())
         .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
         .paymentDate(transactionDetails.getPaymentDate())
         .orderId(transactionDetails.getOrderId())
         .status(transactionDetails.getPaymentStatus())
         .amount(transactionDetails.getAmount())
         .build();
		return paymentResponse;
	}

}
