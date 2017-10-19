package com.avenuecode.test.avenuecodetest.repository;

import com.avenuecode.test.avenuecodetest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(String username);

}
