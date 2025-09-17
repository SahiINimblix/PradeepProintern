package prointern.ProinternApplication.Internship.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Course;
import prointern.ProinternApplication.Internship.Model.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
    	List<Course> listOfCourse = courseRepository.findAll();
    	if(listOfCourse==null) throw new DetailsNotFoundException("No courses found in database");
    	return listOfCourse;
    }
    
    @Transactional(readOnly = true)
    public Optional<Course> getCourseById(Long id) {
    	Optional<Course> course = courseRepository.findByIdWithLessons(id);
    	if(course ==null) throw new DetailsNotFoundException("Course with id "+id+" not found.");
    	return course;
    }
    
    @Transactional
    public String saveCourse(Course course) {
        Course courseDetail = courseRepository.save(course);
        if(courseDetail ==null) throw new OperationFailedException("Unable to save");
    	return "Course details saved successfully";
    }
    
    @Transactional
    public String deleteCourse(Long id) {
    	Course course =courseRepository.findById(id).orElseThrow(()->new DetailsNotFoundException("Detils not found to delete"));
        courseRepository.delete(course);
        if(courseRepository.existsById(id)) throw new OperationFailedException("Unable to delete");
        return "Course deleted successfully.";
    }
}