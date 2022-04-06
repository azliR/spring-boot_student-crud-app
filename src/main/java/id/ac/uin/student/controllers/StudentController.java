package id.ac.uin.student.controllers;

import id.ac.uin.student.entities.Student;
import id.ac.uin.student.exceptions.StudentNotFoundException;
import id.ac.uin.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
  private final StudentService studentservice;

  @Autowired
  public StudentController(StudentService studentservice) {
    this.studentservice = studentservice;
  }

  @GetMapping
  public List<Student> getAllStudents() {
    return studentservice.getAllStudents();
  }

  @GetMapping(value = "/{id}")
  public Student getStudentById(@PathVariable("id") @Min(1) Long id) {
    Student std =
        studentservice
            .findById(id)
            .orElseThrow(
                () ->
                    new StudentNotFoundException("Student dengan id " + id + " tidak ditemukan!"));
    return std;
  }

  @PostMapping
  public Student addStudent(@Valid @RequestBody Student std) {
    return studentservice.save(std);
  }

  @PutMapping(value = "/{id}")
  public Student updateStudent(
      @PathVariable("id") @Min(1) Long id, @Valid @RequestBody Student newStd) {
    Student student =
        studentservice
            .findById(id)
            .orElseThrow(
                () ->
                    new StudentNotFoundException("Student dengan id " + id + " tidak ditemukan!"));
    student.setFirstName(newStd.getFirstName());
    student.setLastName(newStd.getLastName());
    student.setEmail(newStd.getEmail());
    student.setPhone(newStd.getPhone());
    return studentservice.save(student);
  }

  @DeleteMapping(value = "/{id}")
  public String deleteStudent(@PathVariable("id") @Min(1) Long id) {
    Student std =
        studentservice
            .findById(id)
            .orElseThrow(
                () ->
                    new StudentNotFoundException("Student dengan id " + id + " tidak ditemukan!"));
    studentservice.deleteById(std.getId());
    return "Student dengan ID :" + id + " berhasil dihapus";
  }
}
