package toystore.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Toy extends AbstractEntity {

    @NotBlank(message = "Name field must not be blank!")
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20 characters!")
    private String name;

    @Size(max = 50, message = "Description too long!")
    private String description;

    @NotNull(message = "Quantity field is required!")
    @Min(value = 0, message = "The quantity must be a positive value!")
    private Integer quantity;

    public Toy() {
    }

    public Toy(String name, String description, Integer quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}
