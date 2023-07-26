package com.springBoot.ProductService.Modal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private String errorMessage;
	private String errorCode;
}
