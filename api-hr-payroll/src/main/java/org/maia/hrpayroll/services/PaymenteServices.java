package org.maia.hrpayroll.services;

import java.io.Serializable;

import org.maia.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymenteServices extends Serializable{

	Payment getPayment(Long workerId, int days);
}
