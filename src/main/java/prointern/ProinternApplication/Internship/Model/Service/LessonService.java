package prointern.ProinternApplication.Internship.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Lesson;
import prointern.ProinternApplication.Internship.Model.Repository.LessonRepository;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    
    @Transactional(readOnly = true)
    public List<Lesson> getAllLessons() {
    	List<Lesson> listOfLesson = lessonRepository.findAllWithCourse();
    	if(listOfLesson == null) throw new DetailsNotFoundException("No lessons found in database.");
    	return listOfLesson;
    }
    
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByCourseId(Long courseId) {
    	List<Lesson> listOfLesson = lessonRepository.findByCourseId(courseId);
    	if(listOfLesson == null) throw new DetailsNotFoundException("Lesson with course id "+courseId+" is not found.");
    	return listOfLesson;
    }
    
    @Transactional(readOnly = true)
    public Optional<Lesson> getLessonById(Long id) {
    	Optional<Lesson> lesson = Optional.ofNullable(lessonRepository.findByIdWithCourse(id));
        if(lesson == null) throw new DetailsNotFoundException("Lesson with id "+id+" is not found.");
    	return lesson;
    }
    
    @Transactional
    public String saveLesson(Lesson lesson) {
        Lesson savedLesson = lessonRepository.save(lesson);
        if(savedLesson == null) throw new OperationFailedException("Unable to save");
        // Force the reload with the course
//        return lessonRepository.findByIdWithCourse(savedLesson.getId());
        return "Lesson saved successfully";
    }
    
    @Transactional
    public String deleteLesson(Long id) {
    	Optional<Lesson> lesson = lessonRepository.findById(id);
    	 if(lesson == null) throw new DetailsNotFoundException("Lesson with id "+id+" is not found to delete.");
        lessonRepository.deleteById(id);
        if(lessonRepository.existsById(id)) throw new OperationFailedException("Unable to delete");
        return "Lesson deleted successfully.";
    }
}