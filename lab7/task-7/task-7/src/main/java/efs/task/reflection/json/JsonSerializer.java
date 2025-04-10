package efs.task.reflection.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ta klasa nie podlega modyfikacjom
 */
public final class JsonSerializer {

  public static <T> String toJsonText(final T input) {
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null at this point");
    }

    try {
      return MapperHolder.JSON_MAPPER.writeValueAsString(input);
    } catch (final JsonProcessingException e) {
      throw new RuntimeException("Unexpected error during serialization", e);
    }
  }

  public static <T> T fromJsonText(final String input, final Class<T> result) {
    if (input == null) {
      throw new IllegalArgumentException("Input JSON cannot be null at this point");
    }

    try {
      return MapperHolder.JSON_MAPPER.readValue(input, result);
    } catch (final JsonProcessingException e) {
      throw new RuntimeException("Unexpected error during deserialization", e);
    }
  }

  private JsonSerializer() {
  }

  private static class MapperHolder {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
  }
}
