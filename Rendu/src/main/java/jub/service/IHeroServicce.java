package jub.service;

import jub.model.Hero;
import jub.model.Weapon;

import java.util.List;

public interface IHeroServicce {

    List<Hero> getHeroes();
    void init();
    Hero getHero(Integer Id);
    List<Weapon> getWeapons(Integer id);
    Boolean addHero(Hero hero);
    Boolean updateHero(Hero hero);
    Boolean removeHero(Hero hero);
}
