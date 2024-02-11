package com.certify.certify_edu.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certify.certify_edu.modules.students.entities.CertificationsStudentEntity;
import com.certify.certify_edu.modules.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {
  
  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  public List<CertificationsStudentEntity> execute() {
    var result = this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    return result;
  }
}
