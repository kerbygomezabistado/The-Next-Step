import java.util.Random;

public class Player extends Character {
    private Random rand = new Random();

    public Player(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
    }

    @Override
    public int attack() {
        return rand.nextInt(getAttackPower() / 2) + getAttackPower() / 2;
    }

    // 3 unique skills
    public int skill1() { // attack slash2
        return rand.nextInt(getAttackPower()) + getAttackPower();
    }

    public int skill2() { // heal
        return rand.nextInt(25) + 15; // heals 15-39 HP
    }

    public int skill3() { // attack nga naay chance ma hit or wala
        if (rand.nextInt(100) < 70) { // 70% chance
            return rand.nextInt(getAttackPower() * 3 - getAttackPower()) + getAttackPower();
        } else {
            return 0; // missed
        }
    }

    // Static method to select 3 character types
    public static Player chooseCharacter(int choice) {
        switch (choice) {
            case 1: return new Player("Mah", 120, 30);
            case 2: return new Player("Rey", 90, 40);
            case 3: return new Player("Tess", 100, 35);
            default: return new Player("Mah", 120, 30);
        }
    }
}