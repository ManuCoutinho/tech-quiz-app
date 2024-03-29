package com.certify.certify_edu.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certify.certify_edu.modules.certifications.useCases.Top10RankingUseCase;
import com.certify.certify_edu.modules.students.entities.CertificationsStudentEntity;

@RestController
@RequestMapping
public class RankingController {
  
  @Autowired
  private Top10RankingUseCase top10RankingUseCase;

  @GetMapping("/top10")
  public List<CertificationsStudentEntity> top10() {
    return this.top10RankingUseCase.execute();
  }
}
