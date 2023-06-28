package com.example.taskmanagerdemo.repository;

import com.example.taskmanagerdemo.model.Taska;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskaRepository extends JpaRepository<Taska, Long> {
}

