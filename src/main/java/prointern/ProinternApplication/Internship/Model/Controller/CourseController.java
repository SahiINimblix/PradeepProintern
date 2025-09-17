package prointern.ProinternApplication.Internship.Model.Controller;

import prointern.ProinternApplication.Internship.Model.Service.CourseService;
import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public String createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
    
    @PutMapping("/{id}")
    public String updateCourse(@PathVariable("id") Long id, @RequestBody Course courseDetails) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            Course updatedCourse = course.get();
            updatedCourse.setTitle(courseDetails.getTitle());
            updatedCourse.setDuration(courseDetails.getDuration());
            updatedCourse.setLessonsCount(courseDetails.getLessonsCount());
            updatedCourse.setPrice(courseDetails.getPrice());
            updatedCourse.setCompletedLessons(courseDetails.getCompletedLessons());
            String result = courseService.saveCourse(updatedCourse);
            if(result== null) throw new OperationFailedException("Unable to update");
            return "Course details updated successfully";
        } else {
            throw new DetailsNotFoundException("Unable to update");
        }
    }
    
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
//        if (courseService.getCourseById(id).isPresent()) {
//            courseService.deleteCourse(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    	return courseService.deleteCourse(id);
    }
}
