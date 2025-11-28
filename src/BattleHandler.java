import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleHandler {
    private final Team team;
    private final GameGUI gui;
    private final Random random = new Random();
    private List<Zombie> zombies;
    private int currentStage;
    private int battleState = 0;
    private Character currentCharacter;
    private int currentSkill;

    public BattleHandler(Team team, GameGUI gui) {
        this.team = team;
        this.gui = gui;
    }

    public void startBattle(int stage) {
        this.currentStage = stage;
        int zombieCount = 1 + random.nextInt(3);
        gui.appendText("Zombies attacking: " + zombieCount);
        zombies = createZombies(zombieCount);
        showZombies();
        team.reduceCooldowns();
        showTeamStatus();
        battleState = 0;
        chooseCharacter();

    }



    public void handleBattleChoice(int choice) {

        switch (battleState) {

            case 0: handleCharacterChoice(choice); break;

            case 1: handleActionChoice(choice); break;

            case 2: handleSkillChoice(choice); break;

            case 3: handleTargetChoice(choice); break;

        }

    }



    private void handleCharacterChoice(int choice) {

        if (choice < 1 || choice > team.getMembers().size()) return;

        currentCharacter = team.getMembers().get(choice - 1);

        if (!currentCharacter.isAlive()) {

            gui.appendText(currentCharacter.getName() + " is down and cannot act. Choose another.");

            chooseCharacter();

            return;

        }

        gui.appendText(currentCharacter.getName() + " is ready to act!");

        battleState = 1;

        chooseAction();

    }



    private void handleActionChoice(int choice) {

        if (choice == 1) {

            battleState = 3;

            chooseTarget();

        } else if (choice == 2) {

            battleState = 2;

            chooseSkill();

        }

    }



    private void handleSkillChoice(int choice) {

        if (choice < 1 || choice > 3) return;

        currentSkill = choice;

        if (currentCharacter.cooldowns[currentSkill - 1] > 0) {

            gui.appendText("That skill is on cooldown for " + currentCharacter.cooldowns[currentSkill - 1] + " more turns.");

            chooseAction();

            return;

        }

        battleState = 3;

        chooseTarget();

    }



    private void handleTargetChoice(int choice) {

        if (choice < 1 || choice > zombies.size()) return;

        Zombie target = zombies.get(choice - 1);

        if (!target.isAlive()) {

            gui.appendText("That zombie is already defeated. Choose another target.");

            chooseTarget();

            return;

        }

        if (battleState == 3) {

            if (currentSkill == 0) {

                int damage = 15;

                target.takeDamage(damage);

                gui.appendText(currentCharacter.getName() + " hits " + target.getName() + " for " + damage + " damage!");

            } else {

                useCharacterSkill(currentCharacter, currentSkill, target);

                currentCharacter.cooldowns[currentSkill - 1] = (currentSkill == 1) ? 1 : 3;

            }

        }

        checkBattleEnd();

    }



    private void useCharacterSkill(Character character, int skill, Zombie target) {

        if (character instanceof Mah) {

            useMahSkill(skill, target);

        } else if (character instanceof Rey) {

            useReySkill(skill, target);

        } else if (character instanceof Tess) {

            useTessSkill(skill, target);

        }

    }



    private void useMahSkill(int skill, Zombie target) {

        switch (skill) {

            case 1:

                target.takeDamage(30);

                target.takeDamage(30);

                gui.appendText("Mah uses Observe on " + target.getName() + " for 60 damage!");

                break;

            case 2:

                gui.appendText("Mah activates Intuition - next attack will deal bonus damage!");

                break;

            case 3:

                for (Zombie z : zombies) {

                    z.takeDamage(45);

                    z.takeDamage(45);

                }

                gui.appendText("Mah uses Analytical Mind on all zombies for 90 damage each!");

                break;

        }

    }



    private void useReySkill(int skill, Zombie target) {

        switch (skill) {

            case 1:

                for (Zombie z : zombies) {

                    int damage = 10 + random.nextInt(11);

                    z.takeDamage(damage);

                    z.takeDamage(damage);

                }

                gui.appendText("Rey uses Riddles on all enemies!");

                break;

            case 2:

                target.takeDamage(25);

                target.takeDamage(25);

                gui.appendText("Rey uses Strat Master on " + target.getName() + " for 50 damage!");

                break;

            case 3:

                for (Zombie z : zombies) {

                    int damage = 30 + random.nextInt(11);

                    z.takeDamage(damage);

                    z.takeDamage(damage);

                }

                gui.appendText("Rey uses Mind Bank on all enemies!");

                break;

        }

    }



    private void useTessSkill(int skill, Zombie target) {

        switch (skill) {

            case 1:

                int damage = 35 + random.nextInt(11);

                target.takeDamage(damage);

                target.takeDamage(damage);

                gui.appendText("Tess uses Practicality on " + target.getName() + " for " + (damage * 2) + " damage!");

                break;

            case 2:

                gui.appendText("Tess activates Agility - next attack will hit twice!");

                break;

            case 3:

                for (Zombie z : zombies) {

                    int dmg = 50 + random.nextInt(21);

                    z.takeDamage(dmg);

                    z.takeDamage(dmg);

                }

                gui.appendText("Tess uses Endurance on all enemies!");

                break;

        }

    }



    private void chooseCharacter() {

        gui.appendText("\nChoose a character to act:");

        String[] options = new String[3];

        for (int i = 0; i < team.getMembers().size(); i++) {

            Character c = team.getMembers().get(i);

            options[i] = c.getName() + " (HP: " + c.getHP() + ")";

        }

        gui.updateButtons(options);

    }



    private void chooseAction() {

        gui.appendText("Choose action for " + currentCharacter.getName() + ":");

        gui.updateButtons(new String[]{"Normal Attack", "Use Skill", ""});

    }



    private void chooseSkill() {

        gui.appendText("Choose skill for " + currentCharacter.getName() + ":");

        String skill1 = currentCharacter.skillName(1) + " (CD: " + currentCharacter.cooldowns[0] + ")";

        String skill2 = currentCharacter.skillName(2) + " (CD: " + currentCharacter.cooldowns[1] + ")";

        String skill3 = currentCharacter.skillName(3) + " (CD: " + currentCharacter.cooldowns[2] + ")";

        gui.updateButtons(new String[]{skill1, skill2, skill3});

    }



    private void chooseTarget() {

        gui.appendText("Choose target:");

        String[] options = new String[zombies.size()];

        for (int i = 0; i < zombies.size(); i++) {

            Zombie z = zombies.get(i);

            options[i] = z.getName() + " (HP: " + z.getHP() + ")";

        }

        gui.updateButtons(options);

    }



    private void checkBattleEnd() {

        removeDeadZombies();

        if (!anyZombiesAlive()) {

            gui.appendText("All zombies defeated!");

            gui.endBattle(true, currentStage);

            return;

        }

        zombiesAttack();

        if (!team.isAnyAlive()) {

            gui.appendText("Your team has been defeated!");

            gui.endBattle(false, currentStage);

            return;

        }

        team.reduceCooldowns();

        showTeamStatus();

        showZombies();

        battleState = 0;

        chooseCharacter();

    }



    private void zombiesAttack() {

        gui.appendText("Zombies attack!");

        for (Zombie zombie : zombies) {

            if (zombie.isAlive() && team.isAnyAlive()) {

                Character target = team.getRandomAliveMember();

                int damage = zombie.attack();

                target.takeDamage(damage);

                gui.appendText(zombie.getName() + " deals " + damage + " damage to " + target.getName());

            }

        }

    }



    private List<Zombie> createZombies(int count) {

        List<Zombie> zombies = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            zombies.add(new Zombie("Zombie " + (i + 1)));

        }

        return zombies;

    }



    private void showZombies() {

        gui.appendText("Enemies:");

        for (Zombie z : zombies) {

            gui.appendText("- " + z.getName() + " | HP: " + z.getHP());

        }

    }



    private void showTeamStatus() {

        gui.appendText("Team Status:");

        for (Character c : team.getMembers()) {

            String status = c.isAlive() ? "ALIVE" : "DOWN";

            gui.appendText("- " + c.getName() + ": HP " + c.getHP() + " Cooldowns: " + c.cooldownString());

        }

    }



    private boolean anyZombiesAlive() {

        for (Zombie z : zombies) {

            if (z.isAlive()) return true;

        }

        return false;

    }



    private void removeDeadZombies() {

        zombies.removeIf(z -> !z.isAlive());

    }

} 