package com.certify.certify_edu.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.certify.certify_edu.modules.students.entities.CertificationsStudentEntity;

public interface CertificationStudentRepository extends JpaRepository<CertificationsStudentEntity, UUID> {

  @Query("SELECT c FROM certification c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
  List<CertificationsStudentEntity> findByStudentAndTechnology(String email, String technology);
  
}
