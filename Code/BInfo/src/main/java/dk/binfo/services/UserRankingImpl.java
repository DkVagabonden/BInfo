package dk.binfo.services;

import dk.binfo.models.Seniority;
import dk.binfo.repositories.RoleRepository;
import dk.binfo.repositories.SeniorityRepository;
import dk.binfo.repositories.UserRankingRepository;
import dk.binfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userRankingService")
public class UserRankingImpl implements UserRankingService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SeniorityRepository seniorityRepository;

    @Autowired
    private UserRankingRepository userRankingRepository;
}
