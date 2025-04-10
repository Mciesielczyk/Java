package efs.task.reflection.json;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Klasa nie podlega modyfikacjom.
 * Można za jej pomocą przetestować proces konwersji do formatu JSON.
 */
public class JsonDemoApplication {

  public static void main(String[] args) {
    // Create random product
    final ProductDTO randomProduct = newRandomProduct();

    // Convert ProductDTO to JSON
    final String productJson = JsonSerializer.toJsonText(randomProduct);
    System.out.println("JSON: " + productJson);

    // Convert JSON back to ProductDTO
    final ProductDTO product = JsonSerializer.fromJsonText(productJson, ProductDTO.class);
    System.out.println(product);
  }

  private static ProductDTO newRandomProduct() {
    final SecureRandom secureRandom = new SecureRandom();

    final ProductDTO product = new ProductDTO();
    product.setId(Math.abs(secureRandom.nextLong()));
    product.setPrice(new BigDecimal(secureRandom.nextInt(1000)).multiply(BigDecimal.TEN)
            .setScale(2, RoundingMode.HALF_UP));
    product.setProductionDate(Date.from(Instant.now()));
    product.setExpiryDate(java.util.Date.from(Instant.now().plus(30, ChronoUnit.DAYS)));
    String randomString = getRandomString(secureRandom);
    product.setName(randomString);
    return product;
  }

  private static String getRandomString(SecureRandom secureRandom) {
    byte[] bytes = new byte[64];
    secureRandom.nextBytes(bytes);
    return new String(bytes, StandardCharsets.US_ASCII).replaceAll("[^A-Za-z0-9]", "");
  }
}
