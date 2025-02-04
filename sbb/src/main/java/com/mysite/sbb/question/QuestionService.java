package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository qr;

    public List<Question> getList() {
        return qr.findAll();
    }
    
    public Question getQuestion(Integer id) {
    	
    	Optional<Question> q = qr.findById(id);
    	if (q.isPresent()) {
    		return q.get();
    	}else{
    		throw new DataNotFoundException("질문이 없습니다");
    	}
    }
    
    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        qr.save(q);
    }
}