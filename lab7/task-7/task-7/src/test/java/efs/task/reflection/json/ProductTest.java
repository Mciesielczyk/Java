package efs.task.reflection.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {

  private static final String REFERENCE_PRODUCT_JSON = "{\"ProductID\":86354278659412535,\"ProductName\":\"TheBestProductEver\",\"ProductPrice\":123.456,\"DateOfProduction\":\"2019-04-13@12:34:56\",\"DateOfExpiry\":\"2020-02-29\"}";
  private ProductDTO referenceProduct;

  @BeforeEach
  public void setup() {
    referenceProduct = newProduct();
  }

  @Test
  public void testProductToJsonText() {
    // when
    final String jsonText = JsonSerializer.toJsonText(referenceProduct);

    // then
    assertEquals(REFERENCE_PRODUCT_JSON, jsonText,
        "Błąd podczas konwersji do formatu JSON. Sprawdź kolejność elementów oraz ich wartość");
  }

  @Test
  public void testEmptyProductToJsonText() {
    // when
    final String jsonText = JsonSerializer.toJsonText(new ProductDTO());

    // then
    assertEquals("{}", jsonText,
        "Błąd podczas konwersji do formatu JSON. Sprawdź mapowanie pustych wartości");
  }

  @Test
  public void testFromJsonText() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText(REFERENCE_PRODUCT_JSON, ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.ID);
    assertEq(referenceProduct, product, ProductFieldType.NAME);
    assertEq(referenceProduct, product, ProductFieldType.PRICE);
    assertEq(referenceProduct, product, ProductFieldType.PROD_DATE);
    assertEq(referenceProduct, product, ProductFieldType.EXP_DATE);
  }

  @Test
  public void testFromJsonTextProductID() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText("{\"ProductID\":86354278659412535}", ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.ID);
  }

  @Test
  public void testFromJsonTextProductName() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText("{\"ProductName\":\"TheBestProductEver\"}", ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.NAME);
  }

  @Test
  public void testFromJsonTextProductExpiryDate() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText("{\"DateOfExpiry\":\"2020-02-29\"}", ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.EXP_DATE);
  }

  @Test
  public void testFromJsonTextProductExpiryDateFormat() {
    // when
    final String jsonText = JsonSerializer.toJsonText(referenceProduct);

    // then
    final Pattern pattern = Pattern
        .compile("\""+ProductFieldType.EXP_DATE.jsonName+"\"\\s*:\\s*\"([^\"]+)\"");
    final Matcher matcher = pattern.matcher(jsonText);

    assertTrue(matcher.find(), "Niepoprawne mapowanie " + ProductFieldType.EXP_DATE.jsonName);
    assertEquals("2020-02-29", matcher.group(1).trim(),
        "Niepoprawny format daty w polu " + ProductFieldType.EXP_DATE.jsonName);
  }

  @Test
  public void testFromJsonTextProductProductionDateFormat() {
    // when
    final String jsonText = JsonSerializer.toJsonText(referenceProduct);

    // then
    final Pattern pattern = Pattern
        .compile("\""+ProductFieldType.PROD_DATE.jsonName+"\"\\s*:\\s*\"([^\"]+)\"");
    final Matcher matcher = pattern.matcher(jsonText);

    assertTrue(matcher.find(), "Niepoprawne mapowanie " + ProductFieldType.PROD_DATE.jsonName);
    assertEquals("2019-04-13@12:34:56", matcher.group(1).trim(),
        "Niepoprawny format daty w polu " + ProductFieldType.PROD_DATE.jsonName);
  }

  @Test
  public void testFromJsonTextProductProductionDate() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText("{\"DateOfProduction\":\"2019-04-13@12:34:56\"}", ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.PROD_DATE);
  }

  @Test
  public void testFromJsonTextProductPrice() {
    // when
    final ProductDTO product = JsonSerializer
        .fromJsonText("{\"ProductPrice\":123.456}", ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.PRICE);
  }

  @Test
  public void testFromJsonTextWithUnknownField() {
    // given
    final String json = "{\"UnknownField\":1234567,\"ProductID\":86354278659412535,\"ProductName\":\"TheBestProductEver\",\"ProductPrice\":123.456,\"DateOfProduction\":\"2019-04-13@12:34:56\",\"DateOfExpiry\":\"2020-02-29\"}";

    // when
    final ProductDTO product = JsonSerializer.fromJsonText(json, ProductDTO.class);

    // then
    assertNotNull(product, "Błąd podczas parsowania JSON");
    assertEq(referenceProduct, product, ProductFieldType.ID);
    assertEq(referenceProduct, product, ProductFieldType.NAME);
    assertEq(referenceProduct, product, ProductFieldType.PRICE);
    assertEq(referenceProduct, product, ProductFieldType.PROD_DATE);
    assertEq(referenceProduct, product, ProductFieldType.EXP_DATE);
  }

  private static void assertEq(final ProductDTO expectedProduct, final ProductDTO actualProduct,
      ProductFieldType field) {
    assertEquals(readValue(expectedProduct, field), readValue(actualProduct, field),
        String.format("Niepoprawne mapowanie 'ProductDTO.%s' do atrybutu '%s' w formacie JSON",
            field.className, field.jsonName));
  }

  private static Object readValue(final Object obj, final ProductFieldType fieldType) {
    try {
      final Field field = obj.getClass().getDeclaredField(fieldType.className);
      field.setAccessible(true);
      return field.get(obj);
    } catch (Exception e) {
      throw new RuntimeException("Unable to access field: " + fieldType.className, e);
    }
  }

  private static ProductDTO newProduct() {
    final ProductDTO productDTO = new ProductDTO();
    productDTO.setId(86354278659412535L);
    productDTO.setName("TheBestProductEver");
    productDTO.setPrice(new BigDecimal("123.456"));
    productDTO.setProductionDate(
        Date.from(LocalDateTime.of(2019, 4, 13, 12, 34, 56).toInstant(ZoneOffset.UTC)));
    productDTO.setExpiryDate(
        Date.from(LocalDate.of(2020, 2, 29).atStartOfDay().toInstant(ZoneOffset.UTC)));

    return productDTO;
  }

  private enum ProductFieldType {
    ID("id", "ProductID"),
    NAME("name", "ProductName"),
    PRICE("price", "ProductPrice"),
    PROD_DATE("productionDate", "DateOfProduction"),
    EXP_DATE("expiryDate", "DateOfExpiry");

    private final String className;
    private final String jsonName;


    ProductFieldType(final String className, final String jsonName) {
      this.className = className;
      this.jsonName = jsonName;
    }
  }
}
