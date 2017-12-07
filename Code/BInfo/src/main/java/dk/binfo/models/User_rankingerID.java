package dk.binfo.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class User_rankingerID implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "email")
    private String email;


    public User_rankingerID(){};

    public User_rankingerID(String email){
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
