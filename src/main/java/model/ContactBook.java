package model;

public class ContactBook {
    private int id;
    private String name;
    private String email;
    private String address;
    private long celPhone;

    public ContactBook() {
    }

    public ContactBook(String name, String email, String address, long celPhone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.celPhone = celPhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(long celPhone) {
        this.celPhone = celPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ContactBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", celPhone=" + celPhone +
                '}';
    }
}
