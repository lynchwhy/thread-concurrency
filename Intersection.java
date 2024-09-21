/*
 * Intersection.java
 * Author: Jo Lynch
 * Date: 2024-09-21
 * Description: The Intersection object handles access control and coordination between MAC on the two trails.
 * It ensures mutual exclusion so only one MAC enters the intersection at a time.
 */

import java.util.concurrent.Semaphore;

public class Intersection 
{
    private Semaphore semaphore = new Semaphore(1, true);     // Only 1 MAC can cross the intersection at a time.
    private int trail1Count = 0;
    private int trail2Count = 0;
    


    public Intersection()
    {
        // Default constructor
    }

    public void requestAccess(MAC mac)
    {
        System.out.println("MAC-" + mac.getId() + " (" + mac.getStatus() + "): Waiting at the Intersection. Going towards " + mac.getDirection() + ".");

        try
        {
            semaphore.acquire();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
}

    public void releaseAccess(MAC mac)
    {
        updateTrailCount(mac);
        System.out.println("MAC-" + mac.getId() + " (" + mac.getStatus() + "): Crossed the Intersection.");
        semaphore.release();
        
    }

    public void updateTrailCount(MAC mac)
    {
        if (mac.getDirection().equals("CSR1") || mac.getDirection().equals("ED1"))
        {
            trail1Count++;
        }
        else
        {
            trail2Count++;
        }
    }

    // Getters and Setters

    public int getTrail1Count() {
        return trail1Count;
    }

    public void setTrail1Count(int trail1Count) {
        this.trail1Count = trail1Count;
    }

    public int getTrail2Count() {
        return trail2Count;
    }

    public void setTrail2Count(int trail2Count) {
        this.trail2Count = trail2Count;
    }
}