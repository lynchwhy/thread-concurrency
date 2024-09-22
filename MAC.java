/*
 * MAC.java
 * Author: Jo Lynch
 * Date: 2024-09-21
 * Description: The MAC object represents the Medical-supply Automated Carts that will cross the intersection.
 *              This object is created as a Thread and will run concurrently with other MAC objects.
 *              Mutual exclusion is enforced by the Intersection object to ensure only one MAC crosses the intersection at a time.
 */


public class MAC extends Thread
{
private int id;                     // The unique identifier for the MAC
private String status;              // Defines the status of the MAC. I.e. Empty or Stock
private String direction;           // Defines the direction the MAC is travelling towards. I.e. ED1 or CSR2
private int checkpoint;             // The checkpoint the MAC is at whilst crossing the intersection
private int crossingCount;          // Number of times the MAC has crossed the intersection
private int N;                      // Number of times the MAC should cross the intersection
private Intersection intersection;  // The intersection object that the MACs will cross



public MAC(int id, String status, String direction, int N, Intersection intersection)
{
    this.id = id;
    this.status = status;
    this.direction = direction;
    this.checkpoint = 0;
    this.crossingCount = 0;
    this.N = N;
    this.intersection = intersection;
}

@Override
public void run()
{
    while (crossingCount < N)
    {
        System.out.println("MAC-" + id + " (" + status + "): Waiting at the Intersection. Going towards " + direction + ".");
        intersection.requestAccess(this);
        crossIntersection();
        intersection.releaseAccess(this);
        changeStatus();
    }
    finish();   
}



// Called when the MAC is at the intersection and has been granted access to cross.
public void crossIntersection()
{
    for (int i = 1; i < 4; i++)     // Loop used to simulate the 3 checkpoints during crossing.
    {
        checkpoint = i;
        System.out.println("MAC-" + id + " (" + status + "): Crossing intersection Checkpoint " + i + ".");
        try
        {
            Thread.sleep(50);       // Simulate the time it takes to cross the intersection (50ms)
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    crossingCount++;

    
}

public void changeStatus()
{
    if (status.equals("Empty"))
    {
        status = "Stock";
        direction = getOppositeDirection();
    } 
    else 
    {
        status = "Empty";
        direction = getOppositeDirection();
    }
}

    public String getOppositeDirection()
    {
        switch (direction) {
            case "ED1":
                return "CSR1";
            case "ED2":
                return "CSR2";
            case "CSR1":
                return "ED1";
            case "CSR2":
                return "ED2";
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

// Called when the MAC has completed the required number of intersection crossings. It stops further movement and end the thread associated with the MAC.
public void finish()
{
    System.out.println("MAC-" + id + ": Finished");
}

// Getters and Setters

public long getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getDirection() {
    return direction;
}

public void setDirection(String direction) {
    this.direction = direction;
}

public int getCheckpoint() {
    return checkpoint;
}

public void setCheckpoint(int checkpoint) {
    this.checkpoint = checkpoint;
}

public int getCrossingCount() {
    return crossingCount;
}

public void setCrossingCount(int crossingCount) {
    this.crossingCount = crossingCount;
}

public int getN() {
    return N;
}

public void setN(int n) {
    N = n;
}
}