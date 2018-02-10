package br.com.rft.peculium.services.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.services.SendMailService;

@Service
public class SendMailServiceImpl implements SendMailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void enviarEmail(String password, String name, String email) throws EmailException {
		try {
			Email semail = new SimpleEmail();
			semail.setHostName("smtp.googlemail.com");
			semail.setSmtpPort(465);
			semail.setAuthenticator(new DefaultAuthenticator("fontestz@gmail.com", "R0g6199&#"));
			semail.setSSLOnConnect(true);

			semail.setFrom("fontestz@gmail.com");
			semail.setSubject("Você foi convidado pelo ListaVIP");
			semail.setMsg("Olá " + name + ". Você acaba de ser receber sua nova senha.\n" + password);
			semail.addTo(email);
			semail.send();

		} catch (EmailException e) {
			logger.error("Erro ao enviar email: " + e.getMessage());
			throw new EmailException(e);
		}

	}

}
