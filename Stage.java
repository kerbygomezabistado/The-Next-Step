public class Stage {
    private int stageNumber;
    private Monster monster;

    public Stage(int stageNumber) {
        this.stageNumber = stageNumber;
        int hp = 50 + stageNumber * 20;
        int attack = 15 + stageNumber * 5;
        this.monster = new Monster("Monster L" + stageNumber, hp, attack);
    }

    public int getStageNumber() { return stageNumber; }
    public Monster getMonster() { return monster; }
}