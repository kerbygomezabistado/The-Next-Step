// This is for Abstraction
public abstract class Character {
    private String name;
    private int hp;
    private int attackPower;

    public Character(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public String getName() { return name; }
    public int getHP() { return hp; }
    public void setHP(int hp) { this.hp = Math.max(hp, 0); }
    public int getAttackPower() { return attackPower; }

    public abstract int attack(); // default attack
    public boolean isAlive() { return hp > 0; }
}

