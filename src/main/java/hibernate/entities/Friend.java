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

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                '}';
    }

}
