# Java - Testy jednostkowe

Przedmiotem zadania jest zapoznanie się z biblioteką Junit5 umożliwiającą tworzenie testów jednostkowych.

## Materiały, z którymi należy się zapoznać przed zajęciami:

- [Junit5](https://junit.org/junit5/docs/current/user-guide/)
- [Given/When/Then Pattern](https://martinfowler.com/bliki/GivenWhenThen.html)
- [Arrange/Act/Assert Pattern](https://automationpanda.com/2020/07/07/arrange-act-assert-a-pattern-for-writing-good-tests/)
- [Junit assertions](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html)
- [Unit tests naming conventions](https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea)
- [Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)

## Zadanie

Przedmiotem zadania jest zaimplementowanie testów jednostkowych dla przykładowej aplikacji FitCalculator. Przy pisaniu
testów należy stosować się do:

- nazewnictwo testów zgodne z
  wybraną [konwencją nazewniczą](https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea)
- testy pisane zgodnie ze schematem [//Given//When//Then](https://martinfowler.com/bliki/GivenWhenThen.html)
  lub [Arrange/Act/Assert](https://automationpanda.com/2020/07/07/arrange-act-assert-a-pattern-for-writing-good-tests/)
  np.:

  ```
     @Test
     void name() {
     //given
  
     //when
  
     //then
  }
  ```

1. Implementacja testów dla klasy `efs.task.unittests.FitCalculator` w klasie
   testowej `efs.task.unittests.FitCalculatorTest`.
    - testy dla metody `FitCalculator.isBMICorrect`:
        - analogicznie do istniejącego testu `shouldReturnTrue_whenDietRecommended` utwórz test sprawdzający przypadek,
          dla którego isBMICorrect zwraca `false` (np. wzrost 1.72, waga 69.5).
        - utwórz test dla przypadku: wzrost użytkownika równy 0.0, waga dowolna -> oczekiwanie rezultat: metoda rzuca
          wyjątkiem `IllegalArgumentException`.
        - Korzystając z adnotacji **@ParameterizedTest** oraz **@ValueSource** utwórz test dla przypadku: wybrany
          wzrost, waga jako parametr-minimum 3 różne wartości -> oczekiwany rezultat dla wszystkich wag metoda zwraca
          `true`.
        - Korzystając z adnotacji **@ParameterizedTest** oraz **@CsvSource** utwórz test dla przypadków: wzrost i waga
          jako para parametrów, minimum 3 różne wartości -> oczekiwany rezultat dla wszystkich par wartości metoda
          zwraca `false`.
        - Korzystając z adnotacji **@ParameterizedTest** oraz **@CsvFileSource** utwórz test dla przypadków: wzrost i
          waga jako para parametrów pobierane z pliku `resources.data.csv`-> oczekiwany rezultat dla wszystkich par
          wartości metoda zwraca `false`.
        - Dla poprawienia czytelności test result korzystając z argumentu name przyjmowanego przez
          **@ParameterizedTest** nadaj nazwy parametrom przyjmowanym przez testy.

    - testy dla metody `FitCalculator.findUserWithTheWorstBMI`:
        - utwórz test dla przypadku: dla listy `efs.task.unittests.TestConstants.TEST_USERS_LIST` -> oczekiwania:
          użytkownik z najgorszym wynikiem BMI waga: 97.3, wzrost 1.79;
        - utwórz test dla przypadku: pusta lista użytkowników -> oczekiwania: metoda zwraca null;

    - test dla metody `FitCalculator.calculateBMIScore`:
        - utwórz test dla przypadku: dla listy `efs.task.unittests.TestConstants.TEST_USERS_LIST` -> oczekiwania:
          `efs.task.unittests.TestConstants.TEST_USERS_BMI_SCORE`;

2. Stwórz klasę testową dla `efs.task.unittests.Planner`. Klasa testowa powinna zawierać pole typu `Planner`
   inicjalizowane jako nowa instancja obiektu planer przed każdym testem jednostkowym.
    - testy dla metody `Planner.calculateDailyCaloriesDemand`:
        - utwórz test parametryzowany sprawdzający poprawność wyliczenia dziennego zapotrzebowania kalorii dla
          wszystkich wartości typu wyliczeniowego `efs.task.unittests.ActivityLevel`, obliczenie dla użytkownika
          `efs.task.unittests.TestConstants.TEST_USER` -> oczekiwania: prawidłowe wartości zapotrzebowania kalorii dla
          użytkownika `efs.task.unittests.TestConstants.TEST_USER` znajdziesz w
          mapie  `efs.task.unittests.TestConstants.CALORIES_ON_ACTIVITY_LEVEL`;

    - testy dla metody `Planner.calculateDailyIntake`:
        - utwórz test sprawdzający poprawność wyliczenia dziennego zapotrzebowania na składniki odżywcze (`dailyIntake`)
          dla użytkownika `efs.task.unittests.TestConstants.TEST_USER` -> oczekiwania: prawidłowe wartości
          zapotrzebowania na składniki odżywcze dla `efs.task.unittests.TestConstants.TEST_USER` takie jak
          w `efs.task.unittests.TestConstants.TEST_USER_DAILY_INTAKE`;
