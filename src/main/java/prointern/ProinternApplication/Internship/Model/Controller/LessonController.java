package prointern.ProinternApplication.Internship.Model.Controller;

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
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        return lesson.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        // Check if course ID is provided
        if (lesson.getCourse() == null || lesson.getCourse().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Get the course from the database
        Optional<Course> course = courseService.getCourseById(lesson.getCourse().getId());
        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Set the course on the lesson
        lesson.setCourse(course.get());
        
        // Save the lesson
        Lesson savedLesson = lessonService.saveLesson(lesson);
        return ResponseEntity.ok(savedLesson);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lessonDetails) {
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
            
            return ResponseEntity.ok(lessonService.saveLesson(updatedLesson));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        if (lessonService.getLessonById(id).isPresent()) {
            lessonService.deleteLesson(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}