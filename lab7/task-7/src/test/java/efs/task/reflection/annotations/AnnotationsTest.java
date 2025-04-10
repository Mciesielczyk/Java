package efs.task.reflection.annotations;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import static efs.task.reflection.AssertionUtils.*;
import static org.junit.jupiter.api.Assertions.fail;

public class AnnotationsTest {

  @Test
  public void testNotNullAnnotation() throws Exception {
    final Class<? extends Annotation> notNullAnnotation = loadAnnotationByName(
        "efs.task.reflection.annotations.NotNull");
    assertDeclaredMethodDefaultValue(notNullAnnotation, "value", "n/a");
    assertAnnotationTarget(notNullAnnotation, ElementType.FIELD, ElementType.PARAMETER);
    assertRuntimeRetentionPolicy(notNullAnnotation, RetentionPolicy.RUNTIME);
  }

  @Test
  public void testBuilderPropertyAnnotation() throws Exception {
    final Class<? extends Annotation> builderPropertyAnnotation = loadAnnotationByName(
        "efs.task.reflection.annotations.BuilderProperty");
    assertDeclaredMethod(builderPropertyAnnotation, "name", String.class);
    assertAnnotationTarget(builderPropertyAnnotation, ElementType.METHOD, ElementType.CONSTRUCTOR);
    assertRuntimeRetentionPolicy(builderPropertyAnnotation, RetentionPolicy.SOURCE);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Annotation> Class<T> loadAnnotationByName(final String className) {
    try {
      return (Class<T>) Class.forName(className);
    } catch (final ClassNotFoundException e) {
      fail("Nie udało się odszukać adnotacji: " + className);
      return null;
    }
  }
}
