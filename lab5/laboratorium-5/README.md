# Java - kolekcje.

Kolekcje w języku Java.

## Materiały, z którymi należy się zapoznać przed zajęciami.

Obowiązują wszystkie materiały z poprzednich zajęć ze szczególnym uwzględnieniem:

* [Oracle List](https://docs.oracle.com/javase/tutorial/collections/interfaces/list.html)
* [Oracle Set](https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html)
* [Oracle Map](https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html)
* [Oracle Trail: Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
* [Java equals() and hashCode()](https://www.baeldung.com/java-equals-hashcode-contracts)
* [Comparable](https://www.baeldung.com/java-comparator-comparable)
* [Sort a Map in Java](https://www.baeldung.com/java-hashmap-sort)
* [Exceptions in Java](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html)

## Zadanie

Przedmiotem zadania jest implementacja metod niezbędnych do działania uproszczonego lobby gry Heroes III Of Might And Magic.
 
1. Implementacja [DataProvider](src/main/java/efs/task/collections/data/DataProvider.java) :
   *  Na podstawie danych w  [Data](src/main/java/efs/task/collections/data/Data.java) utworzyć listy miast - 
      [klasy Town](src/main/java/efs/task/collections/entity/Town.java) dostępnych w podstawowej wersji 
      gry oraz w dodatku - implementacja metod `getTownsList` oraz `getDLCTownsList`.
   *  Na podstawie danych w  [Data](src/main/java/efs/task/collections/data/Data.java) utworzyć listy bohaterów - 
      [klasy Hero](src/main/java/efs/task/collections/entity/Hero.java) dostępnych w podstawowej wersji gry 
      oraz w dodatku - implementacja metod `getHeroesSet` oraz `getDLCHeroesSet`.
      * UWAGA lista bohaterów powinna zawierać tylko unikalnych bohaterów - porównując imie bohatera i klasę.
   
2. Implementacja metod w [GameLobby](src/main/java/efs/task/collections/game/GameLobby.java) na podstawie komentarzy `//todo:`.
Komentarze `//todo:` zawierają dokładny opis działania metod.
   * UWAGA niektóre funkcjonalności kolekcji działają tylko z prawidłowo zaimplementowanymi metodami `equals()`, `hashCode()`;
   * UWAGA sortowanie wymaga [Comparable](https://www.baeldung.com/java-comparator-comparable).

Nie należy modyfikować typów zwracanych przez metody oraz listy argumentów, jakie przyjmują. Kod klas testujących **nie może** zostać zmieniony.

### Sprawdzanie rozwiązania

Stan swojego rozwiązania można sprawdzić uruchamiając testy jednostkowe w projekcie i analizując ich wyniki. Rozwiązanie
jest poprawne, jeżeli wszystkie testy kończą się pozytywnie.
