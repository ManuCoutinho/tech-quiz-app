package com.certify.certify_edu.modules.students.entities;

import java.util.UUID;

public class AnswerCertificationsEntity {
  private UUID id;
  private UUID certificationId;
  private UUID studentId;
  private UUID questionId;
  private UUID answerId;
  private boolean isCorrect;
}
