public class ExtraordinaryVillager extends Villager{

    public enum Skill{
        IDENTIFY("I will identify items for you at no cost."),
        SHELTER("I can offer you poor shelter.");
        private String description;

        Skill(String description) {
            this.description = description;
        }
    }
    private Skill skill;
    public ExtraordinaryVillager(int wiek, String name,Skill skill) {
        super(wiek, name);
        this.skill=skill;
    }

    @Override
    public void sayHello(){
        System.out.printf("Greetings traveler... I'm %s and I'm %d years old. %s\n",this.name,this.wiek,this.skill.description);

    }

    @Override
    public void takeHit(int damage) {
        health=0;
        System.out.println(name+" has died");
    }

    @Override
    public void attack(Fighter victim) {
        int damage = 0;
        victim.takeHit(damage);
    }
}
