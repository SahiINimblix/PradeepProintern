package prointern.ProinternApplication.Internship.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prointern.ProinternApplication.Internship.Model.Course;
import prointern.ProinternApplication.Internship.Model.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findByIdWithLessons(id);
    }
    
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}