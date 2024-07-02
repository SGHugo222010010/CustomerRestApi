package edu.utvt.attendance.persistence.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.utvt.attendance.persistence.entities.Teacher;
import edu.utvt.attendance.persistence.repositories.TeacherReposistory;

@Service
public class TeacherServiceImplementation implements TeacherService {

	@Autowired
	private TeacherReposistory teacherReposistory;

	@Override
	public Teacher save(Teacher teacher) {
		return this.teacherReposistory.save(teacher);
	}

	@Override
	public void saveAll(List<Teacher> teachers) {
		teacherReposistory.saveAll(teachers);
	}

	@Override
	public Teacher update(String id, Teacher teacher) {

		Optional<Teacher> teacherOptional = null;

		teacherOptional = this.teacherReposistory.findById(id);

		if (teacherOptional.isPresent()) {
			teacherOptional.get().setId(teacher.getId());
			teacherOptional.get().setFirstName(teacher.getFirstName());
			teacherOptional.get().setLastName(teacher.getLastName());
			teacherOptional.get().setEmail(teacher.getEmail());
			teacherOptional.get().setBirthDate(teacher.getBirthDate());
			teacherOptional.get().setStatus(teacher.getStatus());
			this.teacherReposistory.save(teacherOptional.get());
		}
		return teacherOptional.orElse(teacher);

	}

	@Override
	// public List<Teacher> finAll() {
	public List<Teacher> getAll() {
		return this.teacherReposistory.findAll();
	}

	@Override
	public Optional<Teacher> findByid(String id) {
		return this.teacherReposistory.findById(id);
	}

	@Override
	public ResponseEntity<Teacher> deleteById(String id) {

		Optional<Teacher> teacherOptional = null;
		ResponseEntity<Teacher> responseEntity = null;

		teacherOptional = this.teacherReposistory.findById(id);

		if (teacherOptional.isPresent()) {
			this.teacherReposistory.delete(teacherOptional.get());
			responseEntity = ResponseEntity.noContent().build();
		} else {
			responseEntity = ResponseEntity.notFound().build();
		}

		return responseEntity;
	}

	@Override
	public Page<Teacher> get(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("lastName"));
		return this.teacherReposistory.findAll(pageRequest);
	}

}
