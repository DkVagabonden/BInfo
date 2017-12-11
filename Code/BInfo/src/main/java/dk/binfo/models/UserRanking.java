package dk.binfo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_ranking")
public class UserRanking {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "list")
    private int list;

    @Column(name = "seniority")
    private long seniority;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getList() {
        return list;
    }

    public void setList(int list) {
        this.list = list;
    }


    public long getSeniority() {
        return seniority;
    }

    public void setSeniority(long seniority) {
        this.seniority = seniority;
    }
}
