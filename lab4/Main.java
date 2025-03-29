package efs.task.generics;
 
 public class Main {
 
     public static void main(String[] args) {
         classesCanBeGeneric();
         methodsCanBeGeneric();
     }
 
     static void classesCanBeGeneric() {
         var withInteger = GenericWrapper.create(7);
         Integer intValue = withInteger.getValue();
 
         var withString = GenericWrapper.create("Hello");
         String stringValue = withString.getValue();
     }
 
     static void methodsCanBeGeneric() {
         Integer anInt = 10;
         Float aFloat = 10f;
         Double aDouble = 10.0;
 
         Integer resultingInt = NumberInspection.inspectNumberAndReturnTheSameType(anInt);
         Float resultingFloat = NumberInspection.inspectNumberAndReturnTheSameType(aFloat);
         Double resultingDouble = NumberInspection.inspectNumberAndReturnTheSameType(aDouble);
     }
 }
 
 class NumberInspection {
 
     //todo: change the signature of the method below to make it generic
     //  ONLY the signature needs changing, you must leave the body and calling code unchanged
     static Number inspectNumberAndReturnTheSameType(Number number) {
         System.out.println("Double value of the number of type " + number.getClass().getSimpleName() + " is: " + number.doubleValue());
         return number;
     }
 }
 
 //todo: change code below to make this class generic
 class GenericWrapper<T> {
 
     //Object value;
     T value;
     static<T> GenericWrapper<T> create(T value) {
         return new GenericWrapper<>(value);
     }
 
     GenericWrapper(T value) {
         this.value = value;
     }
 
     T getValue() {
         return value;
     }
 }
 