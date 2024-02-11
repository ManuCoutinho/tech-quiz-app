package com.certify.certify_edu.modules.students.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionsAnswersDTO {
  
  private UUID questionID;
  private UUID alternativeID;
  private boolean isCorrect;
}
