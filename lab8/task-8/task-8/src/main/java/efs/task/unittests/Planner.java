package efs.task.unittests;

class Planner {

    private int calculateProtein(int bmr) {
        int proteinPercentage = 20;
        return (int) Math.round(bmr * proteinPercentage / 400.0);
    }

    private int calculateFat(int bmr) {
        int fatPercentage = 30;
        return (int) Math.round(bmr * fatPercentage / 900.0);
    }

    private int calculateCarbohydrate(int bmr) {
        int carbohydratePercentage = 50;
        return (int) Math.round(bmr * carbohydratePercentage / 400.0);
    }

    public int calculateDailyCaloriesDemand(User user, ActivityLevel level) {
        validateUserData(user);
        return (int) Math.round(calculateCaloriesDemand(user) * level.getFactor());
    }

    private double calculateCaloriesDemand(User user) {
        double caloriesDemand;
        if (user.getGender().equals(User.Gender.MALE)) {
            caloriesDemand = 66 + (13.7 * user.getWeight()) + (5 * user.getHeight() * 100) - (6.8 * user.getAge());
        } else {
            caloriesDemand = 655 + (9.6 * user.getWeight()) + (1.8 * user.getHeight() * 100) - (4.7 * user.getAge());
        }
        return caloriesDemand;
    }

    private static void validateUserData(User user) {
        if (user.getAge() == 0 || user.getHeight() == 0.0 || user.getWeight() == 0.0 || user.getGender() == null) {
            throw new IllegalArgumentException("Do obliczenia zapotrzebowania wymagane są wszystkie dane o użytkowniku");
        }
    }

    public DailyIntake calculateDailyIntake(User user) {
        return calculateDailyIntake(user, ActivityLevel.LOW_ACTIVITY);
    }

    private DailyIntake calculateDailyIntake(User user, ActivityLevel level) {
        int calories = calculateDailyCaloriesDemand(user, level);
        int protein = this.calculateProtein(calories);
        int fat = this.calculateFat(calories);
        int carbohydrate = this.calculateCarbohydrate(calories);

        return new DailyIntake(calories, protein, fat, carbohydrate);
    }
}