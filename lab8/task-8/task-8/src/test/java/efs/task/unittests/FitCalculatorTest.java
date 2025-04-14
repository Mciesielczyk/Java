package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

 /*   analogicznie do istniejącego testu shouldReturnTrue_whenDietRecommended utwórz test sprawdzający przypadek,
    dla którego isBMICorrect zwraca false (np. wzrost 1.72, waga 69.5).*/

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        double weight = 69.5;
        double height = 1.72;
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        assertFalse(recommended);
    }


/*
    utwórz test dla przypadku: wzrost użytkownika równy 0.0,
    waga dowolna -> oczekiwanie rezultat: metoda rzuca wyjątkiem IllegalArgumentException.*/

    @Test
    void shouldReturException_when0() {
        double weight = 69.5;
        double height = 0;

        assertThrows(IllegalArgumentException.class, ()
                -> FitCalculator.isBMICorrect(weight, height));
    }


   /* Korzystając z adnotacji @ParameterizedTest oraz @ValueSource utwórz test dla przypadku: wybrany wzrost,
    \waga jako parametr-minimum 3 różne wartości -> oczekiwany rezultat dla wszystkich wag metoda zwraca true.*/

    @ParameterizedTest(name = "Should return true for weight = {0} and height = 1.80")
    @ValueSource(doubles = {82.0, 86.5, 95.0})
        // minimum 3 wartości
    void shouldReturnTrue_forVariousWeights(double weight) {
        // given
        double height = 1.80;

        // when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // then
        assertTrue(result);
    }


/*    Korzystając z adnotacji @ParameterizedTest oraz @CsvSource utwórz test dla przypadków: wzrost i waga jako para parametrów,
    minimum 3 różne wartości -> oczekiwany rezultat dla wszystkich par wartości metoda zwraca false.*/

    @ParameterizedTest(name = "Should return false for weight = {0}, height = {1}")
    @CsvSource({
            "70, 1.72",
            "40.0, 1.50",
            "45.0, 1.80"
    })
    void shouldReturnFalse_forManyCases(double weight, double height) {
        // given – wartości przekazane w CsvSource

        // when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(result);
    }


 /*   Korzystając z adnotacji @ParameterizedTest oraz @CsvFileSource utwórz test dla przypadków: wzrost i waga jako para parametrów pobierane z
    pliku resources.data.csv-> oczekiwany rezultat dla wszystkich par wartości metoda zwraca false.
}*/


    @ParameterizedTest(name = "From CSV: weight = {0}, height = {1} => should return false")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_forAllValuesFromCSV(double weight, double height) {
        // given – dane z CSV

        // when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(result);
    }

/*    Dla poprawienia czytelności test result korzystając z argumentu name przyjmowanego przez @ParameterizedTest nadaj
    nazwy parametrom przyjmowanym przez testy.*/




/*    utwórz test dla przypadku: dla listy efs.task.unittests.TestConstants.TEST_USERS_LIST ->
    oczekiwania: użytkownik z najgorszym wynikiem BMI waga: 97.3, wzrost 1.79;*/

    @Test
    void shouldReturnWorstBMI() {
        //given
        List<User> usersList = TestConstants.TEST_USERS_LIST;


        //when
        User worst = FitCalculator.findUserWithTheWorstBMI(usersList);
        //then
        assertEquals(97.3, worst.getWeight(), 0.01);
        assertEquals(1.79, worst.getHeight(), 0.01);
    }


    /*
        utwórz test dla przypadku: pusta lista użytkowników -> oczekiwania: metoda zwraca null;
    */
    @Test
    void shouldReturnNull() {
        List<User> usersList = new ArrayList<>();
        User worst = FitCalculator.findUserWithTheWorstBMI(usersList);
        //then
        assertEquals(null, worst);
    }

    /* test dla metody FitCalculator.calculateBMIScore:

     utwórz test dla przypadku: dla listy efs.task.unittests.TestConstants.TEST_USERS_LIST
      -> oczekiwania: efs.task.unittests.TestConstants.TEST_USERS_BMI_SCORE;*/
    @Test
    void shouldReturnBMIScore() {
        //given
        List<User> usersList = TestConstants.TEST_USERS_LIST;
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;


        //when
        double[] bmiScores = new double[usersList.size()];
        bmiScores=FitCalculator.calculateBMIScore(usersList);
        //then
        assertArrayEquals(expectedScores, bmiScores, 0.01); // tolerancja 0.01 dla double

    }
}