package com.op.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.op.entity.Course;
import com.op.entity.Teacher;
import com.op.repository.CourseRepository;
import com.op.repository.TeacherRepository;

@Controller
public class TeacherController {

	@Autowired
	public TeacherRepository repo;

	@Autowired
	public CourseRepository crepo;

	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		List<Teacher> listTeacher = repo.findAll();
		model.addAttribute("listTeacher", listTeacher);
		return "teachers";
	}

	@GetMapping("/teachers/new")
	public String showTeacher(Model model) {
		List<Course> listCourses = crepo.findAll();
		model.addAttribute("listCourses", listCourses);	

		model.addAttribute("teacher", new Teacher());
		return "teacher_form";
	}

	@PostMapping("/teachers/save")
	public String saveTeacher(Teacher teacher) {
		repo.save(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{id}")
	public String updateTeacher(@PathVariable("id") Long id, Model model) {
		List<Course> listCourses = crepo.findAll();
		model.addAttribute("listCourses", listCourses);	
		Teacher teacher = repo.findById(id).get();
		model.addAttribute(teacher);
		return "teacher_form";
	}

	@GetMapping("teachers/delete/{id}")
	public String deleteTeacher(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:/teachers";
	}
}
