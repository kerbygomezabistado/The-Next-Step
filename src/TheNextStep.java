import java.util.*;

public class TheNextStep {
    static Scanner in = new Scanner(System.in);
    static Random rand = new Random();

    
    static class Character {
        String name;
        String backstory;
        String[] skills;

        Character(String name, String backstory, String[] skills) {
            this.name = name;
            this.backstory = backstory;
            this.skills = skills;
        }

        void showInfo() {
            System.out.println("\n=== " + name + " ===");
            System.out.println("Backstory: " + backstory);
            System.out.println("Skills:");
            for (String s : skills) System.out.println(" - " + s);
        }
    }

    
    static Character Mah = new Character( 
            "Mah",
            "Shy student, observant type.",
            new String[]{
                    "Observe (Passive): Notices hidden clues and small details.",
                    "Intuition: Can sense danger before it happens (alerts the team).",
                    "Analytical Mind: Solves puzzles faster by reducing time needed."
            }
    );

    static Character Rey = new Character(
            "Rey",
            "Mah’s best friend, excels academically.",
            new String[]{
                    "Riddles: Decodes complex puzzles and unlocks hidden paths.",
                    "Strategy Master: Plans improve team efficiency (faster escapes).",
                    "Knowledge Bank: Reveals secrets about stages or enemies."
            }
    );

    static Character Tess = new Character(
            "Tess",
            "Poor family, best athlete of the school.",
            new String[]{
                    "Practicality: Turns junk into useful tools.",
                    "Agility: Dodges enemy attacks or escapes faster.",
                    "Endurance: Can withstand more damage or exhaustion."
            }
    );

    static void pressEnter() {
        System.out.print("\n(Press Enter to continue...)");
        in.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("=== THE NEXT STEP ===");
        System.out.println("Three students find themselves trapped inside their mysterious school after hours...");
        pressEnter();

       
        Mah.showInfo();
        Rey.showInfo();
        Tess.showInfo();

        pressEnter();

        System.out.println("Choose your team leader:");
        System.out.println("[1] Mah");
        System.out.println("[2] Rey");
        System.out.println("[3] Tess");
        System.out.print("> ");

        Character leader = null;
        while (leader == null) {
            String choice = in.nextLine();
            switch (choice) {
                case "1" -> leader = Mah;
                case "2" -> leader = Rey;
                case "3" -> leader = Tess;
                default -> System.out.print("Invalid choice. Enter 1-3: ");
            }
        }

        System.out.println("\nYou chose " + leader.name + " as your leader!");
        pressEnter();

       
        System.out.println("=== SCENE 1: The Locked Hallway ===");
        System.out.println("The trio stands before a locked metal door with strange markings.");
        System.out.println("What will you do?");
        System.out.println("[1] Let Mah inspect the markings");
        System.out.println("[2] Let Rey try solving the riddle on the wall");
        System.out.println("[3] Let Tess search the nearby area for tools");
        System.out.print("> ");
        String action1 = in.nextLine();

        switch (action1) {
            case "1" -> {
                System.out.println("\nMah activates her skill: 'Observe'!");
                System.out.println("She notices a faint scratch revealing a hidden keyhole behind the poster!");
            }
            case "2" -> {
                System.out.println("\nRey uses 'Riddles'!");
                System.out.println("He decodes the message: 'The answer lies behind the faded memory' — pointing to an old class photo.");
            }
            case "3" -> {
                System.out.println("\nTess uses 'Practicality'!");
                System.out.println("She finds a broken paperclip and bends it into a makeshift lockpick!");
            }
            default -> System.out.println("\nYou hesitate, but the team decides to investigate together.");
        }

        pressEnter();

       
        System.out.println("=== SCENE 2: The Trap Corridor ===");
        System.out.println("The hallway ahead flickers with dim lights. Something feels off...");
        if (leader == Mah) {
            System.out.println("Mah’s Intuition warns everyone — 'Wait! There’s a trap wire ahead!'");
            System.out.println("Thanks to her quick senses, the group avoids danger.");
        } else if (leader == Rey) {
            System.out.println("Rey quickly forms a plan using his 'Strategy Master' skill.");
            System.out.println("He instructs the team to move in formation, minimizing risks.");
        } else {
            System.out.println("Tess takes the lead, using her 'Agility' to dodge sudden falling debris!");
            System.out.println("Her fast reflexes save the others.");
        }

        pressEnter();

        
        System.out.println("=== SCENE 3: The Puzzle Door ===");
        System.out.println("A giant stone door with glowing runes blocks your path.");
        System.out.println("It seems to require solving a riddle or mechanism to open.");
        if (leader == Rey) {
            System.out.println("Rey’s 'Riddles' skill activates — he deciphers the runes instantly!");
            System.out.println("The door slides open with a heavy rumble.");
        } else if (leader == Mah) {
            System.out.println("Mah’s 'Analytical Mind' helps her notice the pattern faster.");
            System.out.println("She finds the solution and unlocks the door efficiently.");
        } else {
            System.out.println("Tess uses her 'Endurance' to force the stuck lever open with sheer strength!");
            System.out.println("The door creaks but finally moves!");
        }

        pressEnter();

        
        System.out.println("=== FINAL SCENE: Escape ===");
        if (leader == Mah) {
            System.out.println("With Mah’s observation and quick thinking, the trio escapes safely!");
            System.out.println("Her quiet nature became the team's greatest strength.");
        } else if (leader == Rey) {
            System.out.println("Rey’s intellect guided the team perfectly, leading them out before sunrise.");
            System.out.println("Brains truly saved the day!");
        } else {
            System.out.println("Tess’s endurance carried the group through every danger.");
            System.out.println("Her determination inspired everyone to keep going!");
        }

        System.out.println("\n=== END OF STORY ===");
            System.out.println("Thanks for playing NextStep!");
    }
}