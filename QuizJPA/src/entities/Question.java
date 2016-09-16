package entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@JoinColumn(name="quiz_id")
//	private int quiz_id;
	
	private String questionText;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="question_id", referencedColumnName="id",  nullable =false)
	private Set<Answer> answers;

	public Question(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getQuiz_id() {
//		return quiz_id;
//	}
//
//	public void setQuiz_id(int quiz_id) {
//		this.quiz_id = quiz_id;
//	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Override
	public String toString() {
		return "Question [id=" + id +", questionText=" + questionText + ", answers=" + answers
				+ "]";
	}
	
	
}
