package com.Manager.Controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Manager.Models.ManagerInfo;
import com.Manager.Repo.ManagerRepo;
import com.Manager.SecurityConfig.ManagerAuthResponse;
import com.Manager.SecurityConfig.ManagerService;


@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManagerRepo managerRepo;
	@Autowired
	private AuthenticationManager authenticationManager;

//Creating/Adding Washer
	
	@PostMapping("/addManager")
	private ResponseEntity<?> saveOwnerInfo(@RequestBody ManagerInfo managerInfo) {
		String email = managerInfo.getEmail();
		String password = managerInfo.getPassword();
		ManagerInfo manager1 = new ManagerInfo();
		manager1.setEmail(email);
		manager1.setPassword(password);
		try {

			managerRepo.save(managerInfo);
		} catch (Exception e) {
			return ResponseEntity.ok(new  ManagerAuthResponse("Error creating Manager" + email));
		}
		return ResponseEntity.ok(new ManagerAuthResponse("Successfully created Manager " + email));
	}

//authenticating washer
	@PostMapping("/auth")
	private ResponseEntity<?> authWasher(@RequestBody ManagerInfo managerInfo) {
		String email = managerInfo.getEmail();
		String password = managerInfo.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception e) {
			return ResponseEntity.ok(new ManagerAuthResponse("Error during Manager Authentication" + email));
		}
		return ResponseEntity.ok(new ManagerAuthResponse("Successfully Authenticated Manager" + email));
	}

//Reading all washer
	@GetMapping("/manager")
	public List<ManagerInfo> findAllManager() {
		return managerService.getManagerInfos();
	}

}
