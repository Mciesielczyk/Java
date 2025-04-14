package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "Calories demand for activity level = {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateCorrectCaloriesForEachActivityLevel(ActivityLevel activityLevel) {
        // given
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        // when
        int actualCalories = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // then
        assertEquals(expectedCalories, actualCalories);
    }


 /*   testy dla metody Planner.calculateDailyIntake:

    utwórz test sprawdzający poprawność wyliczenia dziennego zapotrzebowania na składniki odżywcze (dailyIntake)
    dla użytkownika efs.task.unittests.TestConstants.TEST_USER -> oczekiwania: prawidłowe wartości zapotrzebowania
    na składniki odżywcze dla efs.task.unittests.TestConstants.TEST_USER takie jak w
        efs.task.unittests.TestConstants.TEST_USER_DAILY_INTAKE;*/
@Test
void shouldCalculateCorrectCaloriesForEachUser() {
    User user = TestConstants.TEST_USER;

    DailyIntake dailyIntake = planner.calculateDailyIntake(user);

    DailyIntake expected = TestConstants.TEST_USER_DAILY_INTAKE;

    assertEquals(expected.getProtein(), dailyIntake.getProtein(), 0.01);
    assertEquals(expected.getFat(), dailyIntake.getFat(), 0.01);
    assertEquals(expected.getCarbohydrate(), dailyIntake.getCarbohydrate(), 0.01);
    }
}

