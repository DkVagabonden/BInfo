package dk.binfo.repositories;

import dk.binfo.models.UserRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRankingRepository")
public interface UserRankingRepository extends JpaRepository<UserRanking, Long> {
}
