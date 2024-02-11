package com.certify.certify_edu.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.certify.certify_edu.modules.questions.entities.QuestionEntity;
import com.certify.certify_edu.modules.questions.repositories.QuestionRepository;
import com.certify.certify_edu.modules.students.dto.StudentCertificationAnswerDTO;
import com.certify.certify_edu.modules.students.dto.VerifyCertificationDTO;
import com.certify.certify_edu.modules.students.entities.AnswerCertificationsEntity;
import com.certify.certify_edu.modules.students.entities.CertificationsStudentEntity;
import com.certify.certify_edu.modules.students.entities.StudentEntity;
import com.certify.certify_edu.modules.students.repositories.CertificationStudentRepository;
import com.certify.certify_edu.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  public CertificationsStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

    var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyCertificationDTO(dto.getEmail(), dto.getTechnology()));

    if(hasCertification) {
      throw new Exception("Certification has already been completed");
    }

    // get alternatives
    
    List<QuestionEntity> questionEntity = questionRepository.findByTechnology(dto.getTechnology());
    List<AnswerCertificationsEntity> answerCertifications = new ArrayList<>();
    AtomicInteger correctAnswers = new AtomicInteger(0);

    dto.getQuestionsAnswers()
    .stream()
    .forEach(questionAnswer -> {
      var targetQuestion = questionEntity.stream()
      .filter(question -> question.getId().equals(questionAnswer.getQuestionID()))
      .findFirst()
      .get();

      var findCorrectAlternative = targetQuestion.getAlternatives()
      .stream()
      .filter(alternative -> alternative.isCorrect())
      .findFirst()
      .get();

      if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())){
        questionAnswer.setCorrect(true);
        correctAnswers.incrementAndGet();
      } else {
        questionAnswer.setCorrect(false);
      }

      var answersCertificationsEntity = AnswerCertificationsEntity.builder()
      .answerID(questionAnswer.getAlternativeID())
      .questionID(questionAnswer.getQuestionID())
      .isCorrect(questionAnswer.isCorrect())
      .build();

      answerCertifications.add(answersCertificationsEntity);
    });

       // verificar se existe student pelo email
    var student = studentRepository.findByEmail(dto.getEmail());
    UUID studentID;
    if(student.isEmpty()) {
      var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
      studentCreated = studentRepository.save(studentCreated);
      studentID = studentCreated.getId();
    } else {
      studentID = student.get().getId();
    }

    CertificationsStudentEntity certificationsStudentEntity = CertificationsStudentEntity.builder()
    .technology(dto.getTechnology())
    .studentID(studentID)
    .grade(correctAnswers.get())
    .build();

    var certificationStudentCreated = certificationStudentRepository.save(certificationsStudentEntity);

    answerCertifications.stream().forEach(answerCertification -> {
      answerCertification.setCertificationID(certificationsStudentEntity.getId());
      answerCertification.setCertificationsStudentEntity(certificationsStudentEntity);
    });

    certificationsStudentEntity.setAnswerCertificationsEntities(answerCertifications);
    certificationStudentRepository.save(certificationsStudentEntity);
    return certificationStudentCreated;
  }

}
