import java.util.List;
import java.util.Scanner;

public abstract class Character {

    protected String name;
    protected int hp;
    protected int baseAttack;
    protected int[] cooldowns = new int[3];
    protected boolean nextAttackDouble = false;
    protected int nextAttackBonus = 0;

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int dmg) {
        if (hp <= 0) return;
        hp = Math.max(0, hp - dmg);
        System.out.println(name + " takes " + dmg + " damage.");
    }

    public void reduceCooldowns() {
        for (int i = 0; i < cooldowns.length; i++) if (cooldowns[i] > 0) cooldowns[i]--;
    }

    public void resetCooldowns() {
        for (int i = 0; i < cooldowns.length; i++) cooldowns[i] = 0;
    }

    public String cooldownString() {
        return "[" + cooldowns[0] + "," + cooldowns[1] + "," + cooldowns[2] + "]";
    }

    public void act(Scanner sc, List<Zombie> zombies) {
        System.out.println("1) Normal Attack");
        System.out.println("2) Use Skill");
        int a = Utils.readChoice(sc, 1, 2);
        if (a == 1) normalAttack(sc, zombies);
        else useSkillMenu(sc, zombies);
    }

    private void normalAttack(Scanner sc, List<Zombie> zombies) {
        Zombie z = Utils.selectZombie(sc, zombies);
        int dmg = baseAttack;
        if (nextAttackDouble) {
            z.takeDamage(dmg);
            z.takeDamage(dmg);
            System.out.println(name + " performs a doubled normal attack on " + z.getName() + " for " + (dmg * 2) + " total.");
            nextAttackDouble = false;
        } else {
            z.takeDamage(dmg + nextAttackBonus);
            System.out.println(name + " hits " + z.getName() + " for " + (dmg + nextAttackBonus) + " damage.");
            nextAttackBonus = 0;
        }
    }

    private void useSkillMenu(Scanner sc, List<Zombie> zombies) {
        System.out.println("Choose a skill:");
        System.out.println("1) " + skillName(1) + " (CD 1)");
        System.out.println("2) " + skillName(2) + " (CD 3)");
        System.out.println("3) " + skillName(3) + " (CD 3)");
        int s = Utils.readChoice(sc, 1, 3);
        if (cooldowns[s - 1] > 0) {
            System.out.println("That skill is on cooldown for " + cooldowns[s - 1] + " more turns.");
            return;
        }
        useSkill(s, sc, zombies);
        cooldowns[s - 1] = (s == 1) ? 1 : 3;
    }

    protected void singleTargetSkill(List<Zombie> zombies, Scanner sc, int dmg) {
        Zombie z = Utils.selectZombie(sc, zombies);
        z.takeDamage(dmg);
        z.takeDamage(dmg);
        System.out.println(name + " uses a focused skill on " + z.getName() + " for " + (dmg * 2) + " total damage.");
    }

    protected void allTargetSkill(List<Zombie> zombies, int dmg) {
        for (Zombie z : zombies) {
            z.takeDamage(dmg);
            z.takeDamage(dmg);
        }
        System.out.println(name + " uses an ultimate dealing " + (dmg * 2) + " to all targets.");
    }

    protected abstract String skillName(int i);
    protected abstract void useSkill(int s, Scanner sc, List<Zombie> zombies);
}
