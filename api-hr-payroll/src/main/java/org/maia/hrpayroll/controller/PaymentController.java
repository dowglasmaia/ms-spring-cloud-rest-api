package org.maia.hrpayroll.controller;

import org.maia.hrpayroll.entities.Payment;
import org.maia.hrpayroll.services.PaymenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
	
	@Autowired
	private PaymenteServices services;
	
	
	@HystrixCommand(fallbackMethod = "getPaymentByWorkerIdAndDaysAlternative") //chama metodo alternavo caso ocorra error por indisponibilidade, no metodo principal
	@GetMapping("/{workerId}/days/{days}")
	public ResponseEntity<Payment>getPaymentByWorkerIdAndDays(@PathVariable Long workerId, @PathVariable Integer days ){
		Payment payment = services.getPayment(workerId, days);
		
		return ResponseEntity.ok().body(payment);
	}

	
	
	
	
	
	
	
	/*############ METODOS ALTERNATIVOS ################*/
	public ResponseEntity<Payment>getPaymentByWorkerIdAndDaysAlternative(Long workerId,  Integer days ){
		/*FAZER A LOGICA DE ACORDO COM A NECESSIDADE*/
		Payment payment = Payment.builder().name("Maia Alt").dailyIncome(100.0).days(10).build();
		
		return ResponseEntity.ok().body(payment);
		}
	
}
