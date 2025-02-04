package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	 Question findBySubject(String subject);
	 Question findByContent(String content);
	 Question findBySubjectAndContent(String subject, String content);
	 Question findBySubjectOrContent(String subject, String content);
	 List<Question> findBySubjectLike(String subject);
}