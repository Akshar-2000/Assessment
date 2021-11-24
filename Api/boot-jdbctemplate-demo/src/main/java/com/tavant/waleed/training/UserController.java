package com.tavant.waleed.training;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository repo;

	@GetMapping("/count")
	int getCountOfUsers() {
		return repo.getCountOfUsers();
	}
	
	@GetMapping("/accounts")
	public List<Accounts> users(){
		return repo.users();
	}
	
	@PostMapping("/add")
	void addNewUser(@RequestBody Accounts acc) {
		repo.addNewUser(acc);
	}
	
	@PostMapping("/view")
	Accounts viewUser(@RequestBody Accounts acc) {
		return repo.viewUser(acc);
	}
	
	@DeleteMapping("/delete")
	void deleteAccountDetails(@RequestBody Accounts acc) {
		repo.deleteAccountDetails(acc);
	}
	
	@PutMapping("/update/{id}")
	void updateDetails(@PathVariable int id,@RequestBody Accounts acc) {
		repo.updateDetails(id, acc);
	}
}
