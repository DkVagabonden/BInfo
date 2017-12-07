package dk.binfo.services;


import dk.binfo.models.List_and_seniority;
import dk.binfo.models.Seniority;
import dk.binfo.models.User;
import dk.binfo.repositories.HomeRepository;
import dk.binfo.repositories.SeniorityRepository;
import dk.binfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SeniorityService")
public class SeniorityImpl implements SeniorityService {


    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List_and_seniority findSenority(String email)
    {
       List_and_seniority Senority =  homeRepository.findOne(email);
       System.out.println(Senority.getEmail() + Senority.getSeniority() + Senority.getList_priority());
       return Senority;
    }

    @Override
    @Transactional
    public List<List_and_seniority> findAll() {
        return homeRepository.findAll();
    }
}
