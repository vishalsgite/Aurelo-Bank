package com.AureloBank.Aurelo_Bank_Service.repository;

import com.AureloBank.Aurelo_Bank_Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
}
