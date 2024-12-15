package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Person;
import java.util.List;


public interface PersonRepository extends CrudRepository<Person, Integer>{
	
	public Person findByUsername(String username);

}
