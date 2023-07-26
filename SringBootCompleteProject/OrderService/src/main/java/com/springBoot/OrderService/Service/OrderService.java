package com.springBoot.OrderService.Service;

import com.springBoot.OrderService.Model.OrderRequest;
import com.springBoot.OrderService.Model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

}
