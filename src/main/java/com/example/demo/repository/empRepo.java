package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.employee;

@Repository
public interface empRepo extends JpaRepository<employee,Integer>{

	
}