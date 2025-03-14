/*
 * Client.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-22
 * Description: This class represents a client that can order coffee from a coffee machine.
 * 
 */


public class Client {
    private String clientID;        // The client's ID (e.g. H1, C2)
    private int brewTime;           // The time it takes to brew their preferred coffee
    private String mode;            // The mode of coffee they want (hot or cold)
    private int startTime;          // The time the client starts brewing 

    public Client(String clientID, int brewTime) {
        this.clientID = clientID;
        this.brewTime = brewTime;
        this.mode = clientID.startsWith("H") ? "hot" : "cold";  // If client ID starts with H, they want hot coffee, else they want cold coffee
        this.startTime = -1;   // (-1 if not yet started)
        
    }

    public String getClientID() {
        return clientID;
    }

    public int getBrewTime() {
        return brewTime;
    }

    public String getMode() {
        return mode;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}