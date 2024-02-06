package com.certify.certify_edu.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certify.certify_edu.modules.students.controllers.dto.VerifyCertificationDTO;
import com.certify.certify_edu.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
  
  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @PostMapping("/verify")
  public String verifyIfHasCertification(@RequestBody VerifyCertificationDTO verifyCertificationDTO) {
    var result = this.verifyIfHasCertificationUseCase.execute(verifyCertificationDTO);
    if(result){
      return "Usuário habilitado";
    }
    return "Usuário já completou a certificação";
    
  }
}
