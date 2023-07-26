package com.springBoot.OrderService.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springBoot.OrderService.Entity.Order;
import com.springBoot.OrderService.Exception.CustomException;

import com.springBoot.OrderService.External.client.PaymentService;
import com.springBoot.OrderService.External.client.ProductService;
import com.springBoot.OrderService.External.client.Request.PaymentRequest;
import com.springBoot.OrderService.Model.OrderRequest;
import com.springBoot.OrderService.Model.OrderResponse;
import com.springBoot.OrderService.Repository.OrderRepository;
import com.springBoot.PaymentService.Modal.PaymentResponse;
import com.springBoot.ProductService.Modal.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		log.info("Placing Order Request: {}", orderRequest);
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

		log.info("Creating order with status CREATED");
		Order order = Order.builder().amount(orderRequest.getTotalAmount()).orderStatus("CREATED")
				.productId(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity())
				.build();
		order = orderRepository.save(order);

		log.info("Calling payment service to complete the payment ");

		PaymentRequest paymentRequest = PaymentRequest.builder().orderId(order.getId())
				.amount(orderRequest.getTotalAmount()).paymentMode(orderRequest.getPaymentMode()).build();
		String orderStatus = null;
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment done successfully .changing the order status");
			orderStatus = "PLACED";
		} catch (Exception e) {
			log.info("Error occured in payment .Changing order status");
			orderStatus = "PAYMENT_FAILED";
		}

		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		log.info("Order Places successfully with order Id :{}", order.getId());
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get Order details for order Id : {}", orderId);
		Order order = orderRepository.findById(orderId).orElseThrow(
				() -> new CustomException("Order not found for the orderId : " + orderId, "NOT_FOUND", 404));

		log.info("Invoking Product service to fetch for the orderId " + orderId);

		ProductResponse productResponse = restTemplate
				.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);
		log.info("Getting Payment information for the orderId " + orderId);

		PaymentResponse paymentResponse= restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(), PaymentResponse.class);
		
		OrderResponse.PaymentDetails paymentDetails=OrderResponse.PaymentDetails.builder()
				.paymentStatus(paymentResponse.getStatus())
				.paymentmode(paymentResponse.getPaymentMode())
				.paymentDate(paymentResponse.getPaymentDate())
				.build();
		
		OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
				.productName(productResponse.getProductName()).productId(productResponse.getProductId()).build();

		OrderResponse orderResponse = OrderResponse.builder().orderId(order.getId()).orderStatus(order.getOrderStatus())
				.amount(order.getAmount()).orderDate(order.getOrderDate()).productDetails(productDetails).paymentDetails(paymentDetails).build();
		return orderResponse;
	}

}
