package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class SelfAwarnessReportBean implements Serializable {

    String goal, strength, interestHobbies, responsibilityDischarged_exceptionalAchievements;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getInterestHobbies() {
        return interestHobbies;
    }

    public void setInterestHobbies(String interestHobbies) {
        this.interestHobbies = interestHobbies;
    }

    public String getResponsibilityDischarged_exceptionalAchievements() {
        return responsibilityDischarged_exceptionalAchievements;
    }

    public void setResponsibilityDischarged_exceptionalAchievements(String responsibilityDischarged_exceptionalAchievements) {
        this.responsibilityDischarged_exceptionalAchievements = responsibilityDischarged_exceptionalAchievements;
    }
}
