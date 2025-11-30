/**
 * Visitor class: Inherits from Person class, stores unique attributes of theme park visitors
 * Used to track visitor access information and membership status
 */
public class Visitor extends Person {
    // 1. Visitor-specific instance variables (at least 2)
    private String visitorCardId; // Visitor card ID (used for admission/consumption records)
    private boolean isVipMember;  // Whether the visitor is a VIP member (true=yes, false=no)

    // 2. Default constructor (no-arg): Calls parent no-arg constructor
    public Visitor() {
        super(); // Call Person's default constructor
        this.visitorCardId = "VIS-000000";
        this.isVipMember = false;
    }

    // 3. Parameterized constructor: Calls parent parameterized constructor, initializes all attributes
    public Visitor(String fullName, int age, String phoneNumber,
                   String visitorCardId, boolean isVipMember) {
        super(fullName, age, phoneNumber); // Initialize parent class common attributes
        this.visitorCardId = visitorCardId;
        this.isVipMember = isVipMember;
    }

    // 4. Getter and Setter methods (subclass-specific attributes)
    public String getVisitorCardId() {
        return visitorCardId;
    }

    public void setVisitorCardId(String visitorCardId) {
        // Simple validation: Visitor card ID format is "VIS-XXXXXX" (XXXXXX is 6-digit number)
        if (visitorCardId.matches("VIS-\\d{6}")) {
            this.visitorCardId = visitorCardId;
        } else {
            System.out.println("Error: Visitor card ID format should be 'VIS-XXXXXX' (XXXXXX is 6-digit number), current input is invalid");
        }
    }

    public boolean isVipMember() {
        return isVipMember;
    }

    public void setVipMember(boolean vipMember) {
        isVipMember = vipMember;
    }

    // Override toString(): Integrate parent and subclass attributes, highlight membership status
    @Override
    public String toString() {
        String vipStatus = isVipMember ? "Yes" : "No";
        return "Visitor{" +
                "visitorCardId='" + visitorCardId + '\'' +
                ", isVipMember=" + vipStatus +
                ", " + super.toString().replace("Person{", "") // Append parent class attributes
                ;
    }
}