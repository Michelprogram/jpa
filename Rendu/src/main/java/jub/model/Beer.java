package jub.model;

import javax.persistence.*;

@Entity
public class Beer {
    @Id
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double alcoholByVolume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;

    public Beer(Integer id, String name, Double alcoholByVolume, Brewery brewery) {
        this.id = id;
        this.name = name;
        this.alcoholByVolume = alcoholByVolume;
        this.brewery = brewery;
    }


    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholByVolume=" + alcoholByVolume +
                ", brewery=" + brewery.getId() +
                '}';
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

    public Double getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public void setAlcoholByVolume(Double alcoholByVolume) {
        this.alcoholByVolume = alcoholByVolume;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }
}
