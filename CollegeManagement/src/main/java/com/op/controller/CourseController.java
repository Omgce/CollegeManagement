package com.op.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.op.entity.Course;
import com.op.entity.Student;
import com.op.repository.CourseRepository;
import com.op.repository.StudentRepository;

@Controller
public class CourseController {

	@Autowired
	public CourseRepository repo;

	@Autowired
	public StudentRepository srepo;

	@GetMapping("/courses")
	public String listCourse(Model model) {
		List<Course> listCourses = repo.findAll();
		model.addAttribute("listCourses", listCourses);
		return "courses";
	}

	@GetMapping("/courses/new")
	public String showCourses(Model model) {
		List<Student> listStudents = srepo.findAll();

		model.addAttribute("listStudents", listStudents);
		model.addAttribute("course", new Course());

		return "course_form";
	}

	@PostMapping("/courses/save")
	public String saveCourse(Course course) {
		repo.save(course);
		return "redirect:/courses";
	}

	@GetMapping("/courses/edit/{id}")
	public String updateCourse(@PathVariable("id") Long id, Model model) {
		Course course = repo.findById(id).get();
		model.addAttribute("course", course);

		List<Student> listStudents = srepo.findAll();
		model.addAttribute("listStudents", listStudents);

		return "course_form";
	}

	@GetMapping("courses/delete/{id}")
	public String deleteCourse(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:/courses";
	}

}
