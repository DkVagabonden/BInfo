package dk.binfo.services;

import dk.binfo.models.User;

import java.util.List;
import java.net.URL;

/**
 * The Interface for creating waitinglists for displaying on screen
 * or in a PDF
 *
 * @author jensbackvall
 */

public interface ListService {
    void generateSingleApartmentPDF(int listLength, int apartmentNumber, String filePath);
    List<User> generateList(int length, int priority);
    List<User> generateSingleApartmentList(int length, int ApartmentId);
}