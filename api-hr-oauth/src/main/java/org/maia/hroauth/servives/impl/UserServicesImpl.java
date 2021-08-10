package org.maia.hroauth.servives.impl;

import org.maia.hroauth.domain.User;
import org.maia.hroauth.feignclients.UserFeignClient;
import org.maia.hroauth.servives.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices, UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

	@Autowired
	public UserFeignClient userFeignClient;

	/* Busca Usuario por email - com comunicação entre micro services. */
	@Override
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			logger.error("e-mail não existe na base de dados");
			throw new IllegalArgumentException("e-mail não existe na base de dados");
		}
		logger.info("e-mail encontrado: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// return findByEmail(username);

		User user = userFeignClient.findByEmail(username).getBody();
		if (user == null) {
			logger.error("e-mail não existe na base de dados");
			throw new UsernameNotFoundException("e-mail não existe na base de dados");
		}
		logger.info("e-mail encontrado: " + username);
		return user;

	}

}
