package br.com.rft.peculium.services.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.dtos.UserRecoveryTO;
import br.com.rft.peculium.models.Role;
import br.com.rft.peculium.models.User;
import br.com.rft.peculium.models.UserRole;
import br.com.rft.peculium.repositories.UserRoleRepository;
import br.com.rft.peculium.services.UserAccountService;
import br.com.rft.peculium.services.UserService;
import br.com.rft.peculium.util.PasswordCrypto;
import br.com.rft.peculium.web.to.UserTO;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordCrypto passwordCrypto;

	// @Autowired
	// private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	public JavaMailSender mailSender;

	// @Autowired
	// private VelocityEngine velocityEngine;

	@Override
	public boolean register(UserTO userTO) {
		boolean success;

		User resultUser = userService.findByUsername(userTO.getUsername());
		
		if (resultUser == null) {
			User user = new User();
			
			user.setUsername(userTO.getUsername());
			user.setPassword(passwordCrypto.encrypt(userTO.getPassword()));
			
			if (userService.save(user) != null) {
				UserRole userRole = new UserRole();
				userRole.setRoleName(Role.USER.getRole());
				userRole.setUser(userService.findByUsername(user.getUsername()));
				userRoleRepository.save(userRole);

				success = Boolean.TRUE;
				logger.info("user saved" + user.toString());
			} else {
				success = Boolean.FALSE;
			}
		} else {
			success = Boolean.FALSE;
		}

		return success;
	}

	@Override
	public UserRecoveryTO recoverPassword(String email) {
		return null;
	}

	@Override
	public String validatePasswordResetToken(User user, String token) {
		return null;
	}

	@Override
	public String changeUserPassword(User user, String token) {
		return null;
	}

	/*
	 * public String validatePasswordResetToken(User user, String token) {
	 * PasswordResetToken passToken =
	 * passwordResetTokenRepository.findByToken(token); if ((passToken == null)
	 * || (passToken.getUser().equals(user))) { return "invalidToken"; }
	 * 
	 * Calendar cal = Calendar.getInstance(); if
	 * ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 * return "expired"; }
	 * 
	 * Authentication auth = new
	 * UsernamePasswordAuthenticationToken(passToken.getUser(), null,
	 * Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
	 * SecurityContextHolder.getContext().setAuthentication(auth); return null;
	 * }
	 */

	/*
	 * @Override public UserRecoveryTO recoverPassword(String username) {
	 * UserRecoveryTO userRecovery = null; if (!StringUtils.isEmpty(username)) {
	 * 
	 * User user = userService.findByUsername(username); if (user != null) {
	 * userRecovery = createResetTokenForUser(user); } else { throw new
	 * PasswordException("Não foi encontrado o usuário para o email  " +
	 * username + " !"); } } else { throw new PasswordException(
	 * "Para alterar Password é necessário o email!"); }
	 * 
	 * return userRecovery; }
	 */

	/*
	 * @Override public String changeUserPassword(User user, String token) {
	 * 
	 * UserRecoveryTO userRecoveryTO = new UserRecoveryTO(user, token);
	 * 
	 * String newPassowrd = PasswordGenerator.generatePassword();
	 * user.setPassword(passwordCrypto.encrypt(newPassowrd)); User result =
	 * userService.save(user);
	 * 
	 * if (result != null) { sendNewPasswordToUser(userRecoveryTO, newPassowrd);
	 * logger.info("Password changed: " + user.toString() + "New Password " +
	 * newPassowrd); }
	 * 
	 * return newPassowrd; }
	 */

	/*
	 * private UserRecoveryTO createResetTokenForUser(User user) {
	 * 
	 * String token = UUID.randomUUID().toString(); PasswordResetToken myToken =
	 * new PasswordResetToken(); myToken.setExpiryDate(new Date());
	 * myToken.setToken(token); myToken.setUser(user);
	 * 
	 * PasswordResetToken result = passwordResetTokenRepository.save(myToken);
	 * 
	 * UserRecoveryTO userRecoveryTO = new UserRecoveryTO(user, token); if
	 * (result != null) { sendNewTokenToUser(userRecoveryTO); }
	 * 
	 * return userRecoveryTO; }
	 */

	/*
	 * private void sendNewTokenToUser(UserRecoveryTO userRecoveryTO) { MailTO
	 * mailTO = new MailTO(); mailTO.setTemplateName("passwordrecovery.vm");
	 * mailTO.setFrom(userRecoveryTO.getEmail());
	 * mailTO.setTo(userRecoveryTO.getEmail());
	 * mailTO.setReplyTo(userRecoveryTO.getEmail());
	 * mailTO.setActiveCode(userRecoveryTO.getToken()); mailTO.setSubject(
	 * "Recuperacao d Senha"); String url = "/change-password?id=" +
	 * userRecoveryTO.getId() + "&token=" + userRecoveryTO.getToken();
	 * mailTO.setText("Password Resetado, para alterar acesse: " + url);
	 * 
	 * sendEmail(mailTO); }
	 */

	/*
	 * private void sendNewPasswordToUser(UserRecoveryTO userRecoveryTO, String
	 * newPassowrd) { MailTO mailTO = new MailTO();
	 * mailTO.setTemplateName("passwordrecovery.vm");
	 * mailTO.setFrom(userRecoveryTO.getEmail());
	 * mailTO.setTo(userRecoveryTO.getEmail());
	 * mailTO.setReplyTo(userRecoveryTO.getEmail());
	 * mailTO.setActiveCode(newPassowrd); mailTO.setSubject(
	 * "Recuperacao d Senha"); mailTO.setText("Password Resetado: " +
	 * newPassowrd);
	 * 
	 * sendEmail(mailTO); }
	 */

	/*
	 * private void sendEmail(MailTO mailTO) { MimeMessage message =
	 * mailSender.createMimeMessage();
	 * 
	 * MimeMessageHelper helper; try { message.setSubject(
	 * "[\"Academia\"] - Ativação de cadastro."); helper = new
	 * MimeMessageHelper(message, true); helper.setFrom(mailTO.getFrom());
	 * helper.setTo(mailTO.getTo());
	 * 
	 * StringWriter stringWriter = getMailTemplate(mailTO);
	 * helper.setText(stringWriter.toString(), true); } catch
	 * (MessagingException e) { logger.info("Email não enviado!" +
	 * e.getMessage()); throw new EmailException("Email não enviado!"); }
	 * 
	 * mailSender.send(message); }
	 * 
	 * private StringWriter getMailTemplate(MailTO mailTO) { Template template =
	 * velocityEngine.getTemplate("./templates/email/" +
	 * mailTO.getTemplateName());
	 * 
	 * VelocityContext velocityContext = new VelocityContext();
	 * velocityContext.put("name", mailTO.getToName());
	 * velocityContext.put("email", mailTO.getTo());
	 * velocityContext.put("activeCode", mailTO.getActiveCode());
	 * velocityContext.put("company_name", "mototaxi"); StringWriter
	 * stringWriter = new StringWriter();
	 * 
	 * template.merge(velocityContext, stringWriter); return stringWriter; }
	 */
}
