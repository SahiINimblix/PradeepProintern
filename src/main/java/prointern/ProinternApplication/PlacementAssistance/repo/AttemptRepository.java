package prointern.ProinternApplication.PlacementAssistance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prointern.ProinternApplication.PlacementAssistance.entities.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findByUserId(Long userId);
}
