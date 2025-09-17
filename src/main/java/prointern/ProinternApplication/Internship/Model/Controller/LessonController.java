package prointern.ProinternApplication.Internship.Model.Controller;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Course;
import prointern.ProinternApplication.Internship.Model.Lesson;
import prointern.ProinternApplication.Internship.Model.Service.CourseService;
import prointern.ProinternApplication.Internship.Model.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }
    
    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourseId(@PathVariable("courseId") Long courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable("id") Long id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        return lesson.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public String createLesson(@RequestBody Lesson lesson) {
        // Check if course ID is provided
        if (lesson.getCourse() == null || lesson.getCourse().getId() == null) {
            throw new DetailsNotFoundException("Unable to create");
        }
        
        // Get the course from the database
        Optional<Course> course = courseService.getCourseById(lesson.getCourse().getId());
        if (!course.isPresent()) {
        	throw new DetailsNotFoundException("Course with id "+lesson.getCourse().getId()+" is not found.");
        }
        
        // Set the course on the lesson
        lesson.setCourse(course.get());
        
        // Save the lesson
        return lessonService.saveLesson(lesson);
//        return ResponseEntity.ok(savedLesson);
    }
    
    @PutMapping("/{id}")
    public String updateLesson(@PathVariable("id") Long id, @RequestBody Lesson lessonDetails) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        if (lesson.isPresent()) {
            Lesson updatedLesson = lesson.get();
            updatedLesson.setTitle(lessonDetails.getTitle());
            updatedLesson.setDuration(lessonDetails.getDuration());
            
            // Update course if provided
            if (lessonDetails.getCourse() != null && lessonDetails.getCourse().getId() != null) {
                Optional<Course> course = courseService.getCourseById(lessonDetails.getCourse().getId());
                if (course.isPresent()) {
                    updatedLesson.setCourse(course.get());
                }
            }
            
            String result = lessonService.saveLesson(updatedLesson);
            if(result== null) throw new OperationFailedException("Unable to update");
            return "Lesson updated successfully";
        } else {
            throw new DetailsNotFoundException("Lesson not found with id "+id);
        }
    }
    
    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
//        if (lessonService.getLessonById(id).isPresent()) {
//            lessonService.deleteLesson(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    	return lessonService.deleteLesson(id);
    }
}