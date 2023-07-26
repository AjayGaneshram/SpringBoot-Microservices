package com.springBoot.PaymentService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.springBoot.PaymentService.Modal.PaymentRequest;
import com.springBoot.PaymentService.Modal.PaymentResponse;
import com.springBoot.PaymentService.Service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private  PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Long> doPayment( @RequestBody PaymentRequest paymentRequest){
		return new ResponseEntity<>(paymentService.doPayment(paymentRequest),HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String orderId){
		return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId),HttpStatus.OK);
	}
}
