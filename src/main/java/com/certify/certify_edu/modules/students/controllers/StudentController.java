package com.certify.certify_edu.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certify.certify_edu.modules.students.dto.StudentCertificationAnswerDTO;
import com.certify.certify_edu.modules.students.dto.VerifyCertificationDTO;
import com.certify.certify_edu.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.certify.certify_edu.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
  
  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @Autowired
  private StudentCertificationAnswersUseCase studentCertificationAnswerUseCase;

  @PostMapping("/verify")
  public String verifyIfHasCertification(@RequestBody VerifyCertificationDTO verifyCertificationDTO) {
    var result = this.verifyIfHasCertificationUseCase.execute(verifyCertificationDTO);
    if(result){
      return "Usuário habilitado";
    }
    return "Usuário já completou a certificação";
  }

  @PostMapping("/certification/answer")
  public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws Exception {
    try {
      var result = studentCertificationAnswerUseCase.execute(studentCertificationAnswerDTO);
      return ResponseEntity.ok().body(result);
    } catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    
  }
}
