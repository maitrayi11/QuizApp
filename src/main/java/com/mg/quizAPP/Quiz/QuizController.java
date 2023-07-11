package com.mg.quizAPP.Quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("API/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		return quizService.getQuiz(id);
	}
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam("language") String language,@RequestParam("numQ") int numQ ,@RequestParam("title") String title){
		return quizService.createQuiz(language,numQ,title);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Answer> answers){
		return quizService.calculateScore(id,answers);
		
	}
}
