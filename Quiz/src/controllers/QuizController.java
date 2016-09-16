package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuizDAO;
import entities.Question;
import entities.Quiz;
import entities.Score;

@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
    private QuizDAO quizDAO;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public List<Quiz> index(){
		return quizDAO.index();
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Quiz show(@PathVariable int id){
		return quizDAO.show(id);
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public void create(@RequestBody String quizJSON, HttpServletResponse res){
		ObjectMapper mapper = new ObjectMapper();
		
		try{
			Quiz quiz = mapper.readValue(quizJSON, Quiz.class);
			quizDAO.create(quiz);
			res.setStatus(200);

		}
		catch(Exception e){	
			e.printStackTrace(); 
			res.setStatus(400);
		}	
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody Quiz quiz){
		//implement later
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){
		quizDAO.destroy(id);
	}
	
	
	@RequestMapping(path="/{id}/scores", method=RequestMethod.GET)
	public List<Score> showScores(@PathVariable int id){
		List<Score> scores = quizDAO.showScores(id);
		for (Score s : scores) {
			System.out.println(s);
		}
		return scores;
	}
	
	@RequestMapping(path="/{id}/questions", method=RequestMethod.POST)
	public void addQuestion(@PathVariable int id, @RequestBody String questionJSON, HttpServletResponse res){
		ObjectMapper mapper = new ObjectMapper();
		
		try{
			Question question = mapper.readValue(questionJSON, Question.class);
			quizDAO.addQuestion(id, question);
			res.setStatus(200);
		} 
		catch(Exception e){	
			e.printStackTrace(); 
			res.setStatus(400);
		}	
	}
}
