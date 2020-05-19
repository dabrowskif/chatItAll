package hibernate.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    @Id
    @Column(name = "user1_id")
    private int user1_id;

    @Id
    @Column(name = "user2_id")
    private int user2_id;

}
