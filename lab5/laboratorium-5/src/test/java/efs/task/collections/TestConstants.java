package efs.task.collections;

import efs.task.collections.entity.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConstants {
    public static final int TOWNS_NUMBER_WITH_DLC = 10;
    public static final int TOWNS_NUMBER_NO_DLC = 9;
    public static final int HEROES_NUMBER_NO_DLC = 156;
    public static final int DLC_TOWNS_NUMBER = 1;
    public static final int DLC_HEROES_NUMBER = 17;
    public static final String DLC_TOWN = "Cove";
    public static final Hero TEST_HERO_STYG_WITCH = new Hero("Styg", "Witch");
    public static final Hero TEST_HERO_STYG_BEST = new Hero("Styg", "Beastmaster");

    public static final Map<String, Integer> HEROES_NUMBER_BY_TOWN = new HashMap<>() {{
        put("Castle", 19);
        put("Rampart", 17);
        put("Tower", 17);
        put("Inferno", 17);
        put("Conflux", 16);
        put("Necropolis", 17);
        put("Fortress", 17);
        put("Dungeon", 18);
        put("Stronghold", 18);
        put("Cove", 17);
    }};

    public static final List<String> TOWNS = new ArrayList<>() {{
        add("Castle");
        add("Conflux");
        add("Dungeon");
        add("Fortress");
        add("Inferno");
        add("Necropolis");
        add("Rampart");
        add("Stronghold");
        add("Tower");
    }};
}
