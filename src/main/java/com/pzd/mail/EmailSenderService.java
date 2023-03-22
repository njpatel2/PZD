package com.pzd.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Properties;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	private static Properties props;

	/**
	 * @param toEmail
	 * @param httpRequest
	 */
	public void sendSimpleEmail(String toEmail, HttpServletRequest request) {
		MimeMessage message;
		try {
			message = makeMessage(request);
			message.setFrom("gamemaker22799@gmail.com");
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Welcome");
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Session getSessionForMail() {
		Session session = null;
		try {
			props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "25");
			session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("gamemaker22799@gmail.com", "wftioxnuecrjoewq");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public static MimeMessage makeMessage(HttpServletRequest request) {
		MimeMessage message = null;
		try {
			Session sessionForEmail = getSessionForMail();
			message = new MimeMessage(sessionForEmail);
			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>Welcome to Crazy Italian Pizza</H1><img src=\"cid:image\">";

			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			@SuppressWarnings("deprecation")
			String path = request.getRealPath("images") + File.separator;
			DataSource fds = new FileDataSource(path + "pizzalogo.jpg");

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return message;

	}

}
