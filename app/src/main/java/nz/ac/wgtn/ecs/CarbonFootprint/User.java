package nz.ac.wgtn.ecs.CarbonFootprint;

public class User {
    private int userID;
    private String userName;
    private double points;
    private String password;

    public User(int userID, String userName, double points) {
        this.userID = userID;
        this.userName = userName;
        this.points = points;
    }

    public User(String userName, String password, double points) {
        this.userName = userName;
        this.password = password;
        this.points = points;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
