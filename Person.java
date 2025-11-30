/**
 * Abstract class Person: Defines the abstract model of "person", serving as the base class for all specific roles (employees/visitors, etc.)
 * This class encapsulates common attributes and behaviors of all roles. As an abstract concept, it cannot be instantiated directly and must be inherited by subclasses to implement specific roles
 */
public abstract class Person {
    // Common instance variables (encapsulating basic information of all roles)
    private String name;      // Name: The name identifying an individual
    private int age;          // Age: The age information of an individual
    private String contact;   // Contact information: Information used for communication (e.g., phone number, email, etc.)

    // No-arg constructor: Default constructor for creating Person objects
    public Person() {}

    // Parameterized constructor: Complete constructor for initializing Person objects with specified attribute values
    // @param name Name
    // @param age Age
    // @param contact Contact information
    public Person(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    // Get the name
    // @return Name string
    public String getName() {
        return name;
    }

    // Set the name
    // @param name The name to set
    public void setName(String name) {
        this.name = name;
    }

    // Get the age
    // @return Age value
    public int getAge() {
        return age;
    }

    // Set the age (including data validity verification)
    // @param age The age to set, must satisfy 1 ≤ age ≤ 150
    public void setAge(int age) {
        // Data validation logic: Ensure age is within a reasonable human age range
        if (age > 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 1-150, current input: " + age);
        }
    }

    // Get contact information
    // @return Contact information string
    public String getContact() {
        return contact;
    }

    // Set contact information
    // @param contact The contact information to set
    public void setContact(String contact) {
        this.contact = contact;
    }

    // Override toString method: Returns a string representation of the object for easy printing and log output
    // @return Formatted string containing all attributes of the object
    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", Age=" + age +
                ", Contact='" + contact + '\'';
    }
}