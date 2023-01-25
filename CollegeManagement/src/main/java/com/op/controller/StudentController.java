package com.op.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.op.entity.Student;
import com.op.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	public StudentRepository repo;

	@GetMapping("/students")
	public String listStudents(Model model) {
		List<Student> listStudents = repo.findAll();
		model.addAttribute("listStudents", listStudents);
		return "students";
	}

	@GetMapping("/students/new")
	public String showStudent(Model model) {
		model.addAttribute("student", new Student());
		return "student_form";
	}

	@PostMapping("/students/save")
	public String saveStudent(Student student) {
		repo.save(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String updateStudent(@PathVariable("id") Long id, Model model) {
		Student student = repo.findById(id).get();
		model.addAttribute(student);
		return "student_form";
	}
	@GetMapping("students/delete/{id}")
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:/students";
	}

}
