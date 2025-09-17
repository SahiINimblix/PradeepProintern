package prointern.ProinternApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Exception.OperationFailedException;
import prointern.ProinternApplication.Exception.UserAlreadyExistsException;
import prointern.ProinternApplication.Model.Notifier;
import prointern.ProinternApplication.Repository.NotifierRepo;

@Service
public class NotifierService
{
    @Autowired
    NotifierRepo notifierRepo;

    public String saveNotifier(Notifier notifier)
    {
    	if(notifierRepo.findByEmail(notifier.getEmail()) !=null) throw new UserAlreadyExistsException("Already subscribed with this email");
    	Notifier n =notifierRepo.save(notifier);
    	if(n== null) throw new OperationFailedException("Unable to subscribe. Please try again later");
        return "Subscribed successfully for notifications";
    }
}
