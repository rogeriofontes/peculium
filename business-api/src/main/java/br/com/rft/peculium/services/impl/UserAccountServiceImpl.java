package br.com.rft.peculium.services.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.dtos.MailTO;
import br.com.rft.peculium.dtos.UserRecoveryTO;
import br.com.rft.peculium.models.Roles;
import br.com.rft.peculium.models.User;
import br.com.rft.peculium.models.UserRole;
import br.com.rft.peculium.repositories.UserRepository;
import br.com.rft.peculium.repositories.UserRoleRepository;
import br.com.rft.peculium.services.UserAccountService;
import br.com.rft.peculium.util.PasswordEncoderGenerator;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public String registerUser(User user) {
        String successMessage;
        user.setPassword(PasswordEncoderGenerator.generator(user.getPassword()));
        User resultUser = userRepository.findByEmail(user.getEmail());
        if (resultUser == null) {
            if (userRepository.save(user) != null) {
                UserRole userRole = new UserRole();
                userRole.setRoleName(Roles.USER.getRole());
                userRole.setUser(userRepository.findByEmail(user.getEmail()));
                userRoleRepository.save(userRole);
                successMessage = "success";
            } else {
                successMessage = "unsuccess";
            }
        } else {
            successMessage = "já registrado";
        }

        return successMessage;
    }

    @Override
    public String recoverPassword(UserRecoveryTO userRecoveryTO) {
        String successMessage;
        if (userRecoveryTO != null) {
        	
        	String newPassowrd = newPassword();
        	
        	MailTO mailTO = getNewPassword(userRecoveryTO, newPassowrd);
        	logger.info("Something went wrong: %s ", !StringUtils.isEmpty(mailTO.toString()));
        	
            User result = userRepository.findByEmail(userRecoveryTO.getEmail());
            if (result != null) {
                result.setPassword(PasswordEncoderGenerator.generator(newPassowrd));
                userRepository.save(result);
              
                successMessage = "success";
            } else {
                successMessage = "not finded";
            }
        } else {
            successMessage = "unsuccess";
        }

        return successMessage;
    }

	private String newPassword() {
		return UUID.randomUUID().toString();
	}

	private MailTO getNewPassword(UserRecoveryTO userRecoveryTO, String newPassowrd) {
		MailTO mailTO = new MailTO();
		mailTO.setTemplateName("passwordrecovery.vm");
		mailTO.setFrom(userRecoveryTO.getEmail());
		mailTO.setTo(userRecoveryTO.getEmail());
		mailTO.setReplyTo(userRecoveryTO.getEmail());
		mailTO.setActiveCode(newPassowrd);
		mailTO.setSubject("Recuperacao d Senha");
		return mailTO;
	}

	@Override
	public User find(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Object edit(User user) {
		User result = find(user.getId());
		result.update(user);
		return userRepository.save(user);
	}

}
