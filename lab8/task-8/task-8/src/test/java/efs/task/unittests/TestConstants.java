package efs.task.unittests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConstants {
    public static final List<User> TEST_USERS_LIST = setupTestUsersList();
    public static final User TEST_USER = new User(1.81, 42.3, 22, User.Gender.MALE);
    public static final Map<ActivityLevel, Integer> CALORIES_ON_ACTIVITY_LEVEL = getCaloriesOnActivityLevel();
    public static final DailyIntake TEST_USER_DAILY_INTAKE = new DailyIntake(1926, 96, 64, 241);
    public static final double[] TEST_USERS_BMI_SCORE = {18.52, 29.92, 30.37, 25.03};

    private static List<User> setupTestUsersList() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(1.80, 60.0));
        usersList.add(new User(1.82, 99.1));
        usersList.add(new User(1.79, 97.3));
        usersList.add(new User(1.62, 65.7));
        return usersList;
    }

    private static Map<ActivityLevel, Integer> getCaloriesOnActivityLevel() {
        Map<ActivityLevel, Integer> result = new HashMap<>();
        result.put(ActivityLevel.NO_ACTIVITY, 1681);
        result.put(ActivityLevel.LOW_ACTIVITY, 1926);
        result.put(ActivityLevel.MEDIUM_ACTIVITY, 2171);
        result.put(ActivityLevel.HIGH_ACTIVITY, 2417);
        result.put(ActivityLevel.EXTREME_ACTIVITY, 2662);
        return result;
    }
}
