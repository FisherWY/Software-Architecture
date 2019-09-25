package Experiment4.model;

/**
 * @Author Fisher
 * @Date 2019/9/24 11:03
 **/

public class User {

    private String username;
    private String weight;
    private String height;
    private String bmi;

    public User(String username, String weight, String height) {
        this.username = username;
        this.weight = weight;
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}
