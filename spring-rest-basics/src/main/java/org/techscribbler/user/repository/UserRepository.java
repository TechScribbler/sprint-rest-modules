package org.techscribbler.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techscribbler.user.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
