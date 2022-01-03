package jub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
 @Entity
 public class Brewery {
        @Id
        private Integer id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private String address;
        @Column(nullable = false)
        private String city;
        @Column(nullable = false)
        private String country;
        @Column(nullable = false)
        private String description;

     public Brewery(Integer id, String name, String address, String city, String country, String description) {
         this.id = id;
         this.name = name;
         this.address = address;
         this.city = city;
         this.country = country;
         this.description = description;
     }

     public Brewery() {
         super();
     }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Brewerie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brewery brewerie = (Brewery) o;
        return Objects.equals(id, brewerie.id) && Objects.equals(name, brewerie.name) && Objects.equals(address, brewerie.address) && Objects.equals(city, brewerie.city) && Objects.equals(country, brewerie.country) && Objects.equals(description, brewerie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, country, description);
    }
}
