package com.example.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dao.ChangePasswordDAO;
import com.example.Dao.LoginVAO;
import com.example.entity.Person;
import com.example.service.MailService;
import com.example.service.PersonService;
import com.example.stage1_signup.ForgottenPasswordVAO;

@RestController
@RequestMapping("person")
@CrossOrigin
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
//	@GetMapping
//	public ResponseEntity<Person[]> getAllUsers(){
//		
//	}
//	
	@PostMapping("save")
	public ResponseEntity<Person> save(@RequestBody Person person) throws Exception{
		System.out.println("inside person save controller");
		
		System.out.println("Controller finished");
		return ResponseEntity.ok(personService.save(person));
	}
	
	
	
	@GetMapping("verifyEmail")
	public ResponseEntity<String> verifyEmail(@RequestParam String email,
											  @RequestParam String verificationCode) {
		System.out.println("inside verify email controller");
		return ResponseEntity.ok(personService.verifyEmail(email, verificationCode));
		
	}
	
	
	
	
	
	@GetMapping("/forgottenPassword/{email}")
	public ResponseEntity<ForgottenPasswordVAO> forgottenPassword(@PathVariable String email) throws Exception
	{
		System.out.println("Inside forgotten controller");
		String results = personService.forgottenPassword(email);
		ForgottenPasswordVAO vao = new ForgottenPasswordVAO();
		vao.setResults(results);
		
		return ResponseEntity.ok(vao);
	}
	
	
	
	
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String password)
	{
		
		return ResponseEntity.ok(personService.resetPassword(email, password));
	}
	
	@PostMapping("changePassword")
	public ResponseEntity<ChangePasswordDAO> changePassword(@RequestBody Person person)
	{
		System.out.println("inside control");
		ChangePasswordDAO dao = new ChangePasswordDAO();
		String res = personService.changePassword(person);
		System.out.println("this is res = "+res);
		dao.setResult(res);
		System.out.println("Dao object"+dao);
		return  ResponseEntity.ok(dao);
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginVAO> login(@RequestBody Person person) throws Exception{
		System.out.println("inside control login");
		return ResponseEntity.ok(personService.login(person));
	}
	
	
//	@Autowired
//	private MailService mailService;
//	
	
//	@GetMapping()
//	public String sendMail() throws Exception{
//		return mailService.testMail();
//	}

}
