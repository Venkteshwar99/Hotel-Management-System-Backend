package com.Owner.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Owner.Models.OwnerInfo;
import com.Owner.Repo.OwnerRepo;


@Service
public class OwnerService implements UserDetailsService {
	
	
	@Autowired
	private OwnerRepo ownerRepo;

//
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		OwnerInfo foundedUser=ownerRepo.findByemail(email);
		if (foundedUser==null) {
			return null;
		}
		String email1=foundedUser.getEmail();
		String pass=foundedUser.getPassword();
		return new User(email1, pass,new ArrayList<>());
	}

}
