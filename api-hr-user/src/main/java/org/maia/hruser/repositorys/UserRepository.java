package org.maia.hruser.repositorys;

import org.maia.hruser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
