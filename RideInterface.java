/**
 * Ride Interface: Defines core behaviors that all Ride objects must implement
 * Covers queue management, ride history, and cycle operation functions
 */
public interface RideInterface {
    // --------------------------
    // Part3: Waiting Queue Management Methods
    // --------------------------
    void addVisitorToQueue(Visitor visitor);   // Add visitor to queue (FIFO)
    void removeVisitorFromQueue();             // Remove front visitor from queue
    void printQueue();                         // Print all visitors in queue

    // --------------------------
    // Part4: Ride History Management Methods
    // --------------------------
    void addVisitorToHistory(Visitor visitor); // Add visitor to ride history
    boolean checkVisitorFromHistory(Visitor visitor); // Check if visitor exists in history
    int numberOfVisitors();                    // Return total number of historical visitors
    void printRideHistory();                   // Print all historical visitors (must use Iterator)

    // --------------------------
    // Part5: Ride Cycle Operation Method
    // --------------------------
    void runOneCycle();                        // Run one ride cycle
}