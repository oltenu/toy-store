package toystore.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "`orders`")
public class Order extends AbstractEntity {
    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Toy toy;

    @Min(value = 1, message = "Selected quantity must be at least 1!")
    private Integer quantity;

    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private User customer;

    public Order() {
    }

    public Order(Toy toy, Integer quantity, User customer) {
        this.toy = toy;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "toy=" + toy +
                ", quantity=" + quantity +
                ", customer=" + customer +
                '}';
    }
}
