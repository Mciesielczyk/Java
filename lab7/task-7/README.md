# Java - Refleksja i adnotacje

Przedmiotem zadania jest zapoznanie się z adnotacjami oraz narzędziami umożliwiającymi inspekcję klas w czasie działania programu.

## Materiały, z którymi należy się zapoznać przed zajęciami:

- [Jackson Annotations Wiki](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations)
- [Using Java Reflection](https://www.oracle.com/technical-resources/articles/java/javareflection.html)
- [Trail: The Reflection API](https://docs.oracle.com/javase/tutorial/reflect/index.html)
- [Java Tutorials: Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/index.html)
- [Adnotacje w języku Java](https://www.samouczekprogramisty.pl/adnotacje-w-jezyku-java/)

## Zadanie

1. Korzystając z adnotacji dostępnych w bibliotece Jackson dodać niezbędne adnotacje tak, aby konwersja obiektu `efs.task.reflection.json.ProductDTO` do formatu JSON spełniała następujące warunki
   - nieznane atrybuty powinny być ignorowane i nie powodować błędu,
   - puste atrybuty powinny być pomijane przy generowaniu JSON,
   - atrybuty powinny być generowane zawsze w tej samej kolejności tj. `ProductID`, `ProductName`, `ProductPrice`, `DateOfProduction`, `DateOfExpiry`,
   - pola klasy `efs.task.reflection.json.ProductDTO` powinny być mapowane do następujących elementów JSON:
      - `ProductDTO.id` na`ProductID` w formacie JSON,
      - `ProductDTO.name` na`ProductName` w formacie JSON,
      - `ProductDTO.price` na`ProductPrice` w formacie JSON,
      - `ProductDTO.expiryDate` na`DateOfExpiry` w formacie JSON w następującym formacie `yyyy-MM-dd`,
      - `ProductDTO.productionDate` na`DateOfProduction` w formacie JSON w następującym formacie `yyyy-MM-dd@HH:mm:ss`,
    
2. Utwórz w oddzielnych plikach w pakiecie `efs.task.reflection.annotations`,
   - Publiczną adnotację czasu wykonania o nazwie `NotNull` którą będzie można zastosować na polach klasy oraz parametrach metod. Adnotacja powinna zwierać opcjonalny parametr o nazwie `value` z wartością domyślną `n/a`.
   - Publiczną adnotację czasu kompilacji o nazwie `BuilderProperty` którą będzie można zastosować na metodach i konstruktorach. Adnotacja powinna zwierać wymagany parametr o nazwie `name` typu `String`.
   - Oznacz adnotacją `@NotNull` pole `name` oraz `description` w klasie `Villager`,
   - Oznacz adnotacją `@NotNull` parametry `name` oraz `description` publicznego konstruktora klasy `Villager`,
   - Oznacz adnotacją `@BuilderProperty` z parametrem `name = init` publiczny konstruktor klasy `Villager`,
   - Oznacz adnotacją `@BuilderProperty` z parametrem `name = health` metodę `setHealth` w klasie `Villager`,
   
3. Zaimplementować zawartość metod klasy `efs.task.reflection.ClassInspector` zgodnie z komentarzami zamieszczonymi w pliku  

## Sprawdzanie rozwiązania

Stan swojego rozwiązania można sprawdzić, uruchamiając testy jednostkowe w projekcie i analizując ich wyniki.
Rozwiązanie jest poprawne, jeżeli wszystkie testy kończą się pozytywnie.