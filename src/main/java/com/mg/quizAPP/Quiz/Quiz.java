package com.mg.quizAPP.Quiz;

import java.util.List;

import com.mg.quizAPP.Question.QuestionData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data 
public class Quiz {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ManyToMany
	private List<QuestionData> question;
}
