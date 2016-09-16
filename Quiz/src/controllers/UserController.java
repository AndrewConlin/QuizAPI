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

import data.UserDAO;
import entities.User;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
    private UserDAO userDAO;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public List<User> index(){
		return userDAO.getUsers();
	}
	
	@RequestMapping(path= "{id}", method=RequestMethod.GET)
	public User show(@PathVariable int id){
		User u = userDAO.getUser(id);
		System.out.println(u);
		return u;
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody String userJSON, HttpServletResponse res){
		ObjectMapper mapper = new ObjectMapper();
		
		try{
			User user = mapper.readValue(userJSON, User.class);
			userDAO.editUser(id, user);
			res.setStatus(200);

		}
		catch(Exception e){	
			e.printStackTrace(); 
			res.setStatus(400);
		}
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public void create(@RequestBody String userJSON, HttpServletResponse res){		
		ObjectMapper mapper = new ObjectMapper();
		
	    try {
			User user = mapper.readValue(userJSON, User.class);
			userDAO.createUser(user);
			res.setStatus(201);

		} catch (Exception e) {
			e.printStackTrace(); 
			res.setStatus(422);
		}
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id, HttpServletResponse res){
		boolean isDestroyed = userDAO.deleteUser(id);
		if(isDestroyed){
			res.setStatus(200);
		}
		else{
			res.setStatus(400);
		}
	}
}
