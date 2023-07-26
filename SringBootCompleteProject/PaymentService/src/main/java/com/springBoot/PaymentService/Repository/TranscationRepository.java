package com.springBoot.PaymentService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.PaymentService.Entity.TranscationDetails;

@Repository
public interface TranscationRepository  extends JpaRepository<TranscationDetails, Long>{

	
	TranscationDetails findByOrderId(long orderId);
}
