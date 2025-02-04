package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	//private final QuestionRepository qr;
	private final QuestionService qs;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		//List<Question> questionList = qr.findAll();
		List<Question> questionList = qs.getList();
		
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		Question q = qs.getQuestion(id);
		
		model.addAttribute("q", q);
		
		return "question_detail";
	}
	
	@GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
	
	@PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        qs.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
	
}