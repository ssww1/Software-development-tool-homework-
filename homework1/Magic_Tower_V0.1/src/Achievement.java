package src;

public class Achievement {
    private int index;
    private String name;
    private String description;

    private boolean isAchieved;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Achievement(int index,String name,String description){
        this.index = index;
        this.name = name;
        this.description = description;
    }
    public boolean getAchieved(){
        return this.isAchieved;
    }
    public void achieved(){
        this.isAchieved = true;
    }

}
