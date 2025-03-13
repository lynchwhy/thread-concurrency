/*
 * Client.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-22
 * Description: This class represents a client that can order coffee from a coffee machine.
 * 
 */


public class Client extends Thread {
    private String clientID;        // The client's ID
    private int brewTime;           // The time it takes to brew the coffee
    private CoffeeMachine machine;  // The coffee machine the client is ordering from

    public Client(String clientID, int brewTime, CoffeeMachine machine) {
        this.clientID = clientID;
        this.brewTime = brewTime;
        this.machine = machine;
    }

    @Override
    public void run() {
        if (clientID.startsWith("H")) {
            machine.requestHotCoffee(this);
        } else if (clientID.startsWith("C")) {
            machine.requestColdCoffee(this);
        }

        // Simulate brewing time
        System.out.println("brewing coffee " + clientID);  //debugging line
        try {
            Thread.sleep(brewTime*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        machine.releaseDispenser(this);
    }

    public String getClientID() {
        return clientID;
    }
}