/*
 * 
 */


public class MAC extends Thread
{
private int id;
private String status;
private String direction;           // Defines the direction the MAC is travelling towards. I.e. ED1 or CSR2
private int checkpoint;
private int crossingCount;
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
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    System.out.println("MAC-" + id + " (" + status + "): Crossed the intersection.");
    crossingCount++;
    System.out.println("Total crossed in Trail 1: " + intersection.getTrail1Count() + " Trail2: " + intersection.getTrail2Count());
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