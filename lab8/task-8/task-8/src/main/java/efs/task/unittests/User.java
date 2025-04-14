package efs.task.unittests;

class User {

    private final double height;
    private final double weight;
    private int age;
    private Gender gender;

    public User(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public User(double height, double weight, int age, Gender gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    enum Gender {
        MALE, FEMALE
    }
}
