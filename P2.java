/*
 * P2.java
 * Author: Jo Lynch (c3200655)
 * Date: 2024-09-22
 * Description: This class represents a simulation of a coffee machine.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class P2 {

    List<Thread> threads = new ArrayList<Thread>();

    public static void main(String[] args) {

        P2 simulation = new P2();

        String inputFile = "DataSet 01/P2-1.txt";

        System.out.println("Reading data from file: " + inputFile);

        CoffeeMachine machine = new CoffeeMachine();
        
        try {
            simulation.readDataFromFile(inputFile, machine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Thread t : simulation.threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        
    }

    
    public void readDataFromFile(String inputFile, CoffeeMachine machine) throws IOException {
            
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            
            // First line: total number of clients 
            int numClients = Integer.parseInt(reader.readLine().trim());
            
            // Reading the clients line by line
            for (int i = 0; i < numClients; i++) {
                String line = reader.readLine();
                String[] parts = line.split(" ");
                
                String clientID = parts[0];     // Client ID (e.g. H1, C2)
                int brewTime = Integer.parseInt(parts[1]);
                Client client = new Client(clientID, brewTime, machine);
                
                // Print debugging line
                System.out.println("Client " + clientID + " has a brew time of " + brewTime);
                
                threads.add(client);
                client.start();
            }

            reader.close();
    }
}

    
}