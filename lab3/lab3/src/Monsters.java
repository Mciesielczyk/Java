public class Monsters {
    public static int monstersHealth = 110;

    public static final Monster andariel = new Monster(10, 70) {


        @Override
        public void takeHit(int damage) {
            health -= damage;
            if (health < 0) health = 0;
            monstersHealth -= damage;
            if (monstersHealth < 0) monstersHealth = 0;
        }
    };

    public static final Monster blacksmith = new Monster(100, 25) {


        @Override
        public void takeHit(int damage) {
            health -= (5 + damage);
            if (health < 0) health = 0;
            monstersHealth -= (5 + damage);
            if (monstersHealth < 0) monstersHealth = 0;
        }
    };
}