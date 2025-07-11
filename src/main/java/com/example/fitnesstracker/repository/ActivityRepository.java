package com.example.fitnesstracker.repository;

import com.example.fitnesstracker.model.Activity;
import com.example.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Collection<Activity> findByUser(User user);
}
