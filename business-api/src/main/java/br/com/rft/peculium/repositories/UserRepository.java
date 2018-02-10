package br.com.rft.peculium.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rft.peculium.models.User;

@Repository
@Qualifier(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
}