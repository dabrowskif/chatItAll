package hibernate.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    public enum StatusEnum {ON, BRB, INVIS, OFF}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "number")
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public StatusEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_info_id")
    private UserInfo usersInfoId;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "friend",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id"))
    private List<User> friends = new ArrayList<>();



    public User() {

    }

    public User(String login, String password, int number, StatusEnum status) {
        this.login = login;
        this.password = password;
        this.number = number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public UserInfo getUsersInfoId() {
        return usersInfoId;
    }

    public void setUsersInfoId(UserInfo usersInfoId) {
        this.usersInfoId = usersInfoId;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User userToAdd) {
        if(friends == null) {
            friends = new ArrayList<>();
        }

        friends.add(userToAdd);
        userToAdd.friends.add(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", number=" + number +
                ", status='" + status + '\'' +
                ", usersInfoId=" + usersInfoId +
                '}';
    }
}
