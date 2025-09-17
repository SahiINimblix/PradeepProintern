package prointern.ProinternApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prointern.ProinternApplication.Model.Notifier;
import prointern.ProinternApplication.Service.NotifierService;

@RestController
@RequestMapping("api/subscribe")
public class NotifierController
{
    @Autowired
    private NotifierService notifierService;

    @PostMapping("/save")
    public String createNotifier(@RequestBody Notifier notifier)
    {
        return notifierService.saveNotifier(notifier);
    }
}
