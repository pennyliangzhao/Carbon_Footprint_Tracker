package nz.ac.wgtn.ecs.CarbonFootprint;

public class User {
    private int userID;
    private String userName;
    private int pointsTravel = 1;
    private int pointsFood = 1;

    public User(int userID) {
        this.userID = userID;
    }

    private int pointsShop = 1;
    private int pointsAction = 1;

    public int getPointsTravel() {
        return pointsTravel;
    }

    public void setPointsTravel(int pointsTravel) {
        this.pointsTravel = pointsTravel;
    }

    private String password;

    public User(int userID, String userName, int points) {
        this.userID = userID;
        this.userName = userName;
        this.pointsTravel = points;
    }

    public User(String userName, String password, int points) {
        this.userName = userName;
        this.password = password;
        this.pointsTravel = points;
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
        return pointsTravel;
    }

    public void setPoints(int points) {
        this.pointsTravel = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
