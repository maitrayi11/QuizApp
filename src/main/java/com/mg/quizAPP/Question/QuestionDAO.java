package com.mg.quizAPP.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends JpaRepository<QuestionData,Integer> {
	
	//Returning a list of questions (find by method{language})
	List<QuestionData> findByLanguage(String language);
	
	void deleteByLanguage(String language);
	
	//Returning a list of questions (find by method{Difficulty level})
	List<QuestionData> findByDifficultyLevel(String language);

	@Query(value = "Select * from Question_data q where q.language = :language LIMIT :numQ ",nativeQuery = true)
	List<QuestionData> findRandomQuestions(String language, int numQ);
	
	
}
