package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService qs;
	private final AnswerService as;
	
	@PostMapping("/create/{id}")
	public String createAnswer(
			Model model, 
			@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindingResult
			) 
	{

		Question q = qs.getQuestion(id);
		if (bindingResult.hasErrors()) {
            model.addAttribute("q", q);
            return "question_detail";
        }
		
		as.create(q, answerForm.getContent());

		return String.format("redirect:/question/detail/%s", id);
		//return "redirect:/question/detail/" + id;
	}
}