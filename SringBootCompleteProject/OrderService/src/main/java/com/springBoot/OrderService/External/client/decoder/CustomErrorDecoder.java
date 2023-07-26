package com.springBoot.OrderService.External.client.decoder;

import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot.OrderService.Exception.CustomException;
import com.springBoot.OrderService.External.client.Response.ErrorResponse;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		ObjectMapper objectmapper = new ObjectMapper();
		log.info("::{}", response.request().url());
		log.info("::{}", response.request().headers());

		try {
			ErrorResponse errorResponse = objectmapper.readValue(response.body().asInputStream(), ErrorResponse.class);

			return new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(),
					response.status());
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			return new CustomException("Internal server error", "INTERNAL_SERVER_ERROR", 500);

		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			return new CustomException("Internal server error", "INTERNAL_SERVER_ERROR", 500);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new CustomException("Internal server error", "INTERNAL_SERVER_ERROR", 500);
		}

	}

}
