package efs.task.unittests;

import java.util.Comparator;
import java.util.List;

class FitCalculator {

    private static final double BMI_THRESHOLD = 24.99;

    public static boolean isBMICorrect(double weight, double height) {
        if (height == 0.0 || weight == 0.0) throw new IllegalArgumentException();
        double bmi = weight / (height * height);
        return !(bmi < BMI_THRESHOLD);
    }

    public static User findUserWithTheWorstBMI(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(FitCalculator::calculateBMI))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public static double[] calculateBMIScore(List<User> users) {
        double[] bmiScores = new double[users.size()];
        for (int i = 0; i < bmiScores.length; i++) {
            bmiScores[i] = FitCalculator.calculateBMI(users.get(i));
        }
        return bmiScores;
    }

    private static double calculateBMI(User user) {
        double height = user.getHeight();
        double weight = user.getWeight();
        if (height == 0.0)
            throw new ArithmeticException();
        double bmi = weight / (height * height);
        return Math.round(bmi * 100) / 100.0;
    }
}
