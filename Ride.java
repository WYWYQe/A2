import java.io.*;
import java.util.*;

/**
 * Ride class: Implements the RideInterface interface, integrating all functions from Part3-7
 */
public class Ride implements RideInterface {
    // --------------------------
    // Part1 Attributes: At least 3 instance variables, including 1 Employee type
    // --------------------------
    private String rideName;       // Name of the amusement ride (e.g., "Speed Roller Coaster")
    private String rideType;       // Classification of the amusement ride (e.g., "Roller Coaster", "Water Ride")
    private Employee operator;     // Assigned operator (determines if the ride can operate)

    // --------------------------
    // Part3 Attribute: Visitor waiting queue
    // --------------------------
    private Queue<Visitor> waitingLine;

    // --------------------------
    // Part4 Attribute: Ride history storage
    // --------------------------
    private LinkedList<Visitor> rideHistory;

    // --------------------------
    // Part5 Attributes: Operation cycle management
    // --------------------------
    private int maxRider;          // Maximum riders per operation cycle (must be ≥1)
    private int numOfCycles;       // Number of completed operation cycles (default 0, incremented by 1 per run)

    // --------------------------
    // Constructors: Initialize all attributes and collections to prevent null pointer exceptions
    // --------------------------
    // 1. Default constructor
    public Ride() {
        this.waitingLine = new LinkedList<>();   // Part3: Initialize FIFO queue
        this.rideHistory = new LinkedList<>();   // Part4: Initialize history collection
        this.maxRider = 1;                       // Part5: Default 1 rider per cycle
        this.numOfCycles = 0;                    // Part5: Initialize cycle counter to 0
    }

    // 2. Parameterized constructor
    public Ride(String rideName, String rideType, int maxRider, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        // Initialize collections and validate Part5 attributes
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = (maxRider >= 1) ? maxRider : 1;  // Ensure minimum 1 rider (Part5 requirement)
        this.numOfCycles = 0;
    }

    // --------------------------
    // Getters and Setters for all attributes
    // --------------------------
    /**
     * Returns the name of the amusement ride
     * @return rideName String containing the ride name
     */
    public String getRideName() {
        return rideName;
    }

    /**
     * Sets the name of the amusement ride
     * @param rideName New name for the ride
     */
    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    /**
     * Returns the type/classification of the amusement ride
     * @return rideType String containing the ride type
     */
    public String getRideType() {
        return rideType;
    }

    /**
     * Sets the type/classification of the amusement ride
     * @param rideType New type for the ride
     */
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    /**
     * Returns the operator assigned to the ride
     * @return operator Employee object responsible for ride operation
     */
    public Employee getOperator() {
        return operator;
    }

    /**
     * Assigns a new operator to the ride
     * @param operator Employee to be assigned as operator
     */
    public void setOperator(Employee operator) {
        this.operator = operator;
        System.out.println("[Success] " + rideName + " operator updated to: " + (operator != null ? operator.getName() : "Unassigned"));
    }

    /**
     * Returns the maximum number of riders allowed per operation cycle
     * @return maxRider Integer value of maximum capacity
     */
    public int getMaxRider() {
        return maxRider;
    }

    /**
     * Sets the maximum riders per cycle with validation
     * @param maxRider New maximum capacity (must be ≥1)
     */
    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {
            this.maxRider = maxRider;
            System.out.println("[Success] " + rideName + " maximum riders per cycle updated to: " + maxRider);
        } else {
            System.out.println("[Failure] Maximum riders must be ≥1. Current input: " + maxRider);
        }
    }

    /**
     * Returns the total number of completed operation cycles
     * @return numOfCycles Integer count of cycles
     */
    public int getNumOfCycles() {
        return numOfCycles;
    }

    /**
     * Returns the visitor waiting queue
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
    // Part3: Waiting queue management methods
    // --------------------------
    /**
     * Adds visitor to waiting queue (FIFO implementation)
     * @param visitor Visitor to be added (non-null)
     */
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[Failure] Adding visitor to [" + rideName + "] queue: Visitor object is null");
            return;
        }
        waitingLine.offer(visitor);  // Queue-safe addition
        System.out.println("[Success] Visitor [" + visitor.getName() + "] joined [" + rideName + "] queue. Current queue size: " + waitingLine.size());
    }

    /**
     * Removes visitor from the front of the waiting queue (FIFO implementation)
     */
    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("[Failure] Removing visitor from [" + rideName + "] queue: Queue is empty");
            return;
        }
        Visitor removed = waitingLine.poll();  // Remove front visitor
        System.out.println("[Success] Removed visitor from [" + rideName + "] queue: " + removed.getName() + ". Remaining queue size: " + waitingLine.size());
    }

    /**
     * Prints all visitors in the waiting queue in arrival order
     */
    @Override
    public void printQueue() {
        System.out.println("\n=== [" + rideName + "] Waiting Queue Details ===");
        if (waitingLine.isEmpty()) {
            System.out.println("No visitors in queue currently");
            System.out.println("=== Total visitors in queue: 0 ===");
            return;
        }
        // Traverse queue in FIFO order
        int index = 1;
        for (Visitor v : waitingLine) {
            System.out.println(index + ". " + v);
            index++;
        }
        System.out.println("=== Total visitors in queue: " + waitingLine.size() + " ===");
    }

    // --------------------------
    // Part4A: Ride history management methods
    // --------------------------
    /**
     * Adds visitor to ride history after completing the ride
     * @param visitor Visitor who completed the ride (non-null)
     */
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[Failure] Adding visitor to [" + rideName + "] history: Visitor object is null");
            return;
        }
        rideHistory.add(visitor);  // Add to end of LinkedList
        System.out.println("[Success] Visitor [" + visitor.getName() + "] added to [" + rideName + "] history. Total historical visitors: " + rideHistory.size());
    }

    /**
     * Verifies if visitor exists in ride history (uses unique Visitor ID to prevent name conflicts)
     * @param visitor Visitor to verify
     * @return true if visitor exists in history, false otherwise
     */
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            System.out.println("[Notice] Checking visitor [" + (visitor != null ? visitor.getName() : "null object") + "]: Invalid parameters or empty history");
            return false;
        }
        // Verify using unique Visitor ID
        String targetId = visitor.getVisitorId();
        for (Visitor v : rideHistory) {
            if (v.getVisitorId().equals(targetId)) {
                System.out.println("[Success] Visitor [" + visitor.getName() + "] (ID: " + targetId + ") exists in history");
                return true;
            }
        }
        System.out.println("[Failure] Visitor [" + visitor.getName() + "] (ID: " + targetId + ") not found in history");
        return false;
    }

    /**
     * Returns the total number of visitors in ride history
     * @return Integer count of historical visitors
     */
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[Query] [" + rideName + "] total historical visitors: " + count);
        return count;
    }

    /**
     * Prints complete ride history using Iterator
     */
    @Override
    public void printRideHistory() {
        System.out.println("\n=== [" + rideName + "] Ride History Details ===");
        if (rideHistory.isEmpty()) {
            System.out.println("No visitor records in history currently");
            System.out.println("=== Total historical visitors: 0 ===");
            return;
        }
        // Traverse using Iterator
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.println(index + ". " + iterator.next());
            index++;
        }
        System.out.println("=== Total historical visitors: " + rideHistory.size() + " ===");
    }

    // --------------------------
    // Part4B: Ride history sorting
    // --------------------------
    /**
     * Sorts ride history using custom comparator rules
     * @param comparator Custom VisitorComparator implementation
     */
    public void sortRideHistory(VisitorComparator comparator) {
        if (comparator == null) {
            System.out.println("[Failure] Sorting [" + rideName + "] history: Comparator is null");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[Failure] Sorting [" + rideName + "] history: History is empty");
            return;
        }
        // Sort using Collections.sort() with custom Comparator (Part4B requirement - Comparable not allowed)
        Collections.sort(rideHistory, comparator);
        System.out.println("[Success] [" + rideName + "] history sorted by rules (Members first → Age ascending → Visitor ID ascending)");
    }

    // --------------------------
    // Part5: Operation cycle execution
    // --------------------------
    /**
     * Executes one complete operation cycle of the ride
     * Processes riders from queue, updates history, and increments cycle counter
     */
    @Override
    public void runOneCycle() {
        // 1. Validate operator assignment
        if (operator == null) {
            System.out.println("[Failure] [" + rideName + "] cannot operate: No operator assigned");
            return;
        }
        // 2. Check for waiting visitors
        if (waitingLine.isEmpty()) {
            System.out.println("[Failure] [" + rideName + "] cannot operate: Queue is empty");
            return;
        }
        // 3. Calculate number of riders to process
        int takeCount = Math.min(maxRider, waitingLine.size());
        System.out.println("\n[Start] [" + rideName + "] running 1 cycle. Planned riders: " + takeCount);

        // 4. Process riders (remove from queue, add to history)
        for (int i = 0; i < takeCount; i++) {
            Visitor v = waitingLine.poll();  // Remove from queue (Part3 method)
            addVisitorToHistory(v);          // Add to history (Part4A method)
        }

        // 5. Update cycle counter
        numOfCycles++;
        System.out.println("[Complete] [" + rideName + "] cycle finished. Total cycles: " + numOfCycles);
    }

    // --------------------------
    // Part6: Export ride history to CSV file
    // --------------------------
    /**
     * Exports ride history to CSV file format
     * @param filePath Output file path (non-null/non-empty)
     */
    public void exportRideHistory(String filePath) {
        // Validate parameters and history (Part6 requirement)
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("[Failure] Exporting history: File path is empty");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[Failure] Exporting [" + rideName + "] history: No visitor records");
            return;
        }

        // Use try-with-resources for automatic resource management
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Export history in CSV format (VisitorID,Name,Age,Contact,IsMember)
            for (Visitor v : rideHistory) {
                String line = String.join(",",
                        v.getVisitorId(),          // Unique visitor identifier
                        v.getName(),               // Visitor name
                        String.valueOf(v.getAge()),// Age (int to String conversion)
                        v.getContact(),            // Contact information
                        String.valueOf(v.isMember())// Membership status (boolean to String)
                );
                writer.write(line);
                writer.newLine();  // New line for each visitor record
            }
            System.out.println("[Success] [" + rideName + "] history exported to: " + filePath);
            System.out.println("[Export Details] " + rideHistory.size() + " visitor records exported");

        } catch (IOException e) {
            // Comprehensive exception handling (Part6 file error handling requirement)
            System.out.println("[Error] Failed to export [" + rideName + "] history: " + e.getMessage());
            System.out.println("[Recommendation] Check file path validity and write permissions");
        }
    }

    // --------------------------
    // Part7: Import ride history from CSV file
    // --------------------------
    /**
     * Imports ride history from CSV file format
     * @param filePath Input file path (must exist)
     */
    public void importRideHistory(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("[Failure] Importing history: File path is empty");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("[Failure] Importing history: File does not exist: " + filePath);
            return;
        }

        // Clear existing history
        rideHistory.clear();

        // Use try-with-resources for automatic resource management
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int importedCount = 0;
            // Parse CSV file line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;  // Skip empty lines

                // Split CSV fields (assuming no commas in contact information)
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("[Warning] Skipping invalid line: " + line + " (Expected 5 fields)");
                    continue;
                }

                // Parse fields with type conversion error handling
                try {
                    String visitorId = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String contact = parts[3];
                    boolean isMember = Boolean.parseBoolean(parts[4]);

                    // Create Visitor object and add to history
                    Visitor v = new Visitor(name, age, contact, visitorId, isMember);
                    rideHistory.add(v);
                    importedCount++;
                } catch (NumberFormatException e) {
                    System.out.println("[Warning] Skipping invalid line: " + line + " (Invalid age/membership status format)");
                }
            }
            System.out.println("[Success] Imported " + importedCount + " visitors into [" + rideName + "] history from: " + filePath);

        } catch (IOException e) {
            System.out.println("[Error] Failed to import [" + rideName + "] history: " + e.getMessage());
            System.out.println("[Recommendation] Check file access permissions and ensure file is not in use");
        }
    }

    // --------------------------
    // Override toString(): Comprehensive object representation for debugging
    // --------------------------
    /**
     * Returns formatted string with complete ride status information
     * @return String containing all attributes and current status
     */
    @Override
    public String toString() {
        return "Ride{" +
                "Ride Name='" + rideName + '\'' +
                ", Type='" + rideType + '\'' +
                ", Operator=" + (operator != null ? operator.getName() : "Unassigned") +
                ", Max Riders/Cycle=" + maxRider +
                ", Completed Cycles=" + numOfCycles +
                ", Waiting Queue Size=" + waitingLine.size() +
                ", Historical Visitors=" + rideHistory.size() +
                '}';
    }
}