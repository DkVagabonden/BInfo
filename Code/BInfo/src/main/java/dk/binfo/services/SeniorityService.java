package dk.binfo.services;

import dk.binfo.models.List_and_seniority;
import dk.binfo.models.Seniority;
import dk.binfo.models.User;

import java.util.List;

public interface SeniorityService {

    List_and_seniority findSenority(String email);
    List<List_and_seniority> findAll();

}
