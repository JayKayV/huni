package univer.program.entity;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String country;
    private String city;
    private String address;
    private String description;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private String phone_number;

    public byte[] getImage() {
        return image;
    }

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private byte[] image;

    public Hotel() {}
    public Hotel(String name, String country, String city,
                 String address, String phone_number, String description) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone_number = phone_number;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setData(String name, String country, String city,
                        String address, String phone_number, String description) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone_number = phone_number;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
