import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Ride Class: Manages theme park attractions and implements RideInterface
 * Integrates core features including basic attraction info, waiting queue (Part3), operator association,
 * ride history tracking (Part4A), and operational cycle management (Part5)
 */
public class Ride implements RideInterface {
    // --------------------------
    // Part1 Requirements: Minimum 3 instance variables including 1 Employee type
    // --------------------------
    private String rideName;       // Name of the attraction (e.g., "Speed Roller Coaster")
    private String rideType;       // Type of attraction (e.g., "Roller Coaster", "Water Ride")
    private Employee operator;     // Attraction operator (Part1 mandatory - determines operational status)
    private int maxRider;          // Maximum riders per cycle (Defined early for Part5 to avoid future modifications)
    private int numOfCycles;       // Number of completed operational cycles (Defined early for Part5, default 0)

    // --------------------------
    // Part3 Requirements: Queue<Visitor> for waiting visitors (FIFO)
    // --------------------------
    private Queue<Visitor> waitingLine;

    // --------------------------
    // Part4A Requirements: LinkedList<Visitor> for ride history (Defined early for future expansion)
    // --------------------------
    private LinkedList<Visitor> rideHistory;

    // --------------------------
    // Part1 Requirements: Default constructor + parameterized constructor (covers all instance variables)
    // --------------------------

    /**
     * Default constructor: Initializes collections and default values
     * Prevents null pointer exceptions while meeting Part1 "default constructor" requirement
     */
    public Ride() {
        this.waitingLine = new LinkedList<>();   // Part3: Initialize waiting queue
        this.rideHistory = new LinkedList<>();   // Part4A: Initialize ride history
        this.maxRider = 1;                       // Part5: Default 1 rider per cycle (meets "minimum 1 rider" requirement)
        this.numOfCycles = 0;                    // Part5: Default cycle count set to 0
    }

    /**
     * Parameterized constructor: Initializes all instance variables (matches creation logic in AssignmentTwo)
     * Parameter order: rideName → rideType → maxRider → operator
     * @param rideName Name of the attraction (non-null)
     * @param rideType Type/classification of the attraction (non-null)
     * @param maxRider Maximum riders per operational cycle (≥1)
     * @param operator Employee responsible for operating the attraction (non-null for operational status)
     */
    public Ride(String rideName, String rideType, int maxRider, Employee operator) {
        // Initialize Part1 core attributes
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        // Initialize Part5 attributes with validation
        this.maxRider = (maxRider >= 1) ? maxRider : 1; // Ensure minimum 1 rider per cycle
        this.numOfCycles = 0;
        // Initialize Part3 and Part4A collections
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    // --------------------------
    // Part1 Requirements: Getters and Setters for all instance variables
    // --------------------------
    /**
     * Returns the name of the attraction
     * @return rideName String containing attraction name
     */
    public String getRideName() {
        return rideName;
    }

    /**
     * Sets the name of the attraction
     * @param rideName New name for the attraction
     */
    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    /**
     * Returns the type/classification of the attraction
     * @return rideType String containing attraction type
     */
    public String getRideType() {
        return rideType;
    }

    /**
     * Sets the type/classification of the attraction
     * @param rideType New type for the attraction
     */
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    /**
     * Returns the operator assigned to the attraction
     * @return operator Employee object responsible for operations
     */
    public Employee getOperator() {
        return operator;
    }

    /**
     * Assigns an operator to the attraction
     * @param operator Employee to be assigned as operator
     */
    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    /**
     * Returns maximum riders allowed per operational cycle
     * @return maxRider Integer value of maximum capacity
     */
    public int getMaxRider() {
        return maxRider;
    }

    /**
     * Sets maximum riders per cycle with validation (Part5 requirement)
     * @param maxRider New maximum capacity (must be ≥1)
     */
    public void setMaxRider(int maxRider) {
        // Data validation: Ensure minimum 1 rider per cycle (Part5 requirement)
        if (maxRider >= 1) {
            this.maxRider = maxRider;
            System.out.println("[Success] " + rideName + " max riders per cycle updated to: " + maxRider);
        } else {
            System.out.println("[Failure] Max riders must be ≥1. Current input: " + maxRider);
        }
    }

    /**
     * Returns total number of completed operational cycles
     * @return numOfCycles Integer count of cycles
     */
    public int getNumOfCycles() {
        return numOfCycles;
    }

    // numOfCycles only incremented via runOneCycle() - no setter to prevent external modification

    /**
     * Returns the waiting queue of visitors
     * @return waitingLine Queue<Visitor> containing waiting visitors
     */
    public Queue<Visitor> getWaitingLine() {
        return waitingLine;
    }

    /**
     * Returns the historical record of visitors who rode the attraction
     * @return rideHistory LinkedList<Visitor> containing ride history
     */
    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }

    // --------------------------
    // Part3 Core: Implement 3 queue methods from RideInterface
    // Each method prints success/failure messages (Part3 requirement)
    // --------------------------

    /**
     * Adds a visitor to the waiting queue (FIFO - added to tail)
     * @param visitor Visitor object to be added (non-null)
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        // 1. Validate visitor object (non-null check)
        if (visitor == null) {
            System.out.println("[Failure] Adding visitor to [" + rideName + "] queue: Visitor object is null");
            return;
        }
        // 2. Safe addition using offer() (Queue method - no capacity restrictions, no exceptions)
        waitingLine.offer(visitor);
        // 3. Print success message with current queue size
        System.out.println("[Success] Visitor [" + visitor.getName() + "] joined [" + rideName + "] queue. Current queue size: " + waitingLine.size());
    }

    /**
     * Removes a visitor from the waiting queue (FIFO - removed from head)
     * Prints failure message if queue is empty (Part3 requirement)
     */
    @Override
    public void removeVisitorFromQueue() {
        // 1. Check if queue is empty
        if (waitingLine.isEmpty()) {
            System.out.println("[Failure] Removing visitor from [" + rideName + "] queue: Queue is empty");
            return;
        }
        // 2. Remove and retrieve front visitor
        Visitor removedVisitor = waitingLine.poll();
        // 3. Print success message
        System.out.println("[Success] Removed visitor from [" + rideName + "] queue: " + removedVisitor.getName() + ". Remaining queue size: " + waitingLine.size());
    }

    /**
     * Prints detailed information of all visitors in queue in arrival order
     * Clear formatting with empty queue message (Part3 requirement)
     */
    @Override
    public void printQueue() {
        System.out.println("\n=== [" + rideName + "] Waiting Queue Details ===");
        // 1. Check if queue is empty
        if (waitingLine.isEmpty()) {
            System.out.println("No visitors in queue currently");
            System.out.println("=== Total visitors in queue: 0 ===");
            return;
        }
        // 2. Traverse queue (foreach maintains FIFO order matching arrival sequence)
        int index = 1;
        for (Visitor visitor : waitingLine) {
            System.out.println(index + ". " + visitor); // Reuse Visitor's toString() for complete details
            index++;
        }
        // 3. Print total queue size
        System.out.println("=== Total visitors in queue: " + waitingLine.size() + " ===");
    }

    // --------------------------
    // Part4A Reserved Methods (Implement RideInterface - framework only for Part3, to be completed later)
    // --------------------------
    /**
     * Adds visitor to ride history (Part4A implementation pending)
     * @param visitor Visitor to be added to history
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        System.out.println("[To be implemented] Add visitor [" + visitor.getName() + "] to [" + rideName + "] ride history");
    }

    /**
     * Checks if visitor exists in ride history (Part4A implementation pending)
     * @param visitor Visitor to verify in history
     * @return boolean indicating presence in history
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        System.out.println("[To be implemented] Check if visitor [" + visitor.getName() + "] exists in [" + rideName + "] ride history");
        return false;
    }

    /**
     * Returns count of visitors in ride history (Part4A implementation pending)
     * @return Integer count of historical visitors
     */
    @Override
    public int numberOfVisitors() {
        System.out.println("[To be implemented] Query [" + rideName + "] ride history visitor count");
        return rideHistory.size();
    }

    /**
     * Prints complete ride history (Part4A implementation pending)
     */
    @Override
    public void printRideHistory() {
        System.out.println("[To be implemented] Print [" + rideName + "] ride history");
    }

    // --------------------------
    // Part5 Reserved Method (Implement RideInterface - framework only for Part3, to be completed later)
    // --------------------------
    /**
     * Executes one operational cycle (Part5 implementation pending)
     */
    @Override
    public void runOneCycle() {
        System.out.println("[To be implemented] [" + rideName + "] execute one operational cycle");
    }

    // --------------------------
    // Override toString(): Print complete attraction information (aids debugging/verification)
    // --------------------------
    /**
     * Returns string representation of the Ride object with complete details
     * @return Formatted string containing all attributes and status
     */
    @Override
    public String toString() {
        return "Ride{" +
                "Attraction Name='" + rideName + '\'' +
                ", Type='" + rideType + '\'' +
                ", Operator=" + (operator != null ? operator.getName() : "Unassigned") +
                ", Max Riders/Cycle=" + maxRider +
                ", Completed Cycles=" + numOfCycles +
                ", Waiting Queue Size=" + waitingLine.size() +
                ", Historical Visitors=" + rideHistory.size() +
                '}';
    }
}