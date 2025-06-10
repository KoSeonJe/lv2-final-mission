package finalmission.repository;

import finalmission.domain.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    boolean existsByEmailAndPassword(String email, String password);
}
