package com.hrgonzaga.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.hrgonzaga.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
