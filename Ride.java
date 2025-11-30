import java.io.*;
import java.util.*;

/**
 * Ride class: Implements the RideInterface interface, integrating all functions from Part3-7
 */
public class Ride implements RideInterface {
    // --------------------------
    // Part1 Attributes: At least 3 instance variables, including 1 Employee type
    // --------------------------
    private String rideName;       // Ride name (e.g., "Speed Roller Coaster")
    private String rideType;       // Ride type (e.g., "Roller Coaster", "Water Ride")
    private Employee operator;     // Operator (determines if the ride can operate)

    // --------------------------
    // Part3 Attribute: Waiting queue
    // --------------------------
    private Queue<Visitor> waitingLine;

    // --------------------------
    // Part4 Attribute: Ride history
    // --------------------------
    private LinkedList<Visitor> rideHistory;

    // --------------------------
    // Part5 Attributes: Cycle operation related
    // --------------------------
    private int maxRider;          // Maximum riders per cycle (≥1)
    private int numOfCycles;       // Number of completed cycles (default 0, incremented by 1 each run)

    // --------------------------
    // Constructors: Initialize all attributes and collections (avoid null pointers)
    // --------------------------
    // 1. Default constructor
    public Ride() {
        this.waitingLine = new LinkedList<>();   // Part3: Queue initialization
        this.rideHistory = new LinkedList<>();   // Part4: History initialization
        this.maxRider = 1;                       // Part5: Default capacity of 1 rider
        this.numOfCycles = 0;                    // Part5: Default 0 runs
    }

    // 2. Parameterized constructor
    public Ride(String rideName, String rideType, int maxRider, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        // Initialize collections and Part5 attributes (with validation)
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = (maxRider >= 1) ? maxRider : 1;  // Ensure ≥1
        this.numOfCycles = 0;
    }

    // --------------------------
    // Getters and Setters
    // --------------------------
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {
            this.maxRider = maxRider;
            System.out.println("[Success] " + rideName + " maximum riders per cycle updated to: " + maxRider);
        } else {
            System.out.println("[Failure] Maximum riders per cycle must be ≥1, current input: " + maxRider);
        }
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    public Queue<Visitor> getWaitingLine() {
        return waitingLine;
    }

    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }

    // --------------------------
    // Part3: Implementation of waiting queue methods
    // --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[Failure] Adding visitor to [" + rideName + "] queue: Visitor object is null");
            return;
        }
        waitingLine.offer(visitor);  // Queue-safe addition (no capacity limit)
        System.out.println("[Success] Visitor [" + visitor.getName() + "] joined [" + rideName + "] queue, current queue size: " + waitingLine.size());
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("[Failure] Removing visitor from [" + rideName + "] queue: Queue is empty");
            return;
        }
        Visitor removed = waitingLine.poll();  // Remove front of queue
        System.out.println("[Success] Removed visitor from [" + rideName + "] queue: " + removed.getName() + ", remaining size: " + waitingLine.size());
    }

    @Override
    public void printQueue() {
        System.out.println("\n=== [" + rideName + "] Waiting Queue Details ===");
        if (waitingLine.isEmpty()) {
            System.out.println("Current queue has no visitors");
            System.out.println("=== Total queue size: 0 ===");
            return;
        }
        // Traverse queue
        int index = 1;
        for (Visitor v : waitingLine) {
            System.out.println(index + ". " + v);
            index++;
        }
        System.out.println("=== Total queue size: " + waitingLine.size() + " ===");
    }

    // --------------------------
    // Part4A: Implementation of ride history methods
    // --------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[Failure] Adding visitor to [" + rideName + "] history: Visitor object is null");
            return;
        }
        rideHistory.add(visitor);  // Add to end of LinkedList
        System.out.println("[Success] Visitor [" + visitor.getName() + "] added to [" + rideName + "] history, total count: " + rideHistory.size());
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            System.out.println("[Notice] Checking visitor [" + (visitor != null ? visitor.getName() : "null object") + "]: Invalid parameter or empty history");
            return false;
        }
        // Judge by visitor ID (unique identifier to avoid false judgment for same names)
        String targetId = visitor.getVisitorId();
        for (Visitor v : rideHistory) {
            if (v.getVisitorId().equals(targetId)) {
                System.out.println("[Success] Visitor [" + visitor.getName() + "] (ID: " + targetId + ") exists in history");
                return true;
            }
        }
        System.out.println("[Failure] Visitor [" + visitor.getName() + "] (ID: " + targetId + ") does not exist in history");
        return false;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[Query] [" + rideName + "] total historical visitors: " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n=== [" + rideName + "] Ride History Details ===");
        if (rideHistory.isEmpty()) {
            System.out.println("Current history has no visitor records");
            System.out.println("=== Total historical visitors: 0 ===");
            return;
        }
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.println(index + ". " + iterator.next());
            index++;
        }
        System.out.println("=== Total historical visitors: " + rideHistory.size() + " ===");
    }

    // --------------------------
    // Part4B: Ride history sorting method
    // --------------------------
    public void sortRideHistory(VisitorComparator comparator) {
        if (comparator == null) {
            System.out.println("[Failure] Sorting [" + rideName + "] history: Comparator is null");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[Failure] Sorting [" + rideName + "] history: History is empty");
            return;
        }
        // Call Collections.sort() with custom Comparator (required by Part4B)
        Collections.sort(rideHistory, comparator);
        System.out.println("[Success] [" + rideName + "] history sorted by specified rules");
    }

    // --------------------------
    // Part5: Implementation of ride cycle operation method
    // --------------------------
    @Override
    public void runOneCycle() {
        // 1. Check if operator exists
        if (operator == null) {
            System.out.println("[Failure] [" + rideName + "] cannot run: No operator assigned");
            return;
        }
        // 2. Check if queue has visitors
        if (waitingLine.isEmpty()) {
            System.out.println("[Failure] [" + rideName + "] cannot run: Queue is empty");
            return;
        }
        // 3. Calculate number of riders for this cycle (minimum of maxRider and remaining queue size)
        int takeCount = Math.min(maxRider, waitingLine.size());
        System.out.println("\n[Start] [" + rideName + "] running 1 cycle, planned riders: " + takeCount);

        // 4. Remove visitors from queue and add to history
        for (int i = 0; i < takeCount; i++) {
            Visitor v = waitingLine.poll();  // Remove front of queue
            addVisitorToHistory(v);          // Add to history
        }

        // 5. Update cycle count
        numOfCycles++;
        System.out.println("[Complete] [" + rideName + "] cycle finished, total cycles run: " + numOfCycles);
    }

    // --------------------------
    // Part6: Export ride history to file
    // --------------------------
    public void exportRideHistory(String filePath) {
        // Validate parameters and history
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("[Failure] Exporting history: File path is empty");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[Failure] Exporting history: [" + rideName + "] history is empty");
            return;
        }

        // try-with-resources: Automatically close stream to avoid resource leaks
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Traverse history and write in CSV format (Visitor ID, Name, Age, Contact, Is Member)
            for (Visitor v : rideHistory) {
                String line = String.join(",",
                        v.getVisitorId(),
                        v.getName(),
                        String.valueOf(v.getAge()),
                        v.getContact(),
                        String.valueOf(v.isMember())
                );
                writer.write(line);
                writer.newLine();  // New line
            }
            System.out.println("[Success] [" + rideName + "] history exported to: " + filePath);
        } catch (IOException e) {
            // Exception handling
            System.out.println("[Error] Failed to export history: " + e.getMessage());
        }
    }

    // --------------------------
    // Part7: Import ride history from file
    // --------------------------
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

        // Clear existing history (optional: decide whether to overwrite based on requirements)
        rideHistory.clear();

        // try-with-resources: Automatically close stream
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int importedCount = 0;
            // Read CSV line by line and parse into Visitor objects
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;  // Skip empty lines

                // Split CSV (split by comma, note: contact info does not contain commas)
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("[Warning] Skipping invalid line: " + line + " (mismatched field count)");
                    continue;
                }

                // Parse fields (handle possible type conversion exceptions)
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
                    System.out.println("[Warning] Skipping invalid line: " + line + " (invalid age/membership status format)");
                }
            }
            System.out.println("[Success] Imported [" + rideName + "] history from " + filePath + ", total imported: " + importedCount + " visitors");
        } catch (IOException e) {
            System.out.println("[Error] Failed to import history: " + e.getMessage());
        }
    }

    // --------------------------
    // Override toString(): Print complete ride status (for debugging)
    // --------------------------
    @Override
    public String toString() {
        return "Ride{" +
                "rideName='" + rideName + '\'' +
                ", rideType='" + rideType + '\'' +
                ", operator=" + (operator != null ? operator.getName() : "Not assigned") +
                ", maxRider=" + maxRider +
                ", numOfCycles=" + numOfCycles +
                ", waitingLineSize=" + waitingLine.size() +
                ", rideHistorySize=" + rideHistory.size() +
                '}';
    }
}