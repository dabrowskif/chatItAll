package myclasses;

public class Friend {
    // test class before implementing database
    private int id;
    private String imie;
    private String nazwisko;
    private String status;

    public Friend(int id, String imie, String nazwisko, String status) {
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

    public String getStatus() {
        return status;
    }

}
