import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleHandler {
    private final Team team;
    private final GameGUI gui;
    private final Random random = new Random();

    public BattleHandler(Team team, GameGUI gui) {
        this.team = team;
        this.gui = gui;
    }

    public boolean startBattle() {
        int zombieCount = 1 + random.nextInt(3);
        gui.appendText("Zombies attacking: " + zombieCount);

        List<Zombie> zombies = createZombies(zombieCount);
        showZombies(zombies);

        int turn = 1;
        while (team.isAnyAlive() && anyZombiesAlive(zombies)) {
            gui.appendText("\n--- TURN " + turn + " ---");

            // Team attacks
            teamAttacks(zombies);
            removeDeadZombies(zombies);

            if (!anyZombiesAlive(zombies)) {
                gui.appendText("All zombies defeated!");
                return true;
            }

            // Zombies attack
            zombiesAttack(zombies);

            if (!team.isAnyAlive()) {
                gui.appendText("Your team has been defeated!");
                return false;
            }

            showBattleStatus(zombies);
            turn++;

            // Small delay between turns for readability
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }

        return team.isAnyAlive();
    }

    private List<Zombie> createZombies(int count) {
        List<Zombie> zombies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            zombies.add(new Zombie("Zombie " + (i + 1)));
        }
        return zombies;
    }

    private void teamAttacks(List<Zombie> zombies) {
        gui.appendText("Your team attacks!");

        for (Character member : team.getMembers()) {
            if (member.isAlive() && anyZombiesAlive(zombies)) {
                Zombie target = getRandomAliveZombie(zombies);
                if (target != null) {
                    // Use normal attack damage (baseAttack is protected, so we'll use a fixed value)
                    int damage = 15 + random.nextInt(6); // Standard attack damage
                    target.takeDamage(damage);
                    gui.appendText(member.getName() + " hits " + target.getName() + " for " + damage + " damage");

                    if (!target.isAlive()) {
                        gui.appendText(target.getName() + " defeated!");
                    }
                }
            }
        }
    }

    private void zombiesAttack(List<Zombie> zombies) {
        gui.appendText("Zombies attack!");

        for (Zombie zombie : zombies) {
            if (zombie.isAlive() && team.isAnyAlive()) {
                Character target = team.getRandomAliveMember();
                int damage = zombie.attack();
                target.takeDamage(damage);
                gui.appendText(zombie.getName() + " deals " + damage + " damage to " + target.getName());

                if (!target.isAlive()) {
                    gui.appendText(target.getName() + " is down!");
                }
            }
        }
    }

    private void showZombies(List<Zombie> zombies) {
        gui.appendText("Enemies:");
        for (Zombie z : zombies) {
            gui.appendText("- " + z.getName() + " | HP: " + z.getHP());
        }
    }

    private void showBattleStatus(List<Zombie> zombies) {
        gui.appendText("Battle Status:");
        gui.appendText("Your team:");
        for (Character c : team.getMembers()) {
            String status = c.isAlive() ? "ALIVE" : "DOWN";
            gui.appendText("- " + c.getName() + ": HP " + c.getHP() + " [" + status + "]");
        }

        gui.appendText("Enemies:");
        for (Zombie z : zombies) {
            if (z.isAlive()) {
                gui.appendText("- " + z.getName() + ": HP " + z.getHP());
            }
        }
    }

    private boolean anyZombiesAlive(List<Zombie> zombies) {
        for (Zombie z : zombies) {
            if (z.isAlive()) return true;
        }
        return false;
    }

    private void removeDeadZombies(List<Zombie> zombies) {
        zombies.removeIf(z -> !z.isAlive());
    }

    private Zombie getRandomAliveZombie(List<Zombie> zombies) {
        List<Zombie> aliveZombies = new ArrayList<>();
        for (Zombie z : zombies) {
            if (z.isAlive()) aliveZombies.add(z);
        }
        return aliveZombies.isEmpty() ? null : aliveZombies.get(random.nextInt(aliveZombies.size()));
    }
}
