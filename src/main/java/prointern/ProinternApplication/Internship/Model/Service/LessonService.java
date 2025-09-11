package prointern.ProinternApplication.Internship.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prointern.ProinternApplication.Internship.Model.Lesson;
import prointern.ProinternApplication.Internship.Model.Repository.LessonRepository;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    
    @Transactional(readOnly = true)
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAllWithCourse();
    }
    
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }
    
    @Transactional(readOnly = true)
    public Optional<Lesson> getLessonById(Long id) {
        Lesson lesson = lessonRepository.findByIdWithCourse(id);
        return Optional.ofNullable(lesson);
    }
    
    @Transactional
    public Lesson saveLesson(Lesson lesson) {
        Lesson savedLesson = lessonRepository.save(lesson);
        // Force the reload with the course
        return lessonRepository.findByIdWithCourse(savedLesson.getId());
    }
    
    @Transactional
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}