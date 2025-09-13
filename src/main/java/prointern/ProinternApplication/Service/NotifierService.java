package prointern.ProinternApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prointern.ProinternApplication.Exception.DetailsNotFoundException;
import prointern.ProinternApplication.Model.Notifier;
import prointern.ProinternApplication.Repository.NotifierRepo;

@Service
public class NotifierService
{
    @Autowired
    NotifierRepo notifierRepo;

    public Notifier saveNotifier(Notifier notifier)
    {
    	Notifier n =notifierRepo.save(notifier);
    	if(n== null) throw new DetailsNotFoundException("Unable to save.");
        return n;
    }
}
