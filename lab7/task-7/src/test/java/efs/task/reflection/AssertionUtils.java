package efs.task.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionUtils {

  public static void assertDeclaredMethod(final Class<?> actualClass, final String name,
      final Class<?> returnType) throws NoSuchMethodException {
    final Method method = actualClass.getDeclaredMethod(name);
    assertNotNull(method,
        String.format("Adnotacja nie posiada zadeklarowanego parametru '%s'", name));
    assertEquals(returnType, method.getReturnType(),
        String.format("Adnotacja posiada niepoprawny typ parametru '%s'", name));
    assertNull(method.getDefaultValue(),
        String.format("Adnotacja posiada niepoprawną wartość domyślną '%s'", name));
  }

  public static void assertDeclaredMethodDefaultValue(final Class<?> actualClass, final String name,
      final Object returnValue) throws Exception {
    final Method method = actualClass.getDeclaredMethod(name);
    assertNotNull(method,
        String.format("Adnotacja nie posiada zadeklarowanego parametru '%s'", name));
    assertEquals(returnValue, method.getDefaultValue(),
        String.format("Adnotacja posiada niepoprawną wartość domyślną '%s'", name));
  }

  public static void assertRuntimeRetentionPolicy(final Class<?> actualClass,
      final RetentionPolicy retentionPolicy) {
    final Retention retention = actualClass.getAnnotation(Retention.class);
    assertNotNull(retention, String
        .format("Typ '%s' nie posiada adnotacji określającej tryb działania",
            actualClass.getCanonicalName()));
    assertEquals(retentionPolicy, retention.value(), String
        .format("Typ '%s' posiada niepoprawną wartość adnotacji określającej tryb działania",
            actualClass.getCanonicalName()));
  }

  public static void assertAnnotationTarget(final Class<?> actualClass,
      final ElementType... expectedElementTypes) {
    final Target target = actualClass.getAnnotation(Target.class);
    assertNotNull(target, String.format(
        "Typ '%s' nie posiada adnotacji określającej typ elementów dla których może być zastosowana",
        actualClass.getCanonicalName()));

    final ElementType[] actualElementTypes = target.value();
    Arrays.sort(actualElementTypes);
    Arrays.sort(expectedElementTypes);

    assertArrayEquals(expectedElementTypes, actualElementTypes, String.format(
        "Typ '%s' posiada niepoprawną wartość adnotacji określającej typ elementów dla których może być zastosowana",
        actualClass.getCanonicalName()));
  }

  public static void assertPrivateFieldEquals(final Object obj, final String fieldName,
      final Object expectedValue, final String message) {
    try {
      final Field field = obj.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      assertEquals(expectedValue, field.get(obj), message);
    } catch (Exception e) {
      throw new RuntimeException("Unable to access field: " + fieldName, e);
    }
  }
}
