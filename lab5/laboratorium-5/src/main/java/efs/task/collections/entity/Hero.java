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
        if (this == o) return true; // jesli porownujemy obiekt ze samym soba
        Hero hero = (Hero) o; //konwertujemy o na klase
        boolean name1 = this.name.equals(hero.name);
        boolean class1 = this.heroClass.equals(hero.heroClass);
        return (name1 && class1); // porównanie również heroClass
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, heroClass);
    }

    @Override
    public String toString() {
        return "My name is " + name + " and I am a " + heroClass;
    }
}
