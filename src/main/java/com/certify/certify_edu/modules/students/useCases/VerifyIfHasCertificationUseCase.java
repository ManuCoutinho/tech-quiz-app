package com.certify.certify_edu.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certify.certify_edu.modules.students.dto.VerifyCertificationDTO;
import com.certify.certify_edu.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {
  
  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public boolean execute(VerifyCertificationDTO dto) {
    var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
    return !result.isEmpty();
  }
}
