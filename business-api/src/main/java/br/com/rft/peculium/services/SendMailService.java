package br.com.rft.peculium.services;

import org.apache.commons.mail.EmailException;

@FunctionalInterface
public interface SendMailService {
	public void enviarEmail(String messagem, String name, String email) throws EmailException;
}
