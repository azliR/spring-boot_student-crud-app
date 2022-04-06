package id.ac.uin.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotEmpty(message = "First name is required")
  @NotNull(message = "FirstName can not be null!!")
  @Column(nullable = false, name = "first_name")
  private String firstName;

  @NotEmpty(message = "Last name is required")
  @NotNull(message = "LastName can not be null!!")
  @Column(nullable = false, name = "last_name")
  private String lastName;

  @NotNull(message = "Please enter birth date")
  @Past(message = "Birth date should be less than current date!!")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;

  @NotEmpty(message = "Email is required")
  @Email
  @Column(nullable = false, name = "email", unique = true)
  private String email;

  @NotEmpty(message = "Phone number is required")
  @NotNull(message = "Phone can not be null!!")
  @Size(max = 13, min = 10, message = "Mobile number should be of 10 digits")
  @Column(nullable = false, unique = true)
  private String phone;

  @NotEmpty(message = "Jurusan is required")
  @Column(nullable = false, unique = true)
  private String jurusan;
}
