package efs.task.collections.game;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static efs.task.collections.TestConstants.*;
import static efs.task.collections.game.GameLobby.HERO_NOT_FOUND;
import static efs.task.collections.game.GameLobby.NO_SUCH_TOWN;
import static org.junit.jupiter.api.Assertions.*;

class GameLobbyTest {
    GameLobby gameLobby;
    SoftAssertions softly;

    @BeforeEach
    void setUp() {
        gameLobby = new GameLobby();
        softly = new SoftAssertions();
    }

    @Test
    void shouldSetupBaseLobbyNoDLCHeroesAndTowns() {
        List<String> alphabeticalTowns = getAlphabeticalTowns();
        int iterator = 0;

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_NO_DLC);
        for (Map.Entry<Town, List<Hero>> entry : gameLobby.getPlayableTownsWithHeroesList().entrySet()) {
            softly.assertThat(entry.getKey().getTownName()).isEqualTo(alphabeticalTowns.get(iterator++));
            softly.assertThat(entry.getValue().size()).isEqualTo(HEROES_NUMBER_BY_TOWN.get(entry.getKey().getTownName()));
        }
        softly.assertAll();
    }

    @Test
    void shouldAddDLCTownsAndHeroesOnDLCEnabling() {
        List<String> alphabeticalTowns = getAlphabeticalTownsWithDLC();
        int iterator = 0;

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_NO_DLC);

        gameLobby.enableDLC();

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_WITH_DLC);
        for (Map.Entry<Town, List<Hero>> entry : gameLobby.getPlayableTownsWithHeroesList().entrySet()) {
            softly.assertThat(entry.getKey().getTownName()).isEqualTo(alphabeticalTowns.get(iterator++));
            softly.assertThat(entry.getValue().size()).isEqualTo(HEROES_NUMBER_BY_TOWN.get(entry.getKey().getTownName()));
        }
        softly.assertAll();
    }

    @Test
    void shouldNotAddDLCTownsAndHeroesOnDLCEnablingIfDLCTownAlreadyAdded() {
        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_NO_DLC);

        gameLobby.enableDLC();

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_WITH_DLC);

        gameLobby.enableDLC();

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_WITH_DLC);
        softly.assertAll();
    }

    @Test
    void shouldRemoveDLCTownsAndHeroesOnDLCDisabling() {
        List<String> alphabeticalTowns = getAlphabeticalTowns();
        int iterator = 0;

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_NO_DLC);

        gameLobby.enableDLC();

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_WITH_DLC);

        gameLobby.disableDLC();

        softly.assertThat(gameLobby.getPlayableTownsWithHeroesList().size()).isEqualTo(TOWNS_NUMBER_NO_DLC);
        for (Map.Entry<Town, List<Hero>> entry : gameLobby.getPlayableTownsWithHeroesList().entrySet()) {
            softly.assertThat(entry.getKey().getTownName()).isEqualTo(alphabeticalTowns.get(iterator++));
            softly.assertThat(entry.getValue().size()).isEqualTo(HEROES_NUMBER_BY_TOWN.get(entry.getKey().getTownName()));
        }
        softly.assertAll();
    }

    @Test
    void shouldReturnListOfHeroesForSelectedTown() {
        TOWNS.forEach(townName -> assertEquals(HEROES_NUMBER_BY_TOWN.get(townName),
                gameLobby.getHeroesFromTown(new Town(townName, List.of())).size()));
    }

    @Test
    void shouldThrowNoSuchElementExceptionIfTownNoExist() {
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> gameLobby.getHeroesFromTown(new Town(DLC_TOWN, List.of())));

        String expectedMessage = NO_SUCH_TOWN + DLC_TOWN;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldTrowNoSuchElementExceptionIfHeroNotFound() {
        Exception exception = assertThrows(NoSuchElementException.class, () ->
                gameLobby.selectHeroByName(new Town("Castle", List.of()), "nonExisted"));

        String expectedMessage = HERO_NOT_FOUND + "nonExisted";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnSelectedHeroIfExistAndRemoveFromAvailableHeroesList() {
        String tyris = "Tyris";
        Town town = new Town("Castle", List.of());
        Hero existedHero = new Hero(tyris, "Knight");

        assertEquals(existedHero, gameLobby.selectHeroByName(town, tyris));
        assertFalse(gameLobby.getHeroesFromTown(town).contains(existedHero));
    }


    private List<String> getAlphabeticalTowns() {
        List<String> alphabeticalTowns = new ArrayList<>(TOWNS);
        Collections.sort(alphabeticalTowns);
        return alphabeticalTowns;
    }

    private List<String> getAlphabeticalTownsWithDLC() {
        List<String> alphabeticalTowns = new ArrayList<>(TOWNS);
        alphabeticalTowns.add(DLC_TOWN);
        Collections.sort(alphabeticalTowns);
        return alphabeticalTowns;
    }

}