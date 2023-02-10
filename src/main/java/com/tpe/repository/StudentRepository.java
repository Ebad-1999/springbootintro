package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    List<Student> findStudentByLastName(String lastName);

    //JPQL
    @Query("SELECT s FROM Student s WHERE s.grade=:pGrade")  //89
    List<Student> findStudentByGrade(@Param("pGrade")Integer grade);

    @Query(value = "SELECT * FROM t_student s WHERE s.grade=:pGrade", nativeQuery = true)
    List<Student> findStudentByGradeWithSQL(@Param("pGrade") Integer grade);


    @Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student  s WHERE s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);

}
