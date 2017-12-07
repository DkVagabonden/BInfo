package dk.binfo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="seniority")
public class Seniority {
    @Id
    @Column(name="seniority")
    private long seniority;

    @Basic(optional = false)
    @Column(name = "date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public long getseniority() {
        return seniority;
    }

    public void setseniority(long seniority) {
        this.seniority = seniority;
    }
}
