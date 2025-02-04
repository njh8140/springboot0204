package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository ar;
	
	public void create(Question q, String content) {
		
		Answer a = new Answer();
		a.setContent(content);
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		ar.save(a);
	
	}
}