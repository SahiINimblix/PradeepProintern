package prointern.ProinternApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Model.Notifier;
import prointern.ProinternApplication.Repository.NotifierRepo;

@Service
public class NotifierService
{
    @Autowired
    NotifierRepo notifierRepo;

    public Notifier saveNotifier(Notifier notifier)
    {
        return notifierRepo.save(notifier);
    }
}
