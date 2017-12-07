package dk.binfo.repositories;

import dk.binfo.models.user_ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRankingRepository")
public interface UserRankingRepository extends JpaRepository<user_ranking, String> {
}
