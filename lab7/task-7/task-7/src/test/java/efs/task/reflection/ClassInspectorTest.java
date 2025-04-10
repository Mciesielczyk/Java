package efs.task.reflection;

import efs.task.reflection.model.Villager;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collection;

import static efs.task.reflection.AssertionUtils.assertPrivateFieldEquals;
import static efs.task.reflection.model.Villager.HIDDEN_VILLAGER_DESC;
import static efs.task.reflection.model.Villager.HIDDEN_VILLAGER_NAME;
import static org.junit.jupiter.api.Assertions.*;

public class ClassInspectorTest {

  @Test
  public void testGetAnnotatedFields() {
    final Class<? extends Annotation> notNullAnnotation = loadAnnotationByName(
        "efs.task.reflection.annotations.NotNull");

    // when
    final Collection<String> annotatedFields = ClassInspector
        .getAnnotatedFields(Villager.class, notNullAnnotation);

    // then
    assertTrue(annotatedFields.remove("name"),
        "Pole Villager.name powinno posiadać adnotację @NotNull i zawierać się w liście wyników");
    assertTrue(annotatedFields.remove("description"),
        "Pole Villager.description powinno posiadać adnotację @NotNull i zawierać się w liście wyników");
    assertTrue(annotatedFields.isEmpty(), "Lista zawiera nieoczekiwane wartości: " + annotatedFields
        + "Sprawdź czy adnotacja @NotNull nie została dodana do innych pól klasy Villager");
  }

  @Test
  public void testAllDeclaredMethods() {
    // when
    final Collection<String> declaredMethods = ClassInspector.getAllDeclaredMethods(Villager.class);

    // then
    assertTrue(declaredMethods.remove("getDamage"),
        "Lista powinna zawierać metodę 'getDamage' zadeklarowaną w interfejsie Fighter");
    assertTrue(declaredMethods.remove("toString"),
        "Lista powinna zawierać metodę 'toString' zadeklarowaną w klasie Villager");
    assertTrue(declaredMethods.remove("setHealth"),
        "Lista powinna zawierać metodę 'setHealth' zadeklarowaną w klasie Villager");
    assertTrue(declaredMethods.isEmpty(),
        "Lista zawiera nieoczekiwane nazwy metod: " + declaredMethods);
  }

  @Test
  public void testPrivateCreateInstance() throws Exception {
    // when
    final Villager villager = ClassInspector.createInstance(Villager.class);

    // then
    assertNotNull(villager, "Instancja osadnika nie została poprawnie zainicjowana");
    assertPrivateFieldEquals(villager, "name", HIDDEN_VILLAGER_NAME,
        "Niepoprawna wartość nazwy osadnika");
    assertPrivateFieldEquals(villager, "description", HIDDEN_VILLAGER_DESC,
        "Niepoprawna wartość nazwy osadnika");
  }
  
    @Test
  public void testPrivateCreateInstance2() throws Exception {
    // given
    final String villagerName = "Mason";
    final int villagerAge = 18;

    // when
    final Villager villager = ClassInspector
            .createInstance(Villager.class, villagerName, villagerAge);

    // then
    assertNotNull(villager, "Instancja osadnika nie została poprawnie zainicjowana");
    assertPrivateFieldEquals(villager, "name", villagerName, "Niepoprawna wartość nazwy osadnika");
    assertPrivateFieldEquals(villager, "age", villagerAge,
            "Niepoprawna wartość wieku osadnika");
  }

  @Test
  public void testPrivateCreateInstance3() throws Exception {
    // given
    final String villagerName = "Mason";
    final int villagerAge = 18;

    // when
    final Villager villager = ClassInspector
            .createInstance(Villager.class, villagerAge, villagerName);

    // then
    assertNotNull(villager, "Instancja osadnika nie została poprawnie zainicjowana");
    assertPrivateFieldEquals(villager, "name", villagerName, "Niepoprawna wartość nazwy osadnika");
    assertPrivateFieldEquals(villager, "age", villagerAge,
            "Niepoprawna wartość wieku osadnika");
  }

  @Test
  public void testPublicCreateInstance() throws Exception {
    // given
    final String villagerName = "Mason";
    final String villagerDescription = "I'm skilled in cutting, dressing, and laying stone in buildings";

    // when
    final Villager villager = ClassInspector
        .createInstance(Villager.class, villagerName, villagerDescription);

    // then
    assertNotNull(villager, "Instancja osadnika nie została poprawnie zainicjowana");
    assertPrivateFieldEquals(villager, "name", villagerName, "Niepoprawna wartość nazwy osadnika");
    assertPrivateFieldEquals(villager, "description", villagerDescription,
        "Niepoprawna wartość nazwy osadnika");
  }

  @Test
  public void testConstructorParametersAnnotations() throws Exception {
    final Class<? extends Annotation> notNullAnnotation = loadAnnotationByName(
        "efs.task.reflection.annotations.NotNull");

    // when
    final Constructor<Villager> constructor = Villager.class
        .getConstructor(String.class, String.class);

    // then
    final Parameter[] parameters = constructor.getParameters();
    assertTrue(parameters[0].isAnnotationPresent(notNullAnnotation),
        "Parametr 'name' konstruktora klasy 'Villager' powinien posiadać adnotację @NotNull");
    assertTrue(parameters[1].isAnnotationPresent(notNullAnnotation),
        "Parametr 'description' konstruktora klasy 'Villager' powinien posiadać adnotację @NotNull");
  }

  private static <T extends Annotation> Class<T> loadAnnotationByName(final String className) {
    try {
      return (Class<T>) Class.forName(className);
    } catch (final ClassNotFoundException e) {
      fail("Nie udało się odszukać adnotacji: " + className);
      return null;
    }
  }
}
