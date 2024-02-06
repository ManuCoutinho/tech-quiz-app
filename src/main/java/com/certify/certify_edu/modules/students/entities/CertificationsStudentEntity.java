package com.certify.certify_edu.modules.students.entities;

import java.util.List;
import java.util.UUID;

public class CertificationsStudentEntity {
  private UUID id;
  private UUID student;
  private String technology;
  private int grate;
  List<AnswerCertificationsEntity> answerCertificationsEntities;
}
