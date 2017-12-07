package dk.binfo.models;

import javafx.concurrent.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="seniority")
public class Seniority {
    @Id
    @OneToMany(mappedBy="seniority", cascade = CascadeType.ALL)
    private Set<user_ranking> ranking;

    @Basic(optional = false)
    @Column(name = "date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


}
