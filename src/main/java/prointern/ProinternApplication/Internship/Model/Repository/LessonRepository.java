package prointern.ProinternApplication.Internship.Model.Repository;

import prointern.ProinternApplication.Internship.Model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseId(Long courseId);
    
    @Query("SELECT l FROM Lesson l JOIN FETCH l.course WHERE l.id = :id")
    Lesson findByIdWithCourse(@Param("id") Long id);
    
    @Query("SELECT l FROM Lesson l JOIN FETCH l.course")
    List<Lesson> findAllWithCourse();
}