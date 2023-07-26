package com.springBoot.PaymentService.Service;

import com.springBoot.PaymentService.Modal.PaymentRequest;
import com.springBoot.PaymentService.Modal.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(String orderId);

}
