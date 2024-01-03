package toystore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class DeliveryAddress extends AbstractEntity {

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    @Column(name = "street_name")
    private String streetName;

    @NotBlank
    @Column(name = "street_number")
    private Integer streetNumber;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String country, String city, String streetName, Integer streetNumber) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                '}';
    }
}
