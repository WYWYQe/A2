public class AssignmentTwo {
    // Main method: Program entry point (can be used to call demo methods for each part later)
    public static void main(String[] args) {
        System.out.println("=== PROG2004 Theme Park Management System (A2) ===");
        // Example: Create objects and test (optional, for debugging Part 1 code)
        testPartOneClasses();
    }

    // Helper method: Test class creation and property assignment for Part 1 (optional, for verification)
    private static void testPartOneClasses() {
        // 1. Create Employee object
        Employee rollerCoasterOperator = new Employee(
                "Zhang San", 30, "0412345678",
                "EMP-1001", "Roller Coaster"
        );

        // 2. Create Visitor object
        Visitor vipVisitor = new Visitor(
                "Li Si", 25, "0487654321",
                "VIS-123456", true
        );

        // 3. Create Ride object (associate with Employee)
        Ride rollerCoaster = new Ride(
                "Speed Roller Coaster", "Roller Coaster", 4,
                rollerCoasterOperator
        );

        // 4. Print object information to verify encapsulation and inheritance
        System.out.println("\n【Testing Employee Object】");
        System.out.println(rollerCoasterOperator);

        System.out.println("\n【Testing Visitor Object】");
        System.out.println(vipVisitor);

        System.out.println("\n【Testing Ride Object】");
        System.out.println(rollerCoaster);
    }

    // ---------------------- Placeholder methods for subsequent parts ----------------------
    // Part Three: Waiting Queue (to be implemented later)
    public void partThree() {}

    // Part Four A: Ride Usage History (to be implemented later)
    public void partFourA() {}

    // Part Four B: Usage History Sorting (to be implemented later)
    public void partFourB() {}

    // Part Five: Run Ride Cycle (to be implemented later)
    public void partFive() {}

    // Part Six: Write to File (to be implemented later)
    public void partSix() {}

    // Part Seven: Read from File (to be implemented later)
    public void partSeven() {}
}