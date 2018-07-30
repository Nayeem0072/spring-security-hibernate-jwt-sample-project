package com.syn.controllers;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.syn.dao.TestProcTableDao;
import com.syn.dao.TestProcTableDaoImpl;
import com.syn.entities.TestProcTable;

@Controller
@RequestMapping("/hello")
public class HomeController {
	String message = "Welcome to Spring MVC!";	
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private static final TestProcTableDao procTableDao = new TestProcTableDaoImpl();

	@RequestMapping("/user")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		//logger.error("Logger: in controller");
		List userList = procTableDao.getAllProcUsers();
		procTableDao.showProcUsers(userList);
		
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("users", userList);
		mv.addObject("message", message);
		mv.addObject("name", name);		
		mv.addObject("testproctable", new TestProcTable());
		return mv;
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView submit(@Validated @ModelAttribute("testproctable")TestProcTable user, 
      BindingResult result, ModelMap model) {
		String message = "Successfully Processed.";
        if (result.hasErrors()) {
        	message = "Error in binding.";
        }
        else {
        	boolean status = procTableDao.addProcUser(user);
        	if(!status){
        		message = "Error in inserting.";
        	}
        }
        model.addAttribute("name", user.getName());
        
        System.out.println("In the POST method: Name = " + user.getName());
        
        List userList = procTableDao.getAllProcUsers();
		procTableDao.showProcUsers(userList);
        
        
        ModelAndView mv = new ModelAndView("helloworld");
        mv.addObject("users", userList);
		mv.addObject("message", message);
		mv.addObject("name", user.getName());		
		mv.addObject("testproctable", new TestProcTable());
		return mv;
    }
}
















//public void hibernateInsertion(){
//SessionFactory sessionFactory = new Configuration().configure()
//		.buildSessionFactory();
//
//Session session = sessionFactory.openSession();
//Transaction ts = null;
//try {
//	ts = session.beginTransaction();
//	TestUser user = new TestUser("user66");
//	session.saveOrUpdate(user);
//	ts.commit();
//}
//catch (Exception e) {
//	if (ts != null) 
//		ts.rollback();
//	throw e;
//}
//finally {
//	session.close();
//}
//
//}