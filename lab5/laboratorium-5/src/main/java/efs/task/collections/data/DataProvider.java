package efs.task.collections.data;
 
 import efs.task.collections.entity.Hero;
 import efs.task.collections.entity.Town;

 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 
 public class DataProvider {
 
     public static final String DATA_SEPARATOR = ",";
 
     // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
     //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
     //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
     //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
     public List<Town> getTownsList() {
         List<Town> towns = new ArrayList<Town>();
         String[] A = Data.baseTownsArray;
         String[] parts = new String[0];
         for(String s :A){
           parts = s.split(DATA_SEPARATOR);
             List<String> hero = new ArrayList<>();
             for(int i=1; i<3;i++){
                 hero.add(parts[i].trim());
             }
             Town town = new Town(parts[0].trim(), hero);
             towns.add(town);
        }
         return towns;
     }
 
     //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
     public List<Town> getDLCTownsList() {
         List<Town> towns = new ArrayList<Town>();
         String[] A = Data.dlcTownsArray;
         String[] parts = new String[0];
         for(String s :A){
             parts = s.split(DATA_SEPARATOR);
             List<String> hero = new ArrayList<>();
             for(int i=1; i<3;i++){
                 hero.add(parts[i].trim());
             }
             Town town = new Town(parts[0].trim(), hero);
             towns.add(town);
         }

         return towns;
     }
 
     //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
     // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
     // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
     // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
     public Set<Hero> getHeroesSet() {
         Set<Hero> hero = new HashSet<Hero>();
         String[] A = Data.baseCharactersArray;
         String[] parts;
         for(String s :A){
             parts = s.split(DATA_SEPARATOR);
             Hero hero1 = new Hero(parts[0].trim(), parts[1].trim());
             hero.add(hero1);
         }
         return hero;
     }
 
     //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
     public Set<Hero> getDLCHeroesSet() {
         Set<Hero> hero = new HashSet<Hero>();
         String[] A = Data.dlcCharactersArray;
         String[] parts;
         for(String s :A){
             parts = s.split(DATA_SEPARATOR);

             Hero hero1 = new Hero(parts[0].trim(), parts[1].trim());
             hero.add(hero1);
         }


         return hero;
     }
 }