public class Villager implements Fighter {
    public int wiek;
    public String name;
    public double health;

    public Villager(int wiek, String name) {
        this.wiek = wiek;
        this.name = name;
        this.health = 100;
    }

    public String getName() {
        return name;
    }
    public int getWiek() {
        return wiek;
    }
    public double getHealth() {
        return health;
    }
    public void setWiek(int wiek){
        this.wiek = wiek;
    }
    public void setName(String name){
        this.name = name;
    }
    public void sayHello(){
        System.out.printf("Greetings traveler... I'm %s and I'm %d years old.\n",this.name,this.wiek);
    }

    @Override
    public void attack(Fighter victim) {
        int damage = (int)((100-getWiek()*0.5)/10);
        //System.out.println(damage);
        victim.takeHit(damage);
    }

    @Override
    public void takeHit(int damage) {
         health-=damage;
         if(health<=0){
             System.out.println(name+" has died");
         }
    }
}
