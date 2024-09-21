/*
 * Author: Jo Lynch
 * Date: 2024-09-20
 * Description: This is the main class for the first problem in A2.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 
{

    // Variables to store the data from the input file.
    // The values determine the initial state and direction of the MACs and the number of times they cross the intersection.
    private int CSR1 = 0;
    private int CSR2 = 0;
    private int ED1 = 0;
    private int ED2 = 0;
    private int N = 0;
    private int macTotal = 0;

    private MAC[] macs;

    public static void main(String[] args)
    {
        
        // String inputFile = args[0];
        
        // Input file for testing purposes
        String inputFile = "P1-1.txt";


        P1 simulation = new P1();

        simulation.readDataFromFile(inputFile);
        
        // Initialise the Intersection object
        Intersection intersection = new Intersection();

        // Initialise the MAC objects
        simulation.initialiseMACs(intersection);

        // Start the simulation
        simulation.startSimulation();

        // End the simulation
        simulation.endSimulation();


}

    private void readDataFromFile(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] pairs = line.split(", ");
                for (String pair: pairs)
                {
                    String[] keyValue = pair.split("=");
                    String key = keyValue[0];
                    int value = Integer.parseInt(keyValue[1]);

                    switch (key)
                    {
                        case "CSR1":
                            CSR1 = value;
                            macTotal++;
                            break;
                        case "CSR2":
                            CSR2 = value;
                            macTotal++;
                            break;
                        case "ED1":
                            ED1 = value;
                            macTotal++;
                            break;
                        case "ED2":
                            ED2 = value;
                            macTotal++;
                            break;
                        case "N":
                            N = value;
                            break;
                        default:
                            System.out.println("Unexpected key: " + key);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiseMACs(Intersection intersection) {
        macs = new MAC[macTotal];
        int macIndex = 0;
        if (CSR1 > 0) {
            macs[macIndex++] = new MAC(1, "Stock", "ED1", N, intersection);
        }
        if (CSR2 > 0) {
            macs[macIndex++] = new MAC(2, "Stock", "ED2", N, intersection);
        }
        if (ED1 > 0) {
            macs[macIndex++] = new MAC(3, "Empty", "CSR1", N, intersection);
        }
        if (ED2 > 0) {
            macs[macIndex++] = new MAC(4, "Empty", "CSR2", N, intersection);
        }
    }

    private void startSimulation() {
        // Start the threads
        for (MAC mac : macs) {
            mac.start();
        }
    }

    private void endSimulation() {
        // Wait for the threads to finish
        for (MAC mac : macs) {
            if (mac != null) {
                try {
                    mac.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }      
            }
        }
    }
}
