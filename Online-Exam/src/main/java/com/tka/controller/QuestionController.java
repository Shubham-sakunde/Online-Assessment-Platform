package com.tka.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entities.Answer;
import com.tka.entities.Question;

import jakarta.servlet.http.HttpSession;

@RestController
public class QuestionController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("getFirstQuestion/{subject}")
	public Question getFirstQuestion(@PathVariable String subject) {

		Session session = factory.openSession();
		Query query = session.createQuery("from Question where subject=:subject");
		query.setParameter("subject", subject);
		List<Question> list = query.list();

		HttpSession httpSession = LoginController.httpSession;

		httpSession.setAttribute("allquestions", list);

		Question firstQuestion = list.get(0);

		return firstQuestion;

	}

	@RequestMapping("nextQuestion")
	public Question nextQuestion() {

		HttpSession httpSession = LoginController.httpSession;

		List<Question> list = (List<Question>) httpSession.getAttribute("allquestions");

		Question question;

		if ((int) httpSession.getAttribute("questionIndex") < list.size() - 1) {

			httpSession.setAttribute("questionIndex", (Integer) httpSession.getAttribute("questionIndex") + 1);

			question = list.get((Integer) httpSession.getAttribute("questionIndex"));

		} else {
			question = list.get(list.size() - 1);
		}

		return question;

	}

	@RequestMapping("previousQuestion")
	public Question previousQuestion() {

		HttpSession httpSession = LoginController.httpSession;

		Question question;

		List<Question> list = (List<Question>) httpSession.getAttribute("allquestions");

		if ((int) httpSession.getAttribute("questionIndex") > 0) {

			httpSession.setAttribute("questionIndex", (Integer) httpSession.getAttribute("questionIndex") - 1);

			question = list.get((Integer) httpSession.getAttribute("questionIndex"));

		} else {

			question = list.get(0);

		}

		return question;

	}

	@PostMapping("saveAnswer")
	public void saveAnswer(@RequestBody Answer answer) {

		HttpSession httpSession = LoginController.httpSession;

		HashMap<Integer, Answer> map = (HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

		map.put(answer.getQno(), answer);

		System.out.println(map);

	}

	@RequestMapping("calculateScore")
	public Integer calculateScore() {
		HttpSession httpSession = LoginController.httpSession;

		HashMap<Integer, Answer> map = (HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

		Collection<Answer> collection = map.values();

		for (Answer answer : collection) {

			if (answer.getSubmittedAnswer().equals(answer.getOriginalAnswer())) {
				httpSession.setAttribute("score", (int) httpSession.getAttribute("score") + 1);
			}

		}

		return (int) httpSession.getAttribute("score");
	}

}
