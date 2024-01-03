package toystore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "`order`")
public class Order extends AbstractEntity {
    @OneToOne
    private Toy toy;

    @Min(value = 1, message = "Selected quantity must be at least 1!")
    private Integer quantity;

    @OneToOne
    private User customer;

    @OneToOne
    @Valid
    private DeliveryAddress deliveryAddress;

    public Order() {
    }

    public Order(Toy toy, Integer quantity, User customer, DeliveryAddress deliveryAddress) {
        this.toy = toy;
        this.quantity = quantity;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
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

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "toy=" + toy +
                ", quantity=" + quantity +
                ", customer=" + customer +
                ", deliveryAddress=" + deliveryAddress +
                '}';
    }
}
