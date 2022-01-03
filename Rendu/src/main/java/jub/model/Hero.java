package jub.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hero {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "kind_id")
    private Kind kind;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn( name = "hero_id")
    private List<Weapon> weapons;

    public Hero(Integer id, String name, String slug, Kind kind) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.kind = kind;
        this.weapons = new ArrayList<>();
    }

    public Hero(){
        super();
        weapons = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
