package dk.binfo.models;


import javax.persistence.*;

@Entity
@IdClass(user_ranking.class)
public class user_ranking {

        @Id
        @ManyToOne
        @JoinColumn(name="email")
        private User user;

        @Id
        @ManyToOne
        @JoinColumn(name="list")
        private Role role;

        @Id
        @ManyToOne
        @JoinColumn(name="seniority")
        private Seniority seniority;


}
