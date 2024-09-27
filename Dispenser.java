public class Dispenser extends Thread {
    private int dispenserId;
    //private boolean inUse = false;
    private CoffeeMachine coffeeMachine;
    private int busyUntil = 0;      // The time until which the dispenser is busy


    public Dispenser(int dispenserId, CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
        this.dispenserId = dispenserId;
    }


  
    @Override
    public void run() {
        while (coffeeMachine.isRunning()) {
            synchronized (coffeeMachine) {
                // try {
                //     // Wait for the coffee machine to notify that time has advanced
                //     coffeeMachine.wait();
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }

                // Check if the dispenser is free at the current time
                if (isFree(coffeeMachine.getCurrentTime())) {
                    // Dispenser is free, try to serve the next client
                    Client client = coffeeMachine.getNextClient();
                    if (client != null) {
                        serveCoffee(client, coffeeMachine.getCurrentTime());
                    }
                }
            }
        }
    }

    // Start serving a client and set the time when the dispenser will be free
    public synchronized void serveCoffee(Client client, int currentTime) {
        System.out.println("(" + currentTime + ") " + client.getClientID() + " uses dispenser " + dispenserId + " (time: " + client.getBrewTime() + ")");
        busyUntil = currentTime + client.getBrewTime();  // Set when the dispenser will be free
    }

    // Check if the dispenser is free at the current time
    public boolean isFree(int currentTime) {
        return currentTime >= busyUntil;
     
    }
}
