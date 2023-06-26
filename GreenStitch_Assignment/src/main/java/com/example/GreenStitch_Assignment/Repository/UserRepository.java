package com.example.GreenStitch_Assignment.Repository;

import com.example.GreenStitch_Assignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
