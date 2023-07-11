package com.mg.quizAPP.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mg.quizAPP.Question.QuestionDAO;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
	
	public List<QuestionData> getAllQuestions() {
		return questionDAO.findAll();
	}
	
	public List<QuestionData> getQuestionByLanguage(String language) {
		return questionDAO.findByLanguage(language);
	}
	
	public void addQuestion(QuestionData questionData) {
		questionDAO.save(questionData);
	}
	
	public void deleteQuestionByDifficultyLevel(String language) {
		 questionDAO.deleteByLanguage(language);
	}
	
	
	//Response Entity and exception handling
	public ResponseEntity<List<QuestionData>> getQuestionByDifficultyLevel(String difficultyLevel) {
		try {
		 return new ResponseEntity<>(questionDAO.findByDifficultyLevel(difficultyLevel), HttpStatus.OK );
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
}
