/**
 * Ride class: Implements the RideInterface to manage individual theme park attractions (e.g., roller coasters, carousels)
 * Contains core functionalities for attraction details, waiting queues, ride history, and operational cycles
 */
public class Ride implements RideInterface {
    // Core instance variables (Part1 requirements)
    private String rideName;    // Name of the attraction (e.g., "Roller Coaster")
    private String rideType;    // Type of attraction (e.g., "Thrill Ride", "Family Ride")
    private Employee operator;  // Attendant responsible for operating the ride (Part1 mandatory)

    // --------------------------
    // Incremental attributes for Part3-5 (defined in Part2, implemented in later phases)
    // --------------------------
    // Part3: Waiting queue (FIFO storage for visitors waiting to ride)
    private java.util.Queue<Visitor> waitingLine;
    // Part4A: Ride history (stores visitors who completed rides, implemented with LinkedList)
    private java.util.LinkedList<Visitor> rideHistory;
    // Part5: Operational cycle attributes (maxRider = max passengers per cycle, numOfCycles = total cycles operated)
    private int maxRider;
    private int numOfCycles;

    // 1. Default constructor (Part1): Initializes collections to prevent null pointers
    public Ride() {
        this.waitingLine = new java.util.LinkedList<>();  // Queue implemented with LinkedList
        this.rideHistory = new java.util.LinkedList<>();  // Ride history stored in LinkedList
        this.numOfCycles = 0;  // Initialize cycle count to zero
        this.maxRider = 2;     // Default capacity: 2 riders per cycle (modifiable via setter)
    }

    // 2. Parameterized constructor (Part1): Initializes basic info and collections
    // @param rideName Name of the attraction
    // @param rideType Type/classification of the attraction
    // @param operator Employee assigned to operate the ride
    // @param maxRider Maximum riders per operational cycle
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        // Initialize collections and cycle attributes
        this.waitingLine = new java.util.LinkedList<>();
        this.rideHistory = new java.util.LinkedList<>();
        this.numOfCycles = 0;
        this.maxRider = maxRider;  // Customizable rider capacity per cycle
    }

    // 3. Getters and Setters for Part1 attributes
    // Returns the name of the ride
    public String getRideName() {
        return rideName;
    }

    // Sets the name of the ride
    // @param rideName New name for the attraction
    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    // Returns the type/classification of the ride
    public String getRideType() {
        return rideType;
    }

    // Sets the type/classification of the ride
    // @param rideType New type for the attraction
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    // Returns the employee operating the ride
    public Employee getOperator() {
        return operator;
    }

    // Assigns an employee to operate the ride
    // @param operator Employee responsible for ride operations
    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    // Getters and Setters for Part5 attributes
    // Returns maximum riders allowed per operational cycle
    public int getMaxRider() {
        return maxRider;
    }

    // Sets maximum riders per cycle (with validation)
    // @param maxRider New maximum capacity (must be ≥ 1)
    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {  // Validation: Minimum 1 rider per cycle
            this.maxRider = maxRider;
        } else {
            System.out.println("Error: Max riders per cycle must be ≥ 1. Current input: " + maxRider);
        }
    }

    // Returns total number of operational cycles completed
    public int getNumOfCycles() {
        return numOfCycles;
    }

    // 4. Implementation of RideInterface methods (Part2 framework with Part3-5 logic)
    /**
     * Adds a visitor to the ride's waiting queue
     * @param visitor Visitor to be added (non-null)
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor);  // Queue-safe addition (returns false if full)
            System.out.println("Success: Visitor " + visitor.getName() + " added to [" + rideName + "] waiting queue");
        } else {
            System.out.println("Failure: Cannot add null visitor to queue");
        }
    }

    /**
     * Removes the next visitor from the waiting queue (FIFO)
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("Failure: [" + rideName + "] waiting queue is empty - cannot remove visitor");
            return;
        }
        Visitor removed = waitingLine.poll();  // Remove front visitor from queue
        System.out.println("Success: Removed visitor " + removed.getName() + " from [" + rideName + "] waiting queue");
    }

    /**
     * Prints all visitors in the waiting queue (in order)
     */
    @Override
    public void printQueue() {
        System.out.println("\n[" + rideName + "] Waiting Queue Details (" + waitingLine.size() + " visitors):");
        if (waitingLine.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        // Iterate through queue in insertion order
        int index = 1;
        for (Visitor v : waitingLine) {
            System.out.println(index + ". " + v);
            index++;
        }
    }

    /**
     * Adds a visitor to the ride's historical records
     * @param visitor Visitor who completed the ride (non-null)
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);  // Append to end of history list
            System.out.println("Success: Visitor " + visitor.getName() + " added to [" + rideName + "] ride history");
        } else {
            System.out.println("Failure: Cannot add null visitor to history");
        }
    }

    /**
     * Checks if a visitor exists in ride history (by unique Visitor ID)
     * @param visitor Visitor to verify
     * @return true if visitor exists in history, false otherwise
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            return false;
        }
        // Verify using unique Visitor ID to avoid name duplication issues
        for (Visitor v : rideHistory) {
            if (v.getVisitorId().equals(visitor.getVisitorId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns total number of visitors in ride history
     * @return Size of ride history collection
     */
    @Override
    public int numberOfVisitors() {
        return rideHistory.size();  // Return count of historical visitors
    }

    /**
     * Prints complete ride history using Iterator (Part4A requirement)
     */
    @Override
    public void printRideHistory() {
        System.out.println("\n[" + rideName + "] Ride History Details (" + rideHistory.size() + " visitors):");
        if (rideHistory.isEmpty()) {
            System.out.println("No visitor records in history");
            return;
        }
        // Part4A requirement: Must use Iterator for traversal
        java.util.Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor v = iterator.next();
            System.out.println(index + ". " + v);
            index++;
        }
    }

    /**
     * Runs one complete operational cycle of the ride
     * - Checks for operator assignment
     * - Processes riders from waiting queue (up to maxRider)
     * - Updates cycle count and ride history
     */
    @Override
    public void runOneCycle() {
        // 1. Validate operator assignment
        if (operator == null) {
            System.out.println("Failure: [" + rideName + "] has no assigned operator - cannot run");
            return;
        }
        // 2. Check for waiting visitors
        if (waitingLine.isEmpty()) {
            System.out.println("Failure: [" + rideName + "] waiting queue is empty - cannot run");
            return;
        }
        // 3. Process riders (up to max capacity or remaining queue size)
        int takeNum = Math.min(maxRider, waitingLine.size());
        System.out.println("\n[" + rideName + "] starting operational cycle - planned riders: " + takeNum);

        for (int i = 0; i < takeNum; i++) {
            Visitor v = waitingLine.poll();  // Remove from queue
            addVisitorToHistory(v);          // Add to ride history
        }
        // 4. Update operational metrics
        numOfCycles++;
        System.out.println("Success: [" + rideName + "] completed cycle. Total cycles: " + numOfCycles);
    }

    // 5. Overridden toString() (Part1) - includes collection and cycle information
    /**
     * Returns string representation of the ride with operational details
     * @return Formatted string with ride info, capacity, and statistics
     */
    @Override
    public String toString() {
        return "Ride{" +
                "Name='" + rideName + '\'' +
                ", Type='" + rideType + '\'' +
                ", Operator=" + (operator != null ? operator.getName() : "Unassigned") +
                ", Max Riders/Cycle=" + maxRider +
                ", Total Cycles=" + numOfCycles +
                ", Waiting Queue Size=" + waitingLine.size() +
                ", Historical Visitors=" + rideHistory.size() +
                '}';
    }
}