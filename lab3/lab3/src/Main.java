import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Villager vil1 = new Villager(30,"Kashya");
        //Villager vil2 = new Villager(40,"Akara");
        Villager vil3 = new Villager(50,"Gheed");
        //Villager vil4 = new Villager(85,"Deckard");
        Villager vil5 = new Villager(35,"Warriv");
        Villager vil6 = new Villager(25,"Flawia");

        ExtraordinaryVillager deckardCain = new ExtraordinaryVillager(85,"Deckard Cain",  ExtraordinaryVillager.Skill.IDENTIFY);
        ExtraordinaryVillager akara = new ExtraordinaryVillager(40, "Akara", ExtraordinaryVillager.Skill.SHELTER);

        Object objectDeckardCain = deckardCain;
        Object objectAkara = akara;

        vil1.sayHello();
        //vil2.sayHello();
        vil3.sayHello();
        //vil4.sayHello();
        vil5.sayHello();
        vil6.sayHello();

        List<Villager> villagers = new ArrayList<>();
        villagers.add(vil1);
        villagers.add(vil3);
        villagers.add(vil5);
        villagers.add(vil6);
        villagers.add(deckardCain);
        villagers.add(akara);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(Monsters.blacksmith);
        monsters.add(Monsters.andariel);

       // vil2 = new ExtraordinaryVillager(40, "Akhara",ExtraordinaryVillager.Skill.IDENTIFY);
        //vil2.sayHello();
        //vil4 = new ExtraordinaryVillager(vil4.wiek,vil4.name,ExtraordinaryVillager.Skill.SHELTER);
        //vil4.sayHello();




        Random random = new Random();

        while(Monsters.monstersHealth>0){

            List<Villager> aliveVillagers = new ArrayList<>();
            for (Villager villager : villagers) {
                if (villager.getHealth() > 0) {
                    aliveVillagers.add(villager);
                }
            }

            List<Monster> aliveMonsters = new ArrayList<>();
            for (Monster monster : monsters) {
                if (monster.getHealth() > 0) {
                    aliveMonsters.add(monster);
                }
            }

            if (aliveVillagers.isEmpty() || aliveMonsters.isEmpty()) {
                System.out.println("Brak żywych uczestników walki. Gra zakończona!");
                break;
            }

            Villager randomVillager = null;
            if (!aliveVillagers.isEmpty()) {
                randomVillager = aliveVillagers.get(random.nextInt(aliveVillagers.size()));
            }

            // Losowanie potwora, jeżeli lista nie jest pusta
            Monster randomMonster = null;
            if (!aliveMonsters.isEmpty()) {
                randomMonster = aliveMonsters.get(random.nextInt(aliveMonsters.size()));
            }

            randomMonster.attack(randomVillager);
            randomVillager.attack(randomMonster);



            System.out.println("Aktualne zdrowie potworów: " + Monsters.monstersHealth);
            System.out.println("Aktualne zdrowie osadników: ");
            for (Villager villager : aliveVillagers) {
                System.out.println(villager.getName() + ": " + villager.getHealth());
            }


            if (Monsters.monstersHealth <= 0) {
                System.out.println("Obozowisko ocalone!");
                break;
            }
        }
    }
}