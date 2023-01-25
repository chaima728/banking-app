package com.workshop.bankingapp.Repositories;

import com.workshop.bankingapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
