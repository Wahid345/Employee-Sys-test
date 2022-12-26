package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.employee;
import com.example.demo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class empController {
	@Autowired
	private EmpService service;
	
	@GetMapping("/index")
	public String home(Model m) 
	{
		 List<employee> emp=service.getAllEmp();
		m.addAttribute("emp",emp);
		 return "index";
	}
	
	@GetMapping("/RegisterEmp")
	public String RegisterEmp() {
		return "RegisterEmp";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute employee e,HttpSession session) {
			
		System.out.println(e);
		
		service.addEmp(e);
		session.setAttribute("msg", "Employee Added SuccessFully");
		return "redirect:/";
		
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m) {
		
		employee e=service.getEmpById(id);
		m.addAttribute("emp",e);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute employee e, HttpSession session) {
		service.addEmp(e);
		session.setAttribute("msg", "Employee Data Update SuccessFully");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session) {
		service.deleteEmp(id);
		session.setAttribute("msg","Employee Data Delete SuccessFully");
		return "redirect:/";
		
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<employee> emplist = service.getEMpByPaginate(pageno, 2);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}

}
