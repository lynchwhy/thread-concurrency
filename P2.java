
/*
 * P2.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-22
 * Description: This class represents a simulation of a coffee machine.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2 {

    private static Queue<Client> clientQueue = new LinkedList<>();


    public static void main(String[] args) {
        
        P2 p2 = new P2();
        

        // Replace with the actual input file path
        String filename = "DataSet 01/P2-1.txt";
        try {
            p2.parseInputFile(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }

        CoffeeMachine coffeeMachine = new CoffeeMachine(clientQueue);

        coffeeMachine.runSimulation();

        
    }

    // Method to parse input and add clients to the queue
    public void parseInputFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // First line: Number of clients
        int numClients = scanner.nextInt();

        // Reading client details line by line
        for (int i = 0; i < numClients; i++) {
            String clientId = scanner.next(); // H1, C1, etc.
            int brewTime = scanner.nextInt(); // Brew time in seconds

            Client client = new Client(clientId, brewTime); // Create the new client

            clientQueue.add(client); // Add the client to the queue
        }

        scanner.close();
    }
}
