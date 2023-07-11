package com.mg.quizAPP.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mg.quizAPP.Question.QuestionDAO;
import com.mg.quizAPP.Question.QuestionData;

@Service
public class QuizService {

	@Autowired
	QuizDAO quizDAO;
	
	@Autowired
	QuestionDAO questionDAO;

	public ResponseEntity<String> createQuiz(String language, int numQ, String title) {
		
		try {
		List<QuestionData> questions = questionDAO.findRandomQuestions(language,numQ);
		
		Quiz quiz = new Quiz();
		
		quiz.setTitle(title);
		quiz.setQuestion(questions);
		
		if(questions.isEmpty()) {
			return new ResponseEntity<>("Oops!!", HttpStatus.FORBIDDEN);
		}
		
		else {
		quizDAO.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("OOps!! Error", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		
		Optional<Quiz> quiz = quizDAO.findById(id); 
		//System.out.println(quiz.toString());
		List<QuestionData> question = quiz.get().getQuestion();
		
		List<QuestionWrapper> questionToShow = new ArrayList<>();
		
		for(QuestionData q: question) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getLanguage(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionToShow.add(qw);
		}
		
		return new ResponseEntity<>(questionToShow,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(int id, List<Answer> response) {
		Quiz quiz = quizDAO.findById(id).get();
		List<QuestionData> questions = quiz.getQuestion();
		int score=0;
		int i = 0;
		for(Answer answer: response) {
		 if(answer.getResponse().equals(questions.get(i).getRightAnswer()))
			 score++;
		 i++;
		}
		
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

	
}
