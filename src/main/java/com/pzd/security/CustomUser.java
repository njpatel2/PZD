package com.pzd.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.pzd.entities.User;

public class CustomUser implements OAuth2User, UserDetails {

	private static final long serialVersionUID = 1L;

	private OAuth2User oauth2User;

	private User user;

	public CustomUser(User user) {
		super();
		this.user = user;
	}

	public CustomUser(OAuth2User oauth2User) {
		this.oauth2User = oauth2User;
	}

	public CustomUser(OAuth2User oauth2User, User user) {
		this.oauth2User = oauth2User;
		this.user = user;
	}

	public OAuth2User getOauth2User() {
		return oauth2User;
	}

	public void setOauth2User(OAuth2User oauth2User) {
		this.oauth2User = oauth2User;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		if (!Objects.isNull(user)) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
			return List.of(simpleGrantedAuthority);
		} else {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(oauth2User.getAuthorities().toString());
			return List.of(simpleGrantedAuthority);
		}

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	public String getUserEmail() {
		return user.getEmail();
	}

	public int getUserId() {
		return user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getName() {
		return user.getName();
	}
}