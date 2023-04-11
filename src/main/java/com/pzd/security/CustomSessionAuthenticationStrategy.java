package com.pzd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

@Component
public class CustomSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		if (authentication == null || !authentication.isAuthenticated()) {
			return;
		}

		HttpSession session = request.getSession(false);
		if (session != null) {
			// Migrate session attributes to new session if necessary
			if (session.getAttributeNames().hasMoreElements()) {
				Map<String, Object> attributes = new HashMap<>();
				Enumeration<String> attributeNames = session.getAttributeNames();
				while (attributeNames.hasMoreElements()) {
					String name = attributeNames.nextElement();
					attributes.put(name, session.getAttribute(name));
				}
				session.invalidate();
				session = request.getSession();
				for (Map.Entry<String, Object> entry : attributes.entrySet()) {
					session.setAttribute(entry.getKey(), entry.getValue());
				}
			}

			// Set session timeout based on user role
			String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.filter(a -> a.startsWith("ROLE_")).findFirst().orElse(null);
			if ("ROLE_ADMIN".equals(role)) {
				session.setMaxInactiveInterval(86400); // 24 hours
			} else {
				session.setMaxInactiveInterval(1800); // 30 minutes
			}

			// Publish SessionFixationProtectionEvent
			HttpSessionEvent event = new HttpSessionEvent(session);
			applicationEventPublisher.publishEvent(event);
		}
	}

}
