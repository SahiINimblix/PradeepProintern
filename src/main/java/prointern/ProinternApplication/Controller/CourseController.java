package prointern.ProinternApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Model.Course;
import prointern.ProinternApplication.Service.CourseService;

@RestController
@RequestMapping("api/pay")
public class CourseController
{
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<Course> getAllCourses()
    {
        List<Course> allData = courseService.getAllData();
        return allData;
    }

    @GetMapping
    public Course getStudentById(@RequestParam String id)
    {
        return courseService.getCourseById(id);
    }

}
