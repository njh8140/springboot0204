package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;


@SpringBootTest
class SbbApplicationTests {

	@Autowired
    private QuestionRepository qr;
	
	@Autowired
    private AnswerRepository ar;
	
	//@Test // 질문 테스트
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("java?");
		q1.setContent("jsp!");
		q1.setCreateDate(LocalDateTime.now());
		
        qr.save(q1);  

	}
	
	//@Test // select all 테스트
	void testFindAll() {
		List<Question> all = qr.findAll();
        assertEquals(2, all.size());

        for(int i=0; i<all.size(); i++) {
        	Question q = all.get(i);
        	System.out.println("@@@@ " + q.getSubject());
        	
        }
        
        //Question q = all.get(0);
        //assertEquals("sbb?", q.getSubject());
	}
	
	//@Test // id 번호로 검색
	void testFindById() {
		 Optional<Question> q = this.qr.findById(2);
	        if(q.isPresent()) {
	            Question tmp = q.get();
	            System.out.println("@@@@ " + tmp.getSubject());
	        }else {
	        	System.out.println("존재하지 않습니다");
	        }
	}
	
	//@Test // subject로 검색
	void testFindBySubject() {
		Question q = qr.findBySubject("sbb?");
		System.out.println("@@@@ " + q.getId());
	}
	
	//@Test // content로 검색
	void testFindByContent() {
		Question q = qr.findByContent("sbb!");
		System.out.println("@@@@ " + q.getId());
	}
	
	//@Test // subject & content로 검색
	void testFindBySubjectAndContent() {
		Question q = qr.findBySubjectAndContent("sbb?", "sbb!");
		System.out.println("@@@@ " + q.getId());
	}
	
	//@Test // subject or content로 검색
	void testFindBySubjectOrContent() {
		Question q = qr.findBySubjectOrContent("sbb?", "sbb!123");
		System.out.println("@@@@ " + q.getId());
	}
	
	//@Test // subject 중 해당 키워드로 검색
	void testFindBySubjectLike() {
		List<Question> list = qr.findBySubjectLike("%sbb%");
		
		for(Question q: list) {
			System.out.println(q.getSubject());
		}
	}
	
	//@Test // 데이터 수정 테스트
	void testUpdate() {
		Optional<Question> oq = qr.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("제목 수정22");
		//q.setContent("내용 수정2");
		qr.save(q);
		
	}
	
	//@Test // 데이터 삭제 테스트
	void testDelete() {
		Optional<Question> oq = qr.findById(1);
	    assertTrue(oq.isPresent());
	    Question q = oq.get();
	    qr.delete(q);
	}
	
	//@Test // answer 생성 테스트
	void testAnswer() {
		Optional<Question> oq = qr.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        
        System.out.println(q.getSubject());

        Answer a = new Answer();
        a.setContent("답변 저장");
        a.setQuestion(q);  
        a.setCreateDate(LocalDateTime.now());
        ar.save(a);
	}
	
	//@Test // 답변 조회
	void testFindAnswer() {
		Optional<Answer> oa = ar.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
	}
	
	@Transactional
	@Test // 답변 리스트 조회
	void testFindByIdAnswer() {
		Optional<Question> oq = qr.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> al = q.getAnswerList();
        
        System.out.println(al.size());
        
        for(Answer a : al)
        	System.out.println(a.getContent());
	}
	
}