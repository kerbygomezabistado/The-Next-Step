import javax.swing.*;

import java.awt.*;

import java.util.Random;



public class GameGUI extends JFrame {

    private JTextArea gameText;

    private JButton choice1, choice2, choice3;

    private Team team;

    private StorylineHandler story;

    private BattleHandler battle;

    private int currentStage = 0;

    private Random random = new Random();



    public GameGUI() {

        setTitle("THE NEXT STEP: Dr. RICO's Experiment");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        setSize(800, 600);



        // Set black background

        getContentPane().setBackground(Color.BLACK);



        team = new Team();

        story = new StorylineHandler();

        battle = new BattleHandler(team, this);



        // Game text area with black background

        gameText = new JTextArea(20, 60);

        gameText.setEditable(false);

        gameText.setLineWrap(true);

        gameText.setWrapStyleWord(true);

        gameText.setFont(new Font("Monospaced", Font.PLAIN, 12));

        gameText.setBackground(Color.BLACK);

        gameText.setForeground(Color.WHITE);

        gameText.setCaretColor(Color.WHITE);



        JScrollPane scrollPane = new JScrollPane(gameText);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        scrollPane.getViewport().setBackground(Color.BLACK);

        add(scrollPane, BorderLayout.CENTER);



        // Choice buttons

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.setBackground(Color.BLACK);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        choice1 = new JButton("Start Game");

        choice2 = new JButton("");

        choice3 = new JButton("Exit");



        // Style buttons for black background

        styleButton(choice1);

        styleButton(choice2);

        styleButton(choice3);



        choice1.addActionListener(e -> handleChoice(1));

        choice2.addActionListener(e -> handleChoice(2));

        choice3.addActionListener(e -> handleChoice(3));



        buttonPanel.add(choice1);

        buttonPanel.add(choice2);

        buttonPanel.add(choice3);



        add(buttonPanel, BorderLayout.SOUTH);



        choice2.setEnabled(false);

        setVisible(true);



        showIntro();

    }



    private void styleButton(JButton button) {

        button.setBackground(Color.DARK_GRAY);

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

    }



    private void showIntro() {

        appendText("=== THE NEXT STEP: Dr. RICO's Experiment ===");

        appendText("");

        appendText("Year 2098. Abistado College of Technology stands as a crumbling monument");

        appendText("to forgotten ambitions. Three students—Mah, the logical analyst;");

        appendText("Rey, the strategic thinker; and Tess, the practical survivor—find");

        appendText("themselves trapped in an underground facility beneath their campus.");

        appendText("");

        appendText("A distorted voice echoes through the metallic corridors:");

        appendText("\"I am Dr. RICO. Survive my experiment or become part of it.\"");

        appendText("");

        appendText("The heavy blast doors seal shut. There is no escape, only forward.");

        appendText("Each choice matters. Each puzzle could mean life or death.");

        appendText("");

        appendText("Press Start to begin your descent...");

    }



    public void appendText(String text) {

        SwingUtilities.invokeLater(() -> {

            gameText.append(text + "\n");

            gameText.setCaretPosition(gameText.getDocument().getLength());

        });

    }



    private void handleChoice(int choice) {

        if (currentStage == 0) {

            if (choice == 1) startStage(1);

            else if (choice == 3) System.exit(0);

            return;

        }



        if (currentStage >= 1 && currentStage <= 5) {

            handleStageChoice(currentStage, choice);

        }



        // Handle game over restart

        if (currentStage == -1 && choice == 1) {

            restartGame();

        }

    }



    private void startStage(int stage) {

        currentStage = stage;

        story.showPuzzle(stage);

        updateButtons(story.getCurrentOptions());



        switch (stage) {

            case 1:

                showStage1();

                break;

            case 2:

                showStage2();

                break;

            case 3:

                showStage3();

                break;

            case 4:

                showStage4();

                break;

            case 5:

                showStage5();

                break;

        }

    }



    private void showStage1() {

        appendText("\n--- STAGE 1: THE ENTRANCE CHAMBER ---");

        appendText("");

        appendText("You stand in a circular chamber with walls of polished black metal.");

        appendText("Before you, a massive door is covered in intricate glyphs that pulse");

        appendText("with faint blue energy. The air hums with power.");

        appendText("");

        appendText("A faded mural on the wall shows a scientist descending into madness,");

        appendText("while dates and equations spiral around the images. Three glyphs");

        appendText("stand out among the hundreds:");

        appendText("");

        appendText("- One exactly matches the pattern in the mural");

        appendText("- One is larger than the others, radiating authority");

        appendText("- One glows with an inviting soft light");

        appendText("");

        appendText("Which glyph do you press?");

    }



    private void showStage2() {

        appendText("\n--- STAGE 2: LIBRARY OF ECHOES ---");

        appendText("");

        appendText("The door slides open to reveal a vast library stretching into darkness.");

        appendText("Dust-covered books line shelves that reach impossible heights.");

        appendText("Research notes and campus maps are scattered across central tables.");

        appendText("");

        appendText("You find a ledger with partial coordinates—fragments of a path");

        appendText("through the facility. Some notes are decades old, faded and precise.");

        appendText("Others are recent, scrawled in panic. The maps show conflicting routes.");

        appendText("");

        appendText("How do you reconstruct the path forward?");

    }



    private void showStage3() {

        appendText("\n--- STAGE 3: TRAINING HALL ---");

        appendText("");

        appendText("You enter a chamber filled with bizarre training equipment:");

        appendText("reaction testers, memory challenges, and physical obstacles.");

        appendText("A central console displays three color-coded switches.");

        appendText("");

        appendText("Warning lights flash red across the room. A faded circuit diagram");

        appendText("on the wall shows the proper sequence, but time is running out.");

        appendText("One switch bears an unknown symbol not on the diagram.");

        appendText("");

        appendText("Which switch do you flip?");

    }



    private void showStage4() {

        appendText("\n--- STAGE 4: OBSERVATION WING ---");

        appendText("");

        appendText("Observation windows reveal the horror of Dr. RICO's work:");

        appendText("students suspended in tanks, their bodies twisted and minds broken.");

        appendText("Logbooks show names systematically crossed out—failed experiments.");

        appendText("");

        appendText("A power stabilizer hums dangerously, threatening to overload.");

        appendText("The manual shows a safe 30-second disengagement procedure.");

        appendText("Smashing it would be faster but risk catastrophic failure.");

        appendText("");

        appendText("How do you handle the stabilizer?");

    }



    private void showStage5() {

        appendText("\n--- STAGE 5: THE HEART OF THE EXPERIMENT ---");

        appendText("");

        appendText("You've reached the central chamber. Dr. RICO's master console");

        appendText("displays the truth: the doctor has been dead for years.");

        appendText("His AI continues the experiments autonomously, trapped in a loop.");

        appendText("");

        appendText("The final choice is before you:");

        appendText("- Sabotage the entire facility, destroying the research");

        appendText("- Confront the AI directly in its core chamber");

        appendText("- Accept the AI's offer to continue the 'noble work'");

        appendText("");

        showTeamStatus();

        appendText("");

        appendText("Your decision will determine everything...");

    }



    private void handleStageChoice(int stage, int choice) {

        boolean correct = story.handleChoice(stage, choice);



        appendText("");

        appendText(">> " + story.getChoiceDescription(stage, choice));

        appendText("");



        if (correct) {

            appendText("SUCCESS! The logical choice proves correct.");

            appendText("No threats detected. The way forward opens.");

            team.resetCooldowns();

            showScene(stage);



            if (stage < 5) {

                startStage(stage + 1);

            } else {

                endGame(true, choice);

            }

        } else {

            appendText("FAILURE! Alarms scream through the facility!");

            appendText("Hostile entities detected—PREPARE FOR COMBAT!");

            startBattle(stage);

        }

    }



    private void startBattle(int stage) {

        appendText("\n--- BATTLE INITIATED ---");

        appendText("Zombies emerge from the shadows!");



        // Disable buttons during battle

        setButtonsEnabled(false);



        // Start battle in a separate thread to keep GUI responsive

        new Thread(() -> {

            boolean survived = battle.startBattle();



            SwingUtilities.invokeLater(() -> {

                if (survived && team.isAnyAlive()) {

                    appendText("");

                    appendText("VICTORY! The immediate threat is eliminated.");

                    appendText("Your team survives, battered but determined.");

                    team.resetCooldowns();

                    showTeamStatus();

                    showScene(stage);



                    if (stage < 5) {

                        startStage(stage + 1);

                    } else {

                        endGame(false, 0);

                    }

                } else {

                    gameOver("Your team has been overwhelmed.", stage);

                }

                setButtonsEnabled(true);

            });

        }).start();

    }



    private void showScene(int stage) {

        appendText("");

        appendText(story.getSceneDescription(stage));

        appendText("");

    }



    private void showTeamStatus() {

        appendText("TEAM STATUS:");

        for (int i = 0; i < team.getMembers().size(); i++) {

            Character c = team.getMembers().get(i);

            String status = c.isAlive() ? "ALIVE" : "DOWN";

            appendText("- " + c.getName() + ": HP " + c.getHP() + " [" + status + "]");

        }

    }



    private void endGame(boolean goodEnding, int finalChoice) {

        appendText("\n=== GAME COMPLETE ===");

        appendText("");



        if (goodEnding) {

            appendText("THE TRUTH PREVAILS");

            appendText("");

            appendText("You flood the facility, destroying Dr. RICO's work forever.");

            appendText("As water rushes through the corridors, you escape with");

            appendText("evidence that exposes the experiments to the world.");

            appendText("");

            appendText("Mah, Rey, and Tess become heroes who saved countless");

            appendText("future victims. The college is shut down, its secrets");

            appendText("finally brought to light.");

            appendText("");

            appendText("Some truths are worth any risk.");

        } else {

            appendText("A COSTLY VICTORY");

            appendText("");

            appendText("You defeated Dr. RICO's AI, but the facility remains.");

            appendText("The research survives, waiting for the next ambitious");

            appendText("scientist to continue the work.");

            appendText("");

            appendText("You escape alive but haunted by what you witnessed.");

            appendText("The world never learns the truth. The experiments");

            appendText("continue in shadows, claiming new victims.");

            appendText("");

            appendText("Some battles are never truly won.");

        }



        showFinalStatus();

        updateButtons(new String[]{"Play Again", "", "Exit"});

        currentStage = -1;

    }



    private void gameOver(String reason, int stage) {

        appendText("\n=== GAME OVER ===");

        appendText("");

        appendText(reason);

        appendText("Stage " + stage + " was your limit.");

        appendText("");

        appendText("Dr. RICO's experiment continues without you.");

        appendText("Another name added to the failed subjects list.");



        showFinalStatus();

        updateButtons(new String[]{"Try Again", "", "Exit"});

        currentStage = -1;

    }



    private void showFinalStatus() {

        appendText("");

        appendText("FINAL TEAM STATUS:");

        for (int i = 0; i < team.getMembers().size(); i++) {

            Character c = team.getMembers().get(i);

            appendText("- " + c.getName() + ": HP " + c.getHP());

        }

    }



    private void restartGame() {

        team = new Team();

        story = new StorylineHandler();

        battle = new BattleHandler(team, this);

        gameText.setText("");

        currentStage = 0;

        showIntro();

        updateButtons(new String[]{"Start Game", "", "Exit"});

        choice2.setEnabled(false);

    }



    private void updateButtons(String[] options) {

        SwingUtilities.invokeLater(() -> {

            choice1.setText(options[0]);

            choice2.setText(options[1]);

            choice3.setText(options[2]);

            choice1.setEnabled(true);

            choice2.setEnabled(!options[1].isEmpty());

            choice3.setEnabled(true);

        });

    }



    private void setButtonsEnabled(boolean enabled) {

        SwingUtilities.invokeLater(() -> {

            choice1.setEnabled(enabled);

            choice2.setEnabled(enabled);

            choice3.setEnabled(enabled);

        });

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameGUI());

    }

}