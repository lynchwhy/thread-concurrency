/*
 * Author: Jo Lynch
 * Date: 2024-09-20
 * Description: This is the main class for the first problem in A2.
 *             The program reads data from an input file and creates MAC objects to cross an intersection.
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 {

    // Variables to store the data from the input file.
    // The values determine the initial state and direction of the MACs and the
    // number of times they cross the intersection.
    private int CSR1 = 0;
    private int CSR2 = 0;
    private int ED1 = 0;
    private int ED2 = 0;
    private int N = 0;
    private int macTotal = 0;

    private MAC[] macs;

    public static void main(String[] args) {

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
            while ((line = reader.readLine()) != null) {
                String[] pairs = line.split(", ");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    String key = keyValue[0];
                    int value = Integer.parseInt(keyValue[1]);

                    switch (key) {
                        case "CSR1":
                            CSR1 = value;
                            break;
                        case "CSR2":
                            CSR2 = value;
                            break;
                        case "ED1":
                            ED1 = value;
                            break;
                        case "ED2":
                            ED2 = value;
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

        macTotal = CSR1 + CSR2 + ED1 + ED2;
    }

    // Initialise the MAC objects
    // The input file provides MACs with a starting direction from which they came
    // from.
    // The rest of this program uses the direction they are going towards, thus the
    // seemingly backwards logic below.
    private void initialiseMACs(Intersection intersection) {
        macs = new MAC[macTotal];
        int macIndex = 0;
        while (CSR1-- > 0) {
            macs[macIndex++] = new MAC(macIndex, "Stock", "ED1", N, intersection);
        }
        while (CSR2-- > 0) {
            macs[macIndex++] = new MAC(macIndex, "Stock", "ED2", N, intersection);
        }
        while (ED1-- > 0) {
            macs[macIndex++] = new MAC(macIndex, "Empty", "CSR1", N, intersection);
        }
        while (ED2-- > 0) {
            macs[macIndex++] = new MAC(macIndex, "Empty", "CSR2", N, intersection);
        }
    }

    // Start the threads
    private void startSimulation() {
        for (MAC mac : macs) {
            mac.start();
        }
    }

    // Wait for the threads to finish, join them to the main thread
    private void endSimulation() {
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
