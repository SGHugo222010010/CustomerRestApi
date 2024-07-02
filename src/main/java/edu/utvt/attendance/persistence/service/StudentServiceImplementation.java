package edu.utvt.attendance.persistence.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.utvt.attendance.persistence.entities.Student;
import edu.utvt.attendance.persistence.repositories.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student save(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public Student update(String id, Student student) {
		
		Optional<Student> studentOptional = null;
		
		studentOptional = this.studentRepository.findById(id);
		
		if (studentOptional.isPresent()) {
			studentOptional.get().setId(student.getId());
			studentOptional.get().setFirstName(student.getFirstName());
			studentOptional.get().setLastName(student.getLastName());
			studentOptional.get().setEmail(student.getEmail());
			studentOptional.get().setType(student.getType());
			studentOptional.get().setBirthDate(student.getBirthDate());
		
			this.studentRepository.save(studentOptional.get());
		}
		return studentOptional.orElse(student);
	}
	

	@Override
	public List<Student> getAll() {
		return this.studentRepository.findAll();
	}

	@Override
	public Optional<Student> findByid(String id) {
		return this.studentRepository.findById(id);
	}

	@Override
	public ResponseEntity<Student> deleteById(String id) {
		
		Optional<Student> studentOptional = null;
		ResponseEntity<Student> responseEntity = null;
		
		studentOptional = this.studentRepository.findById(id);
		
		if (studentOptional.isPresent()) {
			this.studentRepository.delete(studentOptional.get());
			responseEntity = ResponseEntity.noContent().build();
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
	}

	@Override
	public Page<Student> get(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("lastName"));
		return this.studentRepository.findAll(pageRequest);
	}
	

	
	
	

}
