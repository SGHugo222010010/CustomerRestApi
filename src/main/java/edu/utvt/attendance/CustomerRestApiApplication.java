package edu.utvt.attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

import com.github.javafaker.Faker;

import edu.utvt.attendance.persistence.common.StatusType;
import edu.utvt.attendance.persistence.common.StudentType;
import edu.utvt.attendance.persistence.entities.Student;
import edu.utvt.attendance.persistence.entities.Teacher;
import edu.utvt.attendance.persistence.repositories.StudentRepository;
import edu.utvt.attendance.persistence.service.TeacherService;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class CustomerRestApiApplication implements CommandLineRunner{
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
    private TeacherService teacherService;

	public static void main(String[] args) {
		SpringApplication.run(CustomerRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populateStudents();
        populateTeachers();
	}
		
	 private void populateStudents() {
		int startElements = 0;
		int totalElements = 10000;
		Faker faker = new Faker();
		List<Student> students = new ArrayList<>();
		startElements = (int) this.repository.count();
		
		for (int i = startElements; i < totalElements; i++) {
			Student student = null;
			 student = new Student(faker.idNumber().ssnValid(), faker.name().firstName(),faker.name().lastName(), 
					 faker.name().username() + "@gmail.com", StudentType.FULL_TIME, new Date(ThreadLocalRandom.current().nextInt() * i), null);
			students.add(student);
		}
		this.repository.saveAll(students);
	}
	 
	 private void populateTeachers() {
	        List<Teacher> teachers = new ArrayList<>();

	        teachers.add(new Teacher("1", "Pamela", "Rosales", "pamela@utvtol.com", StatusType.ON, new Date(), null));
	        teachers.add(new Teacher("2", "Roberto", "Camacho", "vinicio@utvtol.com", StatusType.ON, new Date(), null));
	        teachers.add(new Teacher("3", "Cruz", "Rosales", "cruz.johnson@utvtol.com", StatusType.OFF, new Date(), null));
	        teachers.add(new Teacher("4", "Miguel", "Islas", "miguel@utvtol.com", StatusType.ON, new Date(), null));
	        teachers.add(new Teacher("5", "Angel", "Perez", "patricia@utvtol.com", StatusType.OFF, new Date(), null));
	        teachers.add(new Teacher("6", "Jorge", "Almeida", "almeida@utvtol.com", StatusType.ON, new Date(), null));
	        teachers.add(new Teacher("7", "Marcos", "Sánchez", "marcos@utvtol.com", StatusType.ON, new Date(), null));
	        teachers.add(new Teacher("8", "Adglaé", "Dávila", "patricia.brown@example.com", StatusType.OFF, new Date(), null));
	        	        
	        this.teacherService.saveAll(teachers); // Utiliza el servicio para guardar maestros
	    }


}
