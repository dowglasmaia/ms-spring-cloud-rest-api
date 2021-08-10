package org.maia.hrpayroll.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.maia.hrpayroll.cloud.resources.WokerFeignClient;
import org.maia.hrpayroll.entities.Payment;
import org.maia.hrpayroll.entities.Worker;
import org.maia.hrpayroll.services.PaymenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class PaymentServicesImpl implements PaymenteServices{
	private static final long serialVersionUID = 1L;
	
	/*@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;
	 */
	
	
	@Autowired
	private WokerFeignClient wokerFeignClient; 
	
	@Override
	public Payment getPayment(Long workerId, int days) {
		
		Worker worker = wokerFeignClient.findById(workerId).getBody(); 			
		return Payment.builder()
				.name(worker.getName())
				.dailyIncome(worker.getDailyIncome())
				.days(days)
				.build();
	}
	
	/*	
	@Override
	public Payment getPayment(Long workerId, int days) {
	
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", String.valueOf(workerId));
		
		Worker worker = restTemplate.getForObject(
				workerHost + "/workers/{id}", //url 
				Worker.class, 				 //tipo
				uriVariables); 				// parametros
		
		
		
		return Payment.builder()
				.name(worker.getName())
				.dailyIncome(worker.getDailyIncome())
				.days(days)
				.build();
	}*/
}
