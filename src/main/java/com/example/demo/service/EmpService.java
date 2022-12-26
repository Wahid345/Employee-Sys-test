package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.employee;
import com.example.demo.repository.empRepo;

@Service
public class EmpService {
	
	@Autowired
	private empRepo repo;
	
	public void addEmp(employee e) {
	 repo.save(e);
		
	}
	
	public List<employee> getAllEmp(){
		
		return repo.findAll();
		
		
	}
	public employee getEmpById(int id) {
		Optional<employee> e= repo.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
		}
	public void deleteEmp(int id) {
		repo.deleteById(id);;
	}

	public Page<employee> getEMpByPaginate(int currentPage, int size) {
		Pageable p = PageRequest.of(currentPage, size);
		return repo.findAll(p);
	}
	
}
