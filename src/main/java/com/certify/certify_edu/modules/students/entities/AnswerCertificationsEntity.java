package com.certify.certify_edu.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_student")
public class AnswerCertificationsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JoinColumn(name = "certification_id")
  private UUID certificationId;

  @ManyToOne()
  @JoinColumn(name = "certification_id")
  private CertificationsStudentEntity certificationsStudentEntity;

  @Column(name = "student_id")
  private UUID studentId;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private StudentEntity studentEntity;

  @Column(name = "question_id")
  private UUID questionId;

  @Column(name = "answer_id")
  private UUID answerId;

  @Column(name = "is_correct")
  private boolean isCorrect;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
