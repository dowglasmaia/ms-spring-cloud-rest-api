package org.maia.hroauth.servives;

import org.maia.hroauth.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
	
	
	User findByEmail(String email);

}
