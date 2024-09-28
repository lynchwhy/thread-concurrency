/*
 * Dispenser.java
 */

public class Dispenser extends Thread {
    private int dispenserId;
    private CoffeeMachine coffeeMachine;
    private int busyUntil = 0; // The time until which the dispenser is busy

    public Dispenser(int dispenserId, CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
        this.dispenserId = dispenserId;
    }

    @Override
    public void run() {
        while (coffeeMachine.isRunning()) {
            synchronized (coffeeMachine) {

                // try {
                //     System.out.println("Dispenser " + dispenserId + " waiting for time to advance");    // Debugging
                //     coffeeMachine.wait();  // Wait for the machine to notify time advancement
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }

                // Check if the dispenser is free at the current time
                // if (isFree()) {
                    // Dispenser is free, try to serve the next client
                    System.out.println("Dispenser " + dispenserId + " is free");    // Debugging
                    Client client = coffeeMachine.getNextClient();
                    System.out.println("Client: " + client.getClientID());    // Debugging
                    if (client != null) {
                        coffeeMachine.dispenserStarted();
                        serveCoffee(client, coffeeMachine.getCurrentTime());
                        coffeeMachine.dispenserFinished();
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    //}

    // Start serving a client and set the time when the dispenser will be free
    public synchronized void serveCoffee(Client client, int currentTime) {
        
        System.out.println("(" + currentTime + ") " + client.getClientID() + " uses dispenser " + dispenserId
                + " (time: " + client.getBrewTime() + ")");

        busyUntil = currentTime + client.getBrewTime(); // Set when the dispenser will be free
    }

    // Check if the dispenser is free at the current time
    public boolean isFree() {
        return busyUntil <= coffeeMachine.getCurrentTime();

    }
}
