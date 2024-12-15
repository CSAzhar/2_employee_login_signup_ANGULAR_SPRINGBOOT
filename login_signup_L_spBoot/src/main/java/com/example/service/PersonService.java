package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Dao.LoginVAO;
import com.example.entity.Person;
import com.example.repository.PersonRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MailService mailService;
	
	
	public Person save(Person person)  throws Exception{
		String verificationCode = generateVarificationCode();
		person.setVerificationCode(verificationCode);
		person.setSignupStatus(0);
		
		String username = person.getUsername();
		person = personRepository.save(person);
		mailService.sendMail(username, verificationCode);
		return person;
		
	}
	
	public String verifyEmail(String email, String verificationCode) {
		System.out.println("inside mail verify method in service");
		String status = "";
		
		Person person = personRepository.findByUsername(email);
		if(person.getVerificationCode().equals(verificationCode)) {
			person.setSignupStatus(1);
			personRepository.save(person);
			status = "Successfully activated your Account";
		}else {
			status = "Verification code is Wrong";
		}
		System.out.println("Person verify mathod in PersonService is Last ok tata vbye bye");
		
		return status;
		
		
	}
	
	
	
	
	private String generateVarificationCode() 
	{
		String src = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		int alength = src.length();
		Random random = new Random();
		for (int i = 1; i <= 15; i++) {
			sb.append(     src.charAt( (int) (random.nextDouble() * alength ))      );
		}
		return sb.toString();
	}
	
	
	public String forgottenPassword(String email) throws Exception 
	{
		return mailService.forgottenPassword(email);
		
	}
	
	public String resetPassword(String email, String password) 
	{
		System.out.println("inside person service");
		System.out.println("Email check"+email);
		Person person = personRepository.findByUsername(email);
		person.setPassword(password);
		System.out.println(person.getFirstName()+" "+person.getUsername());
		personRepository.save(person);
		return "Success Password Reset";
	}
	
	public String changePassword(Person person) 
	{
		System.out.println("inside service");
		Person dbPerson = personRepository.findByUsername(person.getUsername());
		dbPerson.setPassword(person.getPassword());
		personRepository.save(dbPerson);
		return "Password Changed Successfully";
	}
	
	public LoginVAO login(Person person) {
		System.out.println("inside service login");
		LoginVAO vao = new LoginVAO();
		vao.setMessage("Login failed pls try again");
		Person dbPerson = personRepository.findByUsername(person.getUsername());
		if(dbPerson.getPassword().equals(person.getPassword())) {
			vao.setLoginStatus(true);
			vao.setMessage("Login Successfull");
		}
		return vao;
	}
	
	
	

}






