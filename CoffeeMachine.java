/*
 * CoffeeMachine.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-21
 * This class represents a coffee machine that can make hot or cold coffee.
 * 
 */

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeMachine {
    private String currentMode = "";                            // "hot" or "cold"
    private int availableDispensers = 3;                        // Number of coffee dispensers available
    private Queue<Client> hotQueue = new LinkedList<>();        // Queue of clients waiting for hot coffee
    private Queue<Client> coldQueue = new LinkedList<>();       // Queue of clients waiting for cold coffee

    public CoffeeMachine() {
        // Default constructor
    }

    public synchronized void requestHotCoffee(Client client) {
        hotQueue.add(client);

        while (!currentMode.equals("hot") || availableDispensers > 0 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        availableDispensers--;
        currentMode = "hot";
        System.out.println("serving hot coffee " + client.getClientID());   //debugging line
    }

    public synchronized void requestColdCoffee(Client client) {
        coldQueue.add(client);

        while (!currentMode.equals("cold") || availableDispensers > 0 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        availableDispensers--;
        currentMode = "cold";
        System.out.println("serving cold coffee " + client.getClientID());  //debugging line
    }

    public synchronized void releaseDispenser(Client client) {
        availableDispensers++;

        System.out.println("releasing dispenser " + client.getClientID());  //debugging line

        // Switch modes if needed
        if (availableDispensers == 3) {
            if (!hotQueue.isEmpty()) {
                currentMode = "hot";
            } else if (!coldQueue.isEmpty()) {
                currentMode = "cold";
            } else {
                currentMode = "";
            }
        }
        notifyAll();    // Notify all waiting clients
    }
}
