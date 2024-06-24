package com.banking.banking.Repository;

import com.banking.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    long deleteByEmail(String email);
    Optional<User>findByEmail(String email);
}
