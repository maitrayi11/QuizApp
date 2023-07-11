package com.mg.quizAPP.Question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("API/questions"))
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public List<QuestionData> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("language/{language}")
	public List<QuestionData> getQuestionByLanguage(@PathVariable String language) {
		return questionService.getQuestionByLanguage(language);
	}
	
	//To understand Exception handling and response entity
	
	@GetMapping("getByDL/{difficultyLevel}")
	public ResponseEntity<List<QuestionData>> getQuestionByDifficultyLevel(@PathVariable String difficultyLevel) {
		return questionService.getQuestionByDifficultyLevel(difficultyLevel);
	}
	
	@PostMapping("add")
	public String addQuestion(@RequestBody QuestionData questionData) {
		questionService.addQuestion(questionData);
		return "Your Question has been added";
	}
	
	@DeleteMapping("delete/{language}")
	public String deleteQuestion(@PathVariable String language) {
		 questionService.deleteQuestionByDifficultyLevel(language);
		return "Success";
	}
}
