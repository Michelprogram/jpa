package jub.service;

import jub.model.Brewery;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;



public class BreweriesService implements IBreweriesService {
    private static IBreweriesService instance= new BreweriesService();
    private  EntityManagerFactory emf;

    private BreweriesService() {
        emf  = Persistence.createEntityManagerFactory("PU");
        init();
    }


    public static IBreweriesService getInstance(){
        return instance;
    }

    @Override
    public void init() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Brewery").executeUpdate();
        Brewery brewery1 = new Brewery(1,"brewery1","address1","city1","fr","desc1" );
        Brewery brewery2 = new Brewery(2,"brewery2","address2","city2","fr","desc2" );
        em.persist(brewery1);
        em.persist(brewery2);
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public List<Brewery> getBreweries() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select b from Brewery b", Brewery.class).getResultList();
    }

    @Override
    public Brewery getBrewery(Integer id) {
        EntityManager em = emf.createEntityManager();
        Brewery brewery =em.find(Brewery.class,id);
        return brewery;
    }

    @Override
    public Boolean addBrewery(Brewery brewery) {
        Brewery brewery1 = getBrewery(brewery.getId());
        if (brewery1 == null) {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(brewery);
            em.flush();
            em.getTransaction().commit();
            return  true;
        }
        return false;
    }

    @Override
    public Boolean updateBrewery(Brewery brewery) {
        if (!removeBrewery(brewery))
            return false;
        return addBrewery(brewery);
    }

    @Override
    public Boolean removeBrewery(Brewery brewery) {
        Brewery brewery1 = getBrewery(brewery.getId());
        if (brewery1 == null) {
            return false;
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(brewery);
        em.remove(brewery);
        em.getTransaction().commit();
        return true;
    }

}
