package dk.binfo.repositories;

import dk.binfo.models.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("seniorityRepository")
public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
}
