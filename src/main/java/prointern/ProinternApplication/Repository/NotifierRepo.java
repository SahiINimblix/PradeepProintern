package prointern.ProinternApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prointern.ProinternApplication.Model.Notifier;

@Repository
public interface NotifierRepo extends JpaRepository<Notifier,String>
{
}
