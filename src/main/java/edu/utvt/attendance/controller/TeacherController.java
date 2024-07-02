package edu.utvt.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.utvt.attendance.persistence.entities.Teacher;
import edu.utvt.attendance.persistence.service.TeacherService;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	
	@GetMapping("/all")
	public List<Teacher> get(){
		//return this.teacherService.findAll();
		return this.teacherService.getAll();
		
	}
	
	/*Forma reducida*/
	@GetMapping
	public Page<Teacher>getMethodoName(@RequestParam(value =  "page",defaultValue = "0" ) Integer page, @RequestParam(value = "size", defaultValue = "50") Integer size) {
		return this.teacherService.get(page, size);
	}
	
	
	@PostMapping
	public ResponseEntity<Teacher> save (@RequestBody Teacher teacher){
		return ResponseEntity.created(null).body(this.teacherService.save(teacher));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Teacher> update(@PathVariable("id") String id, @RequestBody Teacher teacher){
		return ResponseEntity.ok(this.teacherService.update(id, teacher));
		
	}
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
	public ResponseEntity<Teacher> delete(@PathVariable("id") String id){
		
		//this.teacherService.deleteById(id);
		//return ResponseEntity.noContent().build();
		return this.teacherService.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> findById(@PathVariable("id") String id){
		return ResponseEntity.of(this.teacherService.findByid(id));
	}

	

}
