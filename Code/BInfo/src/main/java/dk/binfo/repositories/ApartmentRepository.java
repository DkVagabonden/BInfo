package dk.binfo.repositories;

import dk.binfo.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Vagabonden
 */
@Repository("apartmentRepository")
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

}