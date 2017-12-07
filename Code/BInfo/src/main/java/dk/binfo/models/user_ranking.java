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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }
}
