package prointern.ProinternApplication.Certification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.Certification.Model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {


	Trainer findByEmail(String email);

}
