/**
 * Ride class: Stores information about theme park rides, associates with Employee class (responsible staff)
 * Used to track ride status (e.g., operator availability, maximum capacity, etc.)
 */
public class Ride {
    // 1. Ride instance variables (at least 3, including 1 Employee type)
    private String rideName;       // Ride name (e.g., "Speed Roller Coaster")
    private String rideType;       // Ride type (e.g., "Roller Coaster", "Carousel", "Water Rides")
    private int maxCapacity;       // Maximum capacity (per operation cycle)
    private Employee operator;     // Responsible operator (Employee type, core association)

    // 2. Default constructor (no-arg): Initialize default values
    public Ride() {
        this.rideName = "Unknown Ride";
        this.rideType = "Unknown Type";
        this.maxCapacity = 2; // Default maximum capacity of 2 people
        this.operator = null; // No operator by default
    }

    // 3. Parameterized constructor: Initialize all attributes, including Employee object
    public Ride(String rideName, String rideType, int maxCapacity, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        // Validate maximum capacity: At least 1 person (meets Part 5 "operation cycle" requirements)
        if (maxCapacity >= 1) {
            this.maxCapacity = maxCapacity;
        } else {
            System.out.println("Error: Maximum capacity must be at least 1, defaulting to 2");
            this.maxCapacity = 2;
        }
        this.operator = operator;
    }

    // 4. Getter and Setter methods (including Employee assignment method)
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity >= 1) {
            this.maxCapacity = maxCapacity;
        } else {
            System.out.println("Error: Maximum capacity must be at least 1, modification invalid");
        }
    }

    public Employee getOperator() {
        return operator;
    }

    // Key method: Assign operator to ride (meets task requirement "assign an Employee to operate the ride")
    public void setOperator(Employee operator) {
        // Validate if operator is responsible for current ride type (optional enhanced logic for rationality)
        if (operator != null && operator.getResponsibleRideType().equals(this.rideType)) {
            this.operator = operator;
            System.out.println("Success: Assigned operator [" + operator.getFullName() + "] to [" + rideName + "]");
        } else if (operator != null) {
            System.out.println("Warning: Operator [" + operator.getFullName() + "] is not responsible for [" + rideType + "] type, still forcing assignment");
            this.operator = operator;
        } else {
            this.operator = null;
            System.out.println("Note: Removed operator for [" + rideName + "]");
        }
    }

    // Override toString(): Display core ride information including operator status
    @Override
    public String toString() {
        String operatorInfo = (operator != null) ?
                operator.getFullName() + " (ID: " + operator.getEmployeeId() + ")" :
                "No operator (ride not open)";

        return "Ride{" +
                "rideName='" + rideName + '\'' +
                ", type='" + rideType + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", operator=" + operatorInfo +
                '}';
    }
}