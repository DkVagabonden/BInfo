package dk.binfo.services;

import dk.binfo.models.Apartment;

import java.util.List;
/**
 * @author Vagabonden
 */
public interface ApartmentService {
    Apartment delete(int id);
    List<Apartment> findAll();
    Apartment update(Apartment apartment);
    Apartment findById(int id);

    //Saves the Apartment setters.
    void saveApartment(Apartment apartment);
}
