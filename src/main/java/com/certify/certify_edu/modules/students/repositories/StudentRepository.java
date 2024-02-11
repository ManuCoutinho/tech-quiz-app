package com.certify.certify_edu.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certify.certify_edu.modules.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
  public Optional<StudentEntity> findByEmail(String email);
}
