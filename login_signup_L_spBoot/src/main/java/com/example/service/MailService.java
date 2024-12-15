package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendMail(String username, String verificationCode) throws Exception{
		
		System.out.println("message invoked mail class");
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		StringBuffer htmlMsg = new StringBuffer();
		htmlMsg.append("<h1 style='color:red'>Please activate by cicking below link</h1>");
		htmlMsg.append("<h2 style='color:orange'><a href='http://localhost:9090/person/verifyEmail?email="+username+"&verificationCode="+verificationCode+"'>Verify Email</a></h2>");
		
		
		messageHelper.setText(htmlMsg.toString(), true);
		messageHelper.setTo(username);
		messageHelper.setSubject("Verify/activate email");
		//messageHelper.setFrom("");
		
		javaMailSender.send(mimeMessage);
		System.out.println("Mail Sent mail class");
		return "success";
	}
	
	public String forgottenPassword(String email)  throws Exception
	{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		StringBuffer htmlMsg = new StringBuffer();
		htmlMsg.append("<h1>Reset Your Password</h1>");
		htmlMsg.append("<form action='http://localhost:9090/person/resetPassword' method = 'post'>");
		htmlMsg.append("New Password : <input type='password' name='password'><br><br>");
		htmlMsg.append("Confirm Password: <input type='password' name='confirmPassword'> <br><br>");
		htmlMsg.append("<input type='hidden' name='email' value='"+email+"'> <br><br>");
		htmlMsg.append("<input type='submit' value='Change Password'>");
		htmlMsg.append("</form>");
		
		helper.setText(htmlMsg.toString(), true);
		helper.setTo(email);
		helper.setSubject("Reset Password Form");
		javaMailSender.send(mimeMessage);
		
		
		return "Mail Sent to "+email+" to Reset Password";
	}
	
//	public String testMail() throws Exception {
//		
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
//		String htmlMsg = "<a href='https://www.google.com/'>Test Link </a>";
//		messageHelper.setText(htmlMsg, true);
//		messageHelper.setTo("786azhar134@gmail.com");
//		messageHelper.setSubject("This is test message to check testimg gmail smtp service");
//		//messageHelper.setFrom("");
//		
//		javaMailSender.send(mimeMessage);
//		System.out.println("Mail Sent");
//		return "success";
//	}

}
