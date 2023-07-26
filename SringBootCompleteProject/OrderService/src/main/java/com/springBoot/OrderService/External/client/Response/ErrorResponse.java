package com.springBoot.OrderService.External.client.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private String errorMessage;
	private String errorCode;
}
