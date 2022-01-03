package jub.service;

import jub.model.Brewery;
import jub.model.Hero;
import jub.model.Kind;
import jub.model.Weapon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HeroService implements IHeroServicce{

    private static IHeroServicce instance= new HeroService();
    private EntityManagerFactory emf;

    private HeroService() {
        emf  = Persistence.createEntityManagerFactory("PU");
        init();
    }


    public static IHeroServicce getInstance(){
        return instance;
    }


    @Override
    public void init() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Kind k1 = new Kind(1, "Comics"),
                k2 = new Kind(2, "BD");

        em.persist(k1);
        em.persist(k2);

        em.flush();

        Hero h1 = new Hero(4, "Clark kent", "Superman", k1),
                h2 = new Hero(5, "Thor","Roi Odin", k2);


        em.persist(h1);
        em.persist(h2);


        em.flush();
        em.getTransaction().commit();

    }

    @Override
    public List<Hero> getHeroes() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select h from Hero h", Hero.class).getResultList();
    }

    @Override
    public Hero getHero(Integer Id) {

        EntityManager em = emf.createEntityManager();

        Hero hero = em.find(Hero.class, Id);

        return hero;
    }

    @Override
    public List<Weapon> getWeapons(Integer id) {

        EntityManager em = emf.createEntityManager();

        Hero hero = em.find(Hero.class, id);

        if(hero != null){
            TypedQuery<Weapon> weapons = em.createQuery("select w from Weapon where w.hero_id = " + id, Weapon.class);
            System.out.println(weapons.toString());
            return (List<Weapon>) weapons;
        }

        return null;
    }

    @Override
    public Boolean addHero(Hero hero) {
        return null;
    }

    @Override
    public Boolean updateHero(Hero hero) {
        return null;
    }

    @Override
    public Boolean removeHero(Hero hero) {
        return null;
    }
}
