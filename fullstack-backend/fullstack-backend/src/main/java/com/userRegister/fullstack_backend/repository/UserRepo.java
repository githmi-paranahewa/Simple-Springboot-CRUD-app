package com.userRegister.fullstack_backend.repository;

import com.userRegister.fullstack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
