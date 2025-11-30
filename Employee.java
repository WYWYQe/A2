/**
 * Employee class: Inherits from Person class, stores unique attributes of theme park staff
 * Used to track information of employees operating amusement rides
 */
public class Employee extends Person {
    // 1. Employee-specific instance variables (at least 2)
    private String employeeId;       // Employee ID (unique identifier)
    private String responsibleRideType; // Type of ride responsible for (e.g., "Roller Coaster", "Water Rides")

    // 2. Default constructor (no-arg): Calls parent no-arg constructor, initializes subclass attributes
    public Employee() {
        super(); // Call Person's default constructor
        this.employeeId = "EMP-0000";
        this.responsibleRideType = "Unknown";
    }

    // 3. Parameterized constructor: Calls parent parameterized constructor, initializes all attributes (parent + subclass)
    public Employee(String fullName, int age, String phoneNumber,
                    String employeeId, String responsibleRideType) {
        super(fullName, age, phoneNumber); // Call Person's parameterized constructor to initialize common attributes
        this.employeeId = employeeId;
        this.responsibleRideType = responsibleRideType;
    }

    // 4. Getter and Setter methods (subclass-specific attributes)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        // Simple validation: Employee ID format is "EMP-XXXX" (XXXX is 4-digit number)
        if (employeeId.matches("EMP-\\d{4}")) {
            this.employeeId = employeeId;
        } else {
            System.out.println("Error: Employee ID format should be 'EMP-XXXX' (XXXX is 4-digit number), current input is invalid");
        }
    }

    public String getResponsibleRideType() {
        return responsibleRideType;
    }

    public void setResponsibleRideType(String responsibleRideType) {
        this.responsibleRideType = responsibleRideType;
    }

    // Override toString(): Integrate parent and subclass attributes for easy printing
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", responsibleRideType='" + responsibleRideType + '\'' +
                ", " + super.toString().replace("Person{", "") // Append parent class attributes
                ;
    }
}