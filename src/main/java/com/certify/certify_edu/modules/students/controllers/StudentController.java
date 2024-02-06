package com.certify.certify_edu.modules.students.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certify.certify_edu.modules.students.controllers.dto.VerifyCertificationDTO;

@RestController
@RequestMapping("/students")
public class StudentController {
  
  @PostMapping("/verify")
  public String verifyIfHasCertification(@RequestBody VerifyCertificationDTO verifyCertificationDTO) {
    System.out.print(verifyCertificationDTO);
    return "Lets go!";
  }
}
