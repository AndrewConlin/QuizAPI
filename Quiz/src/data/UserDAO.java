package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
public class UserDAO {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public List<User> getUsers() {
		String query = "Select u from User u";
		return em.createQuery(query, User.class).getResultList();
	}

	public User getUser(int id) {
		return em.find(User.class, id);
	}

	public void createUser(User user) {
	    String rawPassword = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(rawPassword);
	    
	    user.setPassword(encodedPassword);
		em.persist(user);
		em.flush();
	}

	public void editUser(int id, User user) {
	    String rawPassword = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(rawPassword);
	    
		User u = em.find(User.class, id);
		u.setUsername(user.getUsername());
		u.setPassword(encodedPassword);
	}

	public boolean deleteUser(int id) {
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
			return true;
		} else {
			return false;
		}
	}
}
