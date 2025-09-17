package prointern.ProinternApplication.Internship.Model.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Task;
import prointern.ProinternApplication.Internship.Model.Repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> getAllTasks() {
    	List<Task> listOfTask = taskRepository.findAll();
    	if(listOfTask == null) throw new DetailsNotFoundException("No tasks found in database.");
    	return listOfTask;
    }
    
    public Optional<Task> getTaskById(Long id) {
    	Optional<Task> task = taskRepository.findById(id);
    	if(task == null) throw new DetailsNotFoundException("Task with id "+id+" is not found.");
    	return task;
    }
    
    public String saveTask(Task task) {
    	Task task1 = taskRepository.save(task);
    	if(task1 == null) throw new OperationFailedException("Unable to save.");
    	return "Task saved uccessfully";
    }
    
    public String deleteTask(Long id) {
    	Task task = taskRepository.findById(id).orElseThrow(() -> new DetailsNotFoundException("Task details not found."));
        taskRepository.delete(task);
        if(taskRepository.existsById(id)) throw new OperationFailedException("Unable to delete");
        return "Task deleted successfully";
    }
}
