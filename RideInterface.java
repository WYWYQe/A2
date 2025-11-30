/**
 * RideInterface: Defines core behaviors that all Ride objects must implement
 * Constrains queue management, ride history, operation cycle and other functions to ensure behavioral consistency
 */
public interface RideInterface {
    // --------------------------
    // Part3: Waiting Queue Management (FIFO)
    // --------------------------
    /**
     * Add a visitor to the waiting queue
     * @param visitor The visitor object to be added (non-null)
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * Remove the front visitor from the waiting queue (FIFO principle)
     * Print failure prompt if the queue is empty
     */
    void removeVisitorFromQueue();

    /**
     * Print details of all visitors in the waiting queue (in order of joining)
     * Print prompt message if the queue is empty
     */
    void printQueue();

    // --------------------------
    // Part4: Ride History Management
    // --------------------------
    /**
     * Add a visitor who has completed the ride to the history record
     * @param visitor The visitor object who completed the ride (non-null)
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * Check if a visitor exists in the ride history
     * @param visitor The visitor object to be checked
     * @return true=exists, false=does not exist (or history record is empty)
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * Return the total number of visitors in the ride history
     * @return Number of historical visitors (int, >=0)
     */
    int numberOfVisitors();

    /**
     * Print details of all visitors in the ride history (must traverse with Iterator, required by Part4A)
     * Print prompt message if the history record is empty
     */
    void printRideHistory();

    // --------------------------
    // Part5: Ride Cycle Operation
    // --------------------------
    /**
     * Run one ride cycle: Take visitors from queue → Add to history record → Update operation count
     * Must first check "if there is an operator" and "if the queue has visitors", otherwise cannot run
     */
    void runOneCycle();
}