package com.erdal.service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.erdal.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	private String userName; // user email ile login olacağı için ekledik
	
	
	private String password;
	
	// roller Granted türünde olmalı
	private Collection<? extends GrantedAuthority> authorities;
	
//	 user --> UserDetails dönüşümünü yapacak build() metodu 
	public static UserDetailsImpl build(UserDTO userDto) {
		     List<SimpleGrantedAuthority> authorities = userDto.
		    		                                    getRoles().
		    		                                    stream().
		    		 									map(role->new SimpleGrantedAuthority(role)).
		    		 									collect(Collectors.toList());	
		    
		    		 																																					
		     return new UserDetailsImpl(userDto.getUserName(), userDto.getPassword(), authorities);
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
