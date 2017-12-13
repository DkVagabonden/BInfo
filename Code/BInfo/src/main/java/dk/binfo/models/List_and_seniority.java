package dk.binfo.models;


import javax.persistence.*;

@Entity
@Table(name = "List_and_seniority")
public class List_and_seniority {

    @Column(name = "Email")
    private String email;

    @Column(name = "list_priority")
    private String list_priority;

    @Override
    public String toString() {
        return "List_and_seniority{" +
                "email='" + email + '\'' +
                ", list_priority='" + list_priority + '\'' +
                ", seniority='" + seniority + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seniority")
    private String seniority;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getList_priority() {
        return list_priority;
    }

    public void setList_priority(String list_priority) {
        this.list_priority = list_priority;
    }

    public String getSeniority() {
        return seniority;
    }
}
