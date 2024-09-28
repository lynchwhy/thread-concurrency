/*
 * CoffeeMachine.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-21
 * This class represents a coffee machine that can make hot or cold coffee.
 * This is a shared object that is accessed by multiple threads, so the methods are synchronized to make them thread-safe.
 */

import java.util.Queue;

public class CoffeeMachine {

    private String currentMode = ""; // "hot" or "cold"
    private Queue<Client> clientQueue;
    private Dispenser[] dispensers = new Dispenser[3];
    private int activeDispensers = 0;
    private boolean running = true; // Flag to control the simulation loop
    private int currentTime = 0;

    public CoffeeMachine(Queue<Client> clientQueue) {
        this.clientQueue = clientQueue;

        // Create and start the dispensers
        // Initialize the dispenser threads
        for (int i = 0; i < 3; i++) {
            dispensers[i] = new Dispenser(i + 1, this);
            dispensers[i].start();
        }
    }

    // Simulate advancing time, notify dispensers to check the current time
    public synchronized void advanceTime() {
        currentTime++;
        System.out.println("Time: " + currentTime);

        // Notify all dispenser threads that time has advanced
        notifyAll();
    }

    // Get the next client that matches the current mode (hot or cold)
    public synchronized Client getNextClient() {

        if (clientQueue.peek().getMode().equals(currentMode)) {
            Client client = clientQueue.poll();
            
            return client;
        } else {
            
            return null;
        }
    }


    // Check if any dispenser is still busy
    public synchronized boolean anyDispenserBusy() {
        for (Dispenser dispenser : dispensers) {
            if (!dispenser.isFree()) {
                return true;
            }
        }
        return false;
    }

    // Switch the coffee machine mode (hot/cold) when needed
    public synchronized void switchMode() {

        currentMode = currentMode.equals("hot") ? "cold" : "hot";
        System.out.println("Switching mode to: " + currentMode);
    }

    public synchronized int getCurrentTime() {
        return currentTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopSimulation() {
        running = false;
    }

    public String getCurrentMode() {
        return currentMode;

    }

    // Notify the machine that a dispenser has started serving a client
    public synchronized void dispenserStarted() {
        activeDispensers++;
    }

    // Notify the machine that a dispenser has finished serving a client
    public synchronized void dispenserFinished() {
        activeDispensers--;
    }

    public synchronized void runSimulation() {
        // Simulation loop controlled by the coffee machine
        while (isRunning()) {
            // Advance time step by step
            advanceTime();

            // Check if any dispenser is still busy
            if (activeDispensers == 0) {
                switchMode();
            }

            // If no more clients and all dispensers are free, stop the simulation
            if (clientQueue.isEmpty() && !anyDispenserBusy()) {
                stopSimulation();
                System.out.println("Simulation finished at time: " + currentTime);
            }

            // Simulate a small delay between each time step to observe behavior (not sleep)
            try {
                Thread.sleep(100); // Pause to allow threads to act
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
