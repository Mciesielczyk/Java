package efs.task.collections.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static efs.task.collections.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataProviderTest {

    DataProvider underTest;

    @BeforeEach
    void setUp() {
        underTest = new DataProvider();
    }

    @Test
    void shouldReturnOnlyUniqueHeroes() {
        assertEquals(HEROES_NUMBER_NO_DLC, underTest.getHeroesSet().size());
    }

    @Test
    void shouldContainHeroesWithTheSameNameButDifferentClass() {
        assertTrue(underTest.getHeroesSet().contains(TEST_HERO_STYG_BEST));
        assertTrue(underTest.getHeroesSet().contains(TEST_HERO_STYG_WITCH));
    }

    @Test
    void shouldReturnTownsList() {
        assertEquals(TOWNS_NUMBER_NO_DLC, underTest.getTownsList().size());
    }

    @Test
    void shouldReturnOnlyUniqueDLCHeroes(){
        assertEquals(DLC_HEROES_NUMBER, underTest.getDLCHeroesSet().size());
    }

    @Test
    void shouldReturnDLCTownsList() {
        assertEquals(DLC_TOWNS_NUMBER, underTest.getDLCTownsList().size());
    }
}