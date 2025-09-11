package prointern.ProinternApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prointern.ProinternApplication.Model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,String>{
}
