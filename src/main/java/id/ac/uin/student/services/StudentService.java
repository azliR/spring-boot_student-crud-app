package id.ac.uin.student.services;

import id.ac.uin.student.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  List<Student> getAllStudents();

  Optional<Student> findById(Long id);

  Optional<Student> findByEmail(String email);

  Student save(Student std);

  void deleteById(Long id);
}
