package jub.model;

import javax.persistence.*;

@Entity
public class Weapon {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Weapon(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Weapon(){
        super();
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
