package dk.binfo.repositories;

import dk.binfo.models.List_and_seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("homeRepository")
public interface HomeRepository extends JpaRepository<List_and_seniority, String> {
}
