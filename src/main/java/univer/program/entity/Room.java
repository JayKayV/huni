package univer.program.entity;

import javax.persistence.*;

@Entity
public class Room {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String type;
    private int bed_cnt;
    private int price;

    @org.jetbrains.annotations.Contract(pure = true)
    public Room() {
    }

    public Room(int id, int number, int hotel_id, String type, int bed_cnt, int price, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.bed_cnt = bed_cnt;
        this.price = price;
        this.hotel = hotel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBed_cnt() {
        return bed_cnt;
    }

    public void setBed_cnt(int bed_cnt) {
        this.bed_cnt = bed_cnt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
