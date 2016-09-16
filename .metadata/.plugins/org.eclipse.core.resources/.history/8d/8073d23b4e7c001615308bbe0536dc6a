package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Answer;
import entities.Question;
import entities.Quiz;
import entities.Score;

@Transactional
public class QuizDAO {
	@PersistenceContext
	private EntityManager em;
	
	public List<Quiz> index(){
		String query = "Select q from Quiz q";
		return em.createQuery(query, Quiz.class).getResultList();
	}
	
	public Quiz show(int id){
		Quiz q =  em.find(Quiz.class, id); 
		System.out.println(q);
		System.out.println("QUIZ");
		return q; 
	}
	
	public void create(Quiz q){
		em.persist(q); 
		em.flush();
	}
	
	public void destroy(int id){		
		Quiz q = em.find(Quiz.class, id); 
		em.remove(q);
	}
	
	public List<Score> showScores(int quizID){
		String query = "Select s from Score s WHERE s.quiz_id = " + quizID;
		return em.createQuery(query, Score.class).getResultList();
	}
	
	public void addQuestion(int id, Question question){
		//need quiz id but cant have a field
		System.out.println(question);
		em.persist(question);
		em.flush();
 	}
}
