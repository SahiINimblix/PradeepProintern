package prointern.ProinternApplication.Internship.Model.Controller;

import prointern.ProinternApplication.Internship.Model.ExecutionRequest;
import prointern.ProinternApplication.Internship.Model.Service.CodeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execute")
public class CodeExecutionController {
	@Autowired    
	private CodeExecutionService executionService;
    
    @PostMapping
    public String executeCode(@RequestBody ExecutionRequest request) {
        return executionService.executeCode(request.getCode(), request.getLanguage(), request.getTaskId());
    }
}