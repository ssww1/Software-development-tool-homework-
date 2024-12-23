package src;

import java.util.ArrayList;
import java.util.List;

public class Achievements {
    private List<Achievement> achievementList = new ArrayList<>();

    public Achievements(){

        achievementList.add(new Achievement(0, "First step!", "Welcome to Magic Tower!"));
        achievementList.add(new Achievement(1, "First blood!", "Mama~~ just killed a man~~"));
        achievementList.add(new Achievement(2, "Death", "You dead!"));
    }
    public static Achievements init(){
        Achievements achievements = new Achievements();
        return achievements;
    }
    public List<Achievement> getAchievementList() {
        return achievementList;
    }


    public void setAchievementList(List<Achievement> achievementList) {
        this.achievementList = achievementList;
    }

    public void getAchievement(int index){
        Achievement achievement = achievementList.get(index);
        if (!achievement.getAchieved()){
            System.out.println("You have achieved an achievement!");
            System.out.println(achievement.getName());
            System.out.println(achievement.getDescription());
            achievement.achieved();
        }
    }

}
