package efs.task.generics;

public class Main {

    public static void main(String[] args) {
        classesCanBeGeneric();
        methodsCanBeGeneric();
    }

    static void classesCanBeGeneric() {
        var withInteger = GenericWrapper.create(7);
        Integer intValue = withInteger.getValue();
        System.out.println(intValue);

        var withString = GenericWrapper.create("Hello");
        String stringValue = withString.getValue();
        System.out.println(stringValue);
    }

    static void methodsCanBeGeneric() {
        Integer anInt = 10;
        Float aFloat = 10f;
        Double aDouble = 10.0;

        Integer resultingInt = NumberInspection.inspectNumberAndReturnTheSameType(anInt);
        Float resultingFloat = NumberInspection.inspectNumberAndReturnTheSameType(aFloat);
        Double resultingDouble = NumberInspection.inspectNumberAndReturnTheSameType(aDouble);

        System.out.println(resultingInt);
        System.out.println(resultingFloat);
        System.out.println(resultingDouble);
    }
}

class NumberInspection {
    static <T extends Number> T inspectNumberAndReturnTheSameType(T number) {
        System.out.println("Double value of the number of type " + number.getClass().getSimpleName() + " is: " + number.doubleValue());
        return number;
    }
}


class GenericWrapper<T> {
    private T value;

    private GenericWrapper(T value) {
        this.value = value;
    }

    static <T> GenericWrapper<T> create(T value) {
        return new GenericWrapper<>(value);
    }

    public T getValue() {
        return value;
    }
}
