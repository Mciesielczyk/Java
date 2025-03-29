package efs.task.collections.entity;

import java.util.Objects;

public class Hero {
    private String name;
    private String heroClass;

    public Hero(String name, String heroClass) {
        this.name = name;
        this.heroClass = heroClass;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return name.equals(hero.name); // Porównanie na podstawie name i heroClass
    }

    @Override
    public int hashCode() {
        return name.hashCode();
        //return Objects.hash(name); // Generowanie hashCode na podstawie name i heroClass
    }

    @Override
    public String toString() {
        return "My name is " + name + " and I am a " + heroClass;
    }
}
