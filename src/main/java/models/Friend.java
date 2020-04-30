package models;

public class Friend {
    // test class before implementing database
    public enum UserStatusEnum {ON, BRB, INVIS, OFF}

    private int id;
    private String imie;
    private String nazwisko;
    private UserStatusEnum status;

    public Friend(int id, String imie, String nazwisko, UserStatusEnum status) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

}
