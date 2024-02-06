package com.certify.certify_edu.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.certify.certify_edu.modules.students.controllers.dto.VerifyCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {
  
  public boolean execute(VerifyCertificationDTO dto) {
    if(dto.getEmail().equals("email@email.com") && dto.getTechnology().equals("JAVA")) {
      return true;
    }
    return false;
  }
}
