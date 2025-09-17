package prointern.ProinternApplication.Internship.Model.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Internship.Model.Task;
import prointern.ProinternApplication.Internship.Model.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Service
public class CodeExecutionService {
    @Autowired
    private TaskRepository taskRepository;
    
    public String executeCode(String code, String language, Long taskId) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new DetailsNotFoundException("Task not found"));
            if ("java".equalsIgnoreCase(language)) {
                return executeJavaCode(code, task);
            } else if ("python".equalsIgnoreCase(language)) {
                return executePythonCode(code, task);
            } else {
                return "Unsupported language: " + language;
            }
        } catch (Exception e) {
            throw new DetailsNotFoundException("Exception:"+e);
        }
    }
    
    private String executeJavaCode(String code, Task task) {
        try {
            // Write code to a temporary file
            File javaFile = new File("Solution.java");
            try (FileWriter writer = new FileWriter(javaFile)) {
                writer.write(code);
            }
            
            // Compile the Java code
            Process compileProcess = Runtime.getRuntime().exec("javac Solution.java");
            compileProcess.waitFor(10, TimeUnit.SECONDS);
            
            if (compileProcess.exitValue() != 0) {
                try (BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(compileProcess.getErrorStream()))) {
                    StringBuilder error = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        error.append(line).append("\n");
                    }
                    return "Compilation Error:\n" + error.toString();
                }
            }
            
            // Execute the compiled Java code
            Process runProcess = Runtime.getRuntime().exec("java Solution");
            
            // Provide input if needed based on task
            if (task.getId() == 1) { // Task 01: Reverse string
                try (OutputStream stdin = runProcess.getOutputStream();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
                    writer.write("Hello World");
                    writer.newLine();
                    writer.flush();
                }
            }
            
            boolean finished = runProcess.waitFor(2, TimeUnit.MINUTES);
            if (!finished) {
                runProcess.destroy();
                return "Execution timed out.";
            }
            
            // Read output
            try (BufferedReader outputReader = new BufferedReader(
                    new InputStreamReader(runProcess.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(runProcess.getErrorStream()))) {
                
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = outputReader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                
                String errors = errorReader.readLine();
                if (errors != null) {
                    return "Runtime Error:\n" + errors;
                }
                
                // Validate output based on task
                String result = output.toString().trim();
                
                if (task.getId() == 1) {
                    // Extract just the reversed part (after the colon if present)
                    String reversedPart = result;
                    if (result.contains(":")) {
                        reversedPart = result.substring(result.indexOf(":") + 1).trim();
                    }
                    
                    if (reversedPart.equals("dlroW olleH")) {
                        return "SUCCESS: Output matches expected result!\n" + result;
                    } else {
                        throw new DetailsNotFoundException("FAILED: Output does not match.\nExpected: dlroW olleH\nYour output: " + result);
                    }
                }
                
                return "Execution completed:\n" + result;
            }
        } catch (IOException | InterruptedException e) {
            throw new OperationFailedException("Execution error: " + e.getMessage());
        } finally {
            // Clean up temporary files
            File javaFile = new File("Solution.java");
            if (javaFile.exists()) {
                javaFile.delete();
            }
            
            File classFile = new File("Solution.class");
            if (classFile.exists()) {
                classFile.delete();
            }
        }
    }
    
    private String executePythonCode(String code, Task task) {
        try {
            // Write code to a temporary file
            File pythonFile = new File("solution.py");
            try (FileWriter writer = new FileWriter(pythonFile)) {
                writer.write(code);
            }
            
            // Execute the Python code
            Process runProcess = Runtime.getRuntime().exec("python solution.py");
            
            boolean finished = runProcess.waitFor(2, TimeUnit.MINUTES);
            if (!finished) {
                runProcess.destroy();
                return "Execution timed out.";
            }
            
            // Read output
            try (BufferedReader outputReader = new BufferedReader(
                    new InputStreamReader(runProcess.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(runProcess.getErrorStream()))) {
                
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = outputReader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                
                String errors = errorReader.readLine();
                if (errors != null) {
                    return "Runtime Error:\n" + errors;
                }
                
                // For simplicity, we'll just return the output
                return "Execution completed:\n" + output.toString();
            }
        } catch (IOException | InterruptedException e) {
            throw new OperationFailedException("Execution error: " + e.getMessage());
        } finally {
            // Clean up temporary files
            File pythonFile = new File("solution.py");
            if (pythonFile.exists()) {
                pythonFile.delete();
            }
        }
    }
}