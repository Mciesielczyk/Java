package efs.task.unittests;

enum ActivityLevel {
    NO_ACTIVITY(1.2),
    LOW_ACTIVITY(1.375),
    MEDIUM_ACTIVITY(1.55),
    HIGH_ACTIVITY(1.725),
    EXTREME_ACTIVITY(1.9);

    private final double factor;

    ActivityLevel(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
