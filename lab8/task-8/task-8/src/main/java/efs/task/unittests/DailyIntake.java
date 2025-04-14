package efs.task.unittests;

class DailyIntake {
    private final int calories;
    private final int protein;
    private final int fat;
    private final int carbohydrate;

    DailyIntake(int calories, int protein, int fat, int carbohydrate) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }
}
