import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private JTextArea gameText;
    private JButton skill1Btn, skill2Btn, skill3Btn;
    private JProgressBar playerHPBar, monsterHPBar;
    private JLabel playerAtkLabel1, playerAtkLabel2, playerHealLabel, monsterAtkLabel;
    private Player player;
    private Stage[] stages;
    private int currentStage = 0;

    // Stage stories
    private String[] stageStories = {
            "You enter the Forest Edge, a peaceful yet eerie place. Suddenly, a small Goblin jumps out from the bushes!",
            "The forest gives way to a dark, damp cave. Echoes of strange creatures surround you. A Cave Bat swoops down to attack!",
            "You reach an abandoned village, the wind howls through broken houses. A Shadow Thief emerges from the shadows to challenge you.",
            "The path leads to Ancient Ruins, where mystical energies linger. A Stone Golem rises from the rubble, ready to fight.",
            "At last, you arrive at the Dragon’s Lair. Flames flicker across the cavern walls. The mighty Fire Dragon awaits your challenge!"
    };

    public GameGUI() {
        setTitle("[Stage "+(currentStage+1)+"] "+"The Next Step Text-Based Battle Game - Player vs Monster");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Battle log
        gameText = new JTextArea();
        gameText.setEditable(false);
        gameText.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        add(new JScrollPane(gameText), BorderLayout.CENTER);

        // Player panel
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerHPBar = new JProgressBar();
        playerHPBar.setStringPainted(true);
        playerAtkLabel1 = new JLabel("Slash Attack: 0");
        playerAtkLabel2 = new JLabel("Power Strike Attack: 0");
        playerHealLabel = new JLabel("Heal: 0");
        playerPanel.add(Box.createVerticalStrut(20));
        playerPanel.add(playerHPBar);
        playerPanel.add(Box.createVerticalStrut(10));
        playerPanel.add(playerAtkLabel1);
        playerPanel.add(playerAtkLabel2);
        playerPanel.add(playerHealLabel);
        add(playerPanel, BorderLayout.WEST);

        // Monster panel
        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.Y_AXIS));
        monsterPanel.setBorder(BorderFactory.createTitledBorder("Monster"));
        monsterHPBar = new JProgressBar();
        monsterHPBar.setStringPainted(true);
        monsterAtkLabel = new JLabel("Attack: 0");
        monsterPanel.add(Box.createVerticalStrut(20));
        monsterPanel.add(monsterHPBar);
        monsterPanel.add(Box.createVerticalStrut(10));
        monsterPanel.add(monsterAtkLabel);
        add(monsterPanel, BorderLayout.EAST);

        // buttons sa mga skill guys
        JPanel buttonPanel = new JPanel();
        skill1Btn = new JButton("Slash");
        skill2Btn = new JButton("Heal");
        skill3Btn = new JButton("Power Strike (70% chance)");
        buttonPanel.add(skill1Btn);
        buttonPanel.add(skill2Btn);
        buttonPanel.add(skill3Btn);
        add(buttonPanel, BorderLayout.SOUTH);

        // mao ni first mo gawas ig run diri mo select ug character
        String[] options = {"Mah", "Rey", "Tess"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose your character",
                "Character Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane. INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        player = Player.chooseCharacter(choice + 1);

        // Initialize stages
        stages = new Stage[5];
        for (int i = 0; i < 5; i++) stages[i] = new Stage(i + 1);

        // Initialize HP bars and attack values
        playerHPBar.setMaximum(player.getHP());
        playerHPBar.setValue(player.getHP());
        playerHPBar.setString(player.getName() + " HP: " + player.getHP());
        playerAtkLabel1.setText("Slash Attack: " + player.getAttackPower() + " - " + ((player.getAttackPower() * 2)-1));
        playerAtkLabel2.setText("Power Strike Attack: " + player.getAttackPower() + " - " + (((player.getAttackPower() * 3) - player.getAttackPower())+player.getAttackPower()-1));
        playerHealLabel.setText("Heal: 15 - 39");

        Monster monster = stages[currentStage].getMonster();
        monsterHPBar.setMaximum(monster.getHP());
        monsterHPBar.setValue(monster.getHP());
        monsterHPBar.setString(monster.getName() + " HP: " + monster.getHP());
        monsterAtkLabel.setText("Attack: " + monster.getAttackPower()/2 + " - " + (((monster.getAttackPower()/2)*2)-1));

        // Stage story
        gameText.append("Welcome " + player.getName() + "!\n");
        gameText.append("=== THE NEXT STEP: Dr. RICO's Experiment ===");
        gameText.append("\n");
        gameText.append("\n");
        gameText.append("Year 2098 at Abistado College of Technology, once a proud beacon of\n");
        gameText.append("innovation now lies in ruins. Its shattered halls have been swallowed by nature twisted");
        gameText.append("into a forest where reality bends and shadows whisper.");
        gameText.append("Everything you see is just an hallucinations\n");
        gameText.append("\n");
        gameText.append("\n");
        gameText.append("\n");
        gameText.append("\n");
        gameText.append("Stage " + (currentStage + 1) + ": " + stageStories[currentStage] + "\n\n");

        // Button actions
        skill1Btn.addActionListener(e -> playerTurn(1));
        skill2Btn.addActionListener(e -> playerTurn(2));
        skill3Btn.addActionListener(e -> playerTurn(3));
    }

    private void playerTurn(int skill) {
        Monster monster = stages[currentStage].getMonster();
        gameText.append("--- Player Turn ---\n");

        switch (skill) {
            case 1:
                int dmg1 = player.skill1();
                monster.setHP(monster.getHP() - dmg1);
                gameText.append("You used Slash! It deals " + dmg1 + " damage.\n");
                break;
            case 2:
                int heal = player.skill2();
                int new_hp = player.getHP() + heal;
                gameText.append("You used Heal! You recieved " + heal + " HP.\n");
                if (currentStage == 4) {
                    gameText.append("You restored " + heal + " HP.\n");
                    player.setHP(new_hp);
                }
                else {
                    if (new_hp > 150)
                        gameText.append("You restored " + (150 - player.getHP()) + " HP only. Reached limit of HP.\n");
                    else
                        gameText.append("You restored " + heal + " HP.\n");
                    player.setHP(Math.min(new_hp, 150));
                }
                break;
            case 3:
                int dmg3 = player.skill3();
                if (dmg3 > 0)
                    gameText.append("You used Power Strike! It deals " + dmg3 + " damage.\n");
                else
                    gameText.append("Power Strike missed!\n");
                monster.setHP(monster.getHP() - dmg3);
                break;
        }

        // Monster turn
        if (monster.isAlive()) {
            int monsterDmg = monster.attack();
            player.setHP(player.getHP() - monsterDmg);
            gameText.append(monster.getName() + " attacks you for " + monsterDmg + " damage.\n\n");
        }

        updateGameStatus();
    }

    private void updateGameStatus() {
        Monster monster = stages[currentStage].getMonster();

        // Update HP bars
        playerHPBar.setValue(player.getHP());
        playerHPBar.setString(player.getName() + " HP: " + player.getHP());

        monsterHPBar.setValue(monster.getHP());
        monsterHPBar.setString(monster.getName() + " HP: " + monster.getHP());

        gameText.append(player.getName() + " HP: " + player.getHP() +
                " | " + monster.getName() + " HP: " + monster.getHP() + "\n\n");

        if (!player.isAlive()) {
            gameText.append("You were defeated... Game Over!\n");
            disableSkills();
        } else if (!monster.isAlive()) {
            gameText.append("You defeated " + monster.getName() + "!\n");
            gameText.append("\n====================\n\n");
            currentStage++;
            this.setTitle("[Stage "+(currentStage+1)+"] "+"The Next Step Text-Based Battle Game - Player vs Monster");
            if (currentStage < stages.length) {
                Monster nextMonster = stages[currentStage].getMonster();
                monsterHPBar.setMaximum(nextMonster.getHP());
                monsterHPBar.setValue(nextMonster.getHP());
                monsterHPBar.setString(nextMonster.getName() + " HP: " + nextMonster.getHP());
                monsterAtkLabel.setText("Attack: " + nextMonster.getAttackPower()/2 + " - " + (((nextMonster.getAttackPower()/2)*2)-1));

                if (currentStage == 4) {
                    player.setHP(player.getHP() + 20);
                    gameText.append("\nBonus of 20 HP for reaching stage 5!\n\n");

                    // Update HP bars
                    playerHPBar.setValue(player.getHP());
                    playerHPBar.setString(player.getName() + " HP: " + player.getHP());
                }

                // Stage story
                gameText.append("Stage " + (currentStage + 1) + ": " + stageStories[currentStage] + "\n\n");
            } else {
                gameText.append("Congratulations! You cleared all stages!\n");
                disableSkills();
            }
        }
    }

    private void disableSkills() {
        skill1Btn.setEnabled(false);
        skill2Btn.setEnabled(false);
        skill3Btn.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI game = new GameGUI();
            game.setVisible(true);
        });
    }
}