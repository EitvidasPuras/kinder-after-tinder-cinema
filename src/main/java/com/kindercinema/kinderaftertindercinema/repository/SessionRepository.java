package com.kindercinema.kinderaftertindercinema.repository;

import com.kindercinema.kinderaftertindercinema.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
}