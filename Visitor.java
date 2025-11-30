/**
 * Visitor class: Represents theme park visitors, extending the abstract Person class
 * Contains visitor-specific attributes and methods for identification and membership status
 */
public class Visitor extends Person {
    private String visitorId;  // Unique visitor identification number (specific attribute)
    private boolean isMember;  // Membership status flag (specific attribute)

    // 1. Default constructor: Creates empty Visitor instance
    public Visitor() {}

    // 2. Parameterized constructor: Initializes both parent class and subclass attributes
    // @param name Visitor's name (inherited from Person)
    // @param age Visitor's age (inherited from Person)
    // @param contact Visitor's contact information (inherited from Person)
    // @param visitorId Unique ID for visitor identification
    // @param isMember Membership status (true = member, false = non-member)
    public Visitor(String name, int age, String contact, String visitorId, boolean isMember) {
        super(name, age, contact);  // Call parent class constructor
        this.visitorId = visitorId;
        this.isMember = isMember;
    }

    // --------------------------
    // Required public methods (must match caller naming conventions)
    // --------------------------

    /**
     * Retrieves the unique visitor ID
     * @return Visitor's unique identification string
     */
    public String getVisitorId() {
        return visitorId;
    }

    /**
     * Checks visitor's membership status
     * @return true if visitor is a member, false otherwise
     */
    public boolean isMember() {
        return isMember;
    }

    // Additional setter methods for attribute modification
    /**
     * Sets the visitor's unique ID
     * @param visitorId New unique identification string
     */
    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * Updates visitor's membership status
     * @param member New membership status (true = member)
     */
    public void setMember(boolean member) {
        isMember = member;
    }

    // Override toString for comprehensive object representation
    /**
     * Returns formatted string with visitor details (including parent class attributes)
     * @return String representation of Visitor object
     */
    @Override
    public String toString() {
        return "Visitor{" +
                "Visitor ID='" + visitorId + '\'' +
                ", Is Member=" + (isMember ? "Yes" : "No") +
                ", " + super.toString() +
                '}';
    }
}