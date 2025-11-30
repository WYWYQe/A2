/**
 * Abstract class: Represents common attributes and behaviors of all "Persons", cannot be instantiated
 * Subclasses (Employee/Visitor) must inherit from this class and extend unique attributes
 */
public abstract class Person {
    // 1. Instance variables (at least 3, conforming to general attributes of "Persons")
    private String fullName;    // Full name
    private int age;            // Age
    private String phoneNumber; // Contact phone number

    // 2. Default constructor (no-arg)
    public Person() {
        this.fullName = "Unknown";
        this.age = 0;
        this.phoneNumber = "Unknown";
    }

    // 3. Parameterized constructor (initializes all instance variables)
    public Person(String fullName, int age, String phoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // 4. Getter and Setter methods (encapsulate attributes to ensure data security)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Simple data validation: Age must be a positive number
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 1-120, current input is invalid");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // Simple data validation: Phone number must be 10 digits (assuming Australian phone number format)
        if (phoneNumber.matches("\\d{10}")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Error: Phone number must be 10 digits, current input is invalid");
        }
    }

    // Override toString(): Facilitates printing object information (for subsequent debugging/demonstration)
    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}