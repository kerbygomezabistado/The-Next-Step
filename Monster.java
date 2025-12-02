import java.util.Random;

public class Monster extends Character {
    private Random rand = new Random();

    public Monster(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
    }

    @Override
    public int attack() {
        return rand.nextInt(getAttackPower() / 2) + getAttackPower() / 2;
    }
}
 