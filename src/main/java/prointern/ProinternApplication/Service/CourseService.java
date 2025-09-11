package prointern.ProinternApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Model.Course;
import prointern.ProinternApplication.Repository.CourseRepo;

@Service
public class CourseService
{
    @Autowired
    CourseRepo courseRepo;

    public List<Course> getAllData()
    {
        List<Course> list = courseRepo.findAll();
        return list;
    }

    public Course getCourseById(String id)
    {
        Optional<Course> byId = courseRepo.findById(id);
        Course xx = byId.get();
        return xx;
    }
}
