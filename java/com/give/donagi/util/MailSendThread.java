package com.give.donagi.util;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSendThread extends Thread{
	private String authKey;
	private String mailTo;
	private JavaMailSender mailSender;
	
	public MailSendThread(String authKey, String mailTo, JavaMailSender mailSender) {
		this.authKey = authKey;
		this.mailTo = mailTo;
		this.mailSender = mailSender;
	}
	
	public void run() {
		try {
			MimeMessage message = null;
			MimeMessageHelper messageHelper = null;
			String mailText = "";
			
			mailText += "아래링크를 클릭하셔서 인증해 주세요<br>";
			mailText += "<a href='http://localhost:8181/member/auth_member.do?auth_key=" + authKey + "'>인증하기</a>";
			
			message = this.mailSender.createMimeMessage();
			messageHelper = new MimeMessageHelper(message, true, "utf-8");
			messageHelper.setSubject("[NANUGI] 인증키 발송");
			messageHelper.setText(mailText, true);
			messageHelper.setFrom("admin");
			messageHelper.setTo(this.mailTo);
			this.mailSender.send(message);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
