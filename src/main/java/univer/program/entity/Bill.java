package univer.program.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.Set;

@Entity
public class Bill {
    @Id
    private int id;

    @OneToOne
    private User user;

    private Date checkin;
    private Date checkout;

    @OneToMany
    private Set<Room> room;

    public int getPrice() {
        int price = 0;
        for (Room r: room)
            price += r.getPrice();
        long diff = checkout.getTime() - checkin.getTime();
        return Float.floatToIntBits(diff / (3600 * 24));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Set<Room> getRoom() {
        return room;
    }

    public void setRoom(Set<Room> room) {
        this.room = room;
    }
}
