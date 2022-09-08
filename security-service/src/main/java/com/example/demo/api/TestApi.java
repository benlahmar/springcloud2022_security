package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repo.IUser;

@RestController
public class TestApi {

	
	@Autowired
	public  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	IUser urepo;
	
	@PostMapping(path = "/test/save")
	public ResponseEntity<User> save(@RequestBody User u)
	{
		String password=bCryptPasswordEncoder.encode(u.password);
		u.setPassword(password);
		u=urepo.save(u);
		return new ResponseEntity<User>(u,HttpStatus.FOUND);
	}
	
	
	
	
	 @GetMapping("/test")
	public String test()
	{
		
		return "success";
	}
	 
	 @GetMapping("test2")
	 public List<User> test2()
	 {
		 return urepo.findAll();
	 }
}
