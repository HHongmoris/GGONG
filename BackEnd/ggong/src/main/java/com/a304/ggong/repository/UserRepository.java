package com.a304.ggong.repository;

import com.a304.ggong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
    Optional<User> findByGender(String gender);
    Optional<User> findByAgeRange(String age_range);
}
