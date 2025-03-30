package efs.task.collections.game;
 
 import efs.task.collections.data.DataProvider;
 import efs.task.collections.entity.Hero;
 import efs.task.collections.entity.Town;

 import java.util.*;

public class GameLobby {

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC() {
        List<Town> dlcTowns = dataProvider.getDLCTownsList();
        Set<Hero> dlcHeroes = dataProvider.getDLCHeroesSet();
        List<Town> towns = dataProvider.getTownsList();

        // Dodajemy miasta z DLC do listy, jeżeli jeszcze się tam nie znajdują
        for (Town town : dlcTowns) {
            if (!playableTownsWithHeroesList.containsKey(town)) {
                List<Hero> townHeroes = new ArrayList<>();
                for (Hero hero : dlcHeroes) {
                    List<String> Classess = town.getStartingHeroClasses();
                    for(String classs : Classess) {

                        if (hero.getHeroClass().equals(classs)) {
                            townHeroes.add(hero);
                        }
                    }
                }
                playableTownsWithHeroesList.put(town, townHeroes);
            }
        }

        // Dla miast z podstawowej wersji gry (Towns), dodajemy bohaterów, jeśli jeszcze nie ma w mapie
        for (Town town : towns) {
            if (!playableTownsWithHeroesList.containsKey(town)) {
                List<Hero> townHeroes = new ArrayList<>();
                for (Hero hero : dlcHeroes) {
                    List<String> Classess = town.getStartingHeroClasses();
                    for(String classs : Classess) {

                        if (hero.getHeroClass().equals(classs)) {
                            townHeroes.add(hero);
                        }
                    }
                }
                playableTownsWithHeroesList.put(town, townHeroes);
            }
        }
    }



    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC() {
        List<Town> dlcTowns = dataProvider.getDLCTownsList();
        List<Town> towns = dataProvider.getTownsList();
        Set<Hero> dlcHeroes = dataProvider.getDLCHeroesSet();
        for (Town town : dlcTowns) {
            if (playableTownsWithHeroesList.containsKey(town)) {
                playableTownsWithHeroesList.remove(town);
            }
        }
        for (Town town : towns) {
            if (playableTownsWithHeroesList.containsKey(town)) {
                List<Hero> Heroes = playableTownsWithHeroesList.get(town);
                for (Hero hero : Heroes) {
                    for (Hero heroD: dlcHeroes){
                        if(hero.equals(heroD)){
                            Heroes.remove(hero);
                        }
                    }
                }
                playableTownsWithHeroesList.replace(town, Heroes);

            }
        }
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town) {
        if(playableTownsWithHeroesList.containsKey(town)) {
            return playableTownsWithHeroesList.get(town);

        }
        else {
            // Jeśli miasta nie ma na mapie, rzucamy wyjątek
            throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());
        }
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes) {
        // Używamy TreeMap, aby miasta były automatycznie posortowane
        Map<Town, List<Hero>> townHeroMap = new TreeMap<>(Comparator.comparing(Town::getTownName)); // Sortujemy miasta po nazwie

        for (Town town : availableTowns) {
            List<Hero> townHeroes = new ArrayList<>();

            // Dla każdego bohatera sprawdzamy, czy pasuje do klasy bohatera przypisanej do miasta
            for (Hero hero : availableHeroes) {
                List<String> classes = town.getStartingHeroClasses();
                for(String className : classes) {
                    String classs = hero.getHeroClass();
                    if (classs.equals(className))
                    {
                        townHeroes.add(hero);
                    }
                }
            }

            // Dodajemy miasto i listę bohaterów do mapy
            townHeroMap.put(town, townHeroes);
        }

        return townHeroMap;
    }


    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
     // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
     // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
     public Hero selectHeroByName(Town heroTown, String name) {
         List<Hero> townHeroes = playableTownsWithHeroesList.get(heroTown);

             for (Hero hero : townHeroes) {
                 if (hero.getName().equals(name))
                 {
                     townHeroes.remove(hero);
                     return hero;
                 }
             }

         throw new NoSuchElementException(HERO_NOT_FOUND+name);
     }
 }