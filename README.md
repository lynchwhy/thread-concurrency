# Operating Systems - Assignment 2 
# Requirements Summary

## Overview
This assignment involves simulating two real-world scenarios using Java threads and synchronization mechanisms. There are two main programming problems (with an additional research report for COMP6240 students) that must be implemented following strict input/output formats.

(Result: I obtained full marks for this assignment.)
---

## Problem 1: MAC Management

### Scenario
Simulate autonomous medical-supply carts (MACs) transporting supplies between Emergency Departments (EDs) and Central Supply Rooms (CSRs) in a hospital. The carts operate along two intersecting trails.

### Key Requirements
- **Intersection Safety:**  
  - Only one MAC is allowed in the intersection at any given time to prevent collisions.
  - The system must be **deadlock-free** and **starvation-free**.

- **Checkpoint Simulation:**  
  - There are three checkpoints in the intersection.
  - Each MAC must simulate passing each checkpoint by adding a 50 ms delay.
  - The system keeps count of the number of MACs crossing each trail.

- **Loading/Unloading:**  
  - These operations occur instantly once a MAC has crossed the intersection.

### Input/Output Specifications
- **Input File:**  
  - Contains the initial number of MACs starting from each location (CSR1, CSR2, ED1, ED2) and the number of intersection crossings (N).  
  - Example: `CSR1=1, CSR2=2, ED1=1, ED2=1, N=2`.

- **Output:**  
  - Must adhere strictly to the provided sample format.
  - Outputs include messages indicating MAC statuses, checkpoint passes, trail crossing counts, and completion notifications.

### Implementation Details
- **Concurrency:**  
  - Use Java threads to simulate multiple concurrent MACs.
- **Synchronization:**  
  - Use semaphores to manage access to the intersection and enforce mutual exclusion.

---

## Problem 2: Hot or Iced Coffee Machine

### Scenario
Simulate a coffee machine that serves both hot and cold coffee to staff, featuring three dispensing heads that allow parallel processing.

### Key Requirements
- **Machine Operation:**  
  - The machine operates in one mode (hot or cold) at a time.
  - Only three clients can use the machine concurrently.
  - Clients of the opposite mode must wait if the machine is already serving a particular type of coffee.

- **Client Service Order:**  
  - For both hot and cold coffee clients, the service order should follow the arrival order (i.e., lower ID served before higher ID).

- **Brew Time Selection:**  
  - Each client selects a brew time that simulates the strength of the coffee.

### Input/Output Specifications
- **Input File:**  
  - The first line indicates the total number of clients.
  - Each subsequent line specifies the client's ID (starting with `H` for hot or `C` for cold) and the brew time.
  - The order in the file represents the arrival order.

- **Output:**  
  - Each line shows:
    - The time the client starts using the coffee machine.
    - Client ID.
    - Assigned dispenser number.
    - Brew time.
  - The final line indicates the total time taken to serve all clients.

### Implementation Details
- **Concurrency:**  
  - Use Java threads to simulate concurrent client access.
- **Synchronization:**  
  - Use monitors to manage mutual exclusion and ensure fairness between hot and cold clients.


## Submission Requirements and Guidelines

### Programming Language and Environment
- **Language:** Java (compatible with a variant of Java 17).
- **Allowed Libraries:** Only standard Java libraries may be used.

### Input and Output Handling
- **Input:**  
  - Read from a file specified as a command line argument.
- **Output:**  
  - Must be printed to the console.
  - The format must strictly follow the provided sample outputs.

### File Naming and Execution
- **Main Files:**  
  - `P1.java` for Problem 1.
  - `P2.java` for Problem 2.
- **Compilation/Execution:**  
  - Programs must compile with `javac P1.java` and `javac P2.java`.
  - Execution should follow `java P1 input.txt` and `java P2 input.txt`.


