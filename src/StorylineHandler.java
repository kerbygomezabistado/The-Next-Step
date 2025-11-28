import java.util.ArrayList;
import java.util.List;

public class StorylineHandler {
    private List<String[]> stagePuzzles;
    private List<String> stageScenes;
    private List<String[]> choiceDescriptions;
    private String[] currentOptions;

    public StorylineHandler() {
        initializeStoryContent();
    }

    private void initializeStoryContent() {
        stagePuzzles = new ArrayList<>();
        stageScenes = new ArrayList<>();
        choiceDescriptions = new ArrayList<>();
        stagePuzzles.add(new String[]{
                "Press the glyph matching the mural's pattern",
                "Press the largest, authoritative glyph",
                "Press the softly glowing glyph"
        });
        choiceDescriptions.add(new String[]{
                "You carefully match the mural's pattern. The glyph accepts your touch and the door slides open silently.",
                "The large glyph resists, then triggers hidden defenses. Metal shutters slam down around you.",
                "The glowing glyph pulses violently, emitting energy that disrupts your equipment."
        });
        stagePuzzles.add(new String[]{
                "Reconstruct path using campus map logic",
                "Follow only the recent annotations",
                "Take random paths until something works"
        });
        choiceDescriptions.add(new String[]{
                "The coordinates align perfectly with campus architecture. A hidden elevator reveals itself.",
                "Recent notes lead to a dead end with fresh blood stains and broken equipment.",
                "Random corridors lead to identical rooms, each more disorienting than the last."
        });

        stagePuzzles.add(new String[]{
                "Flip switch completing circuit diagram",
                "Flip switch with unknown symbol",
                "Flip all three switches at once"
        });
        choiceDescriptions.add(new String[]{
                "The circuit stabilizes perfectly. Training equipment powers down safely.",
                "The unknown symbol triggers emergency lockdown and defense protocols.",
                "All switches cause massive power surge that damages the entire facility."
        });

        stagePuzzles.add(new String[]{
                "Carefully disengage using manual procedure",
                "Smash casing for immediate shutdown",
                "Ignore and continue forward"
        });

        choiceDescriptions.add(new String[]{
                "The stabilizer powers down gracefully. Systems return to normal operation.",
                "The casing shatters, releasing arc energy that damages nearby equipment.",
                "The humming intensifies to painful levels. Structural integrity alarms sound."
        });

        stagePuzzles.add(new String[]{
                "Release valves to flood and sabotage",
                "Confront Dr. RICO directly",
                "Accept Dr. RICO's offer to join"
        });

        choiceDescriptions.add(new String[]{
                "Emergency valves release coolant. The facility begins flooding immediately.",
                "You march toward the central chamber, weapons ready for final confrontation.",
                "You approach the main console, considering the power and knowledge offered."
        });
        stageScenes.add(
                "The mural tells Dr. Alistair RICO's story: once a brilliant geneticist\n" +
                        "at Abistado College, he became obsessed with human enhancement after\n" +
                        "his daughter's terminal illness. When the college revoked his funding,\n" +
                        "he went underground, using students as unwilling test subjects.\n" +
                        "The dates show the project began 25 years ago."
        );
        stageScenes.add(
                "Research notes reveal the 'cognition serum' was meant to unlock\n" +
                        "latent psychic abilities. Early tests showed promise but caused\n" +
                        "violent psychosis in 92% of subjects. Dr. RICO continued anyway,\n" +
                        "convinced he could perfect the formula. The recent notes are from\n" +
                        "students who discovered his work and suffered the consequences."
        );
        stageScenes.add(
                "Training logs show Dr. RICO believed physical and mental enhancement\n" +
                        "had to develop together. Subjects who survived cognitive tests were\n" +
                        "pushed to physical limits. Those who broke became mindless servants—\n" +
                        "the zombies you've been fighting. The successful ones... disappeared\n" +
                        "into deeper parts of the facility."
        );

        stageScenes.add(
                "The final truth emerges: Dr. RICO died 8 years ago from his own\n" +
                        "experimental treatments. His AI, RICO-7, continues the work,\n" +
                        "trapped in his final obsessive state. The students in tanks are\n" +
                        "being prepared for 'Stage 3'—complete mental rewriting to create\n" +
                        "perfect soldiers. Your classmates are among them."
        );
    }

    public void showPuzzle(int stage) {
        currentOptions = stagePuzzles.get(stage - 1);
    }

    public String[] getCurrentOptions() {
        return currentOptions;
    }

    public boolean handleChoice(int stage, int choice) {
        return choice == 1;
    }

    public String getChoiceDescription(int stage, int choice) {
        return choiceDescriptions.get(stage - 1)[choice - 1];
    }
    public String getSceneDescription(int stage) {
        if (stage <= stageScenes.size()) {
            return stageScenes.get(stage - 1);
        }
        return "You press forward, the weight of discovery heavy upon you.";
    }
} 