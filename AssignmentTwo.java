/**
 * Main class: Demonstration entry integrating all modules from Part1-7
 * Contains demonstration methods for each module, which can be run directly to verify functionality
 */
public class AssignmentTwo {
    // Define CSV file path constant (reused in Part6-7 to avoid path inconsistency)
    private static final String CSV_FILE_PATH = "ride_history_backup.csv";

    // Main method: Program entry point
    public static void main(String[] args) {
        System.out.println("=== PROG2004 Theme Park Management System (A2) ===");
        AssignmentTwo demo = new AssignmentTwo();

        // Demonstrate modules in order (uncomment modules not needed)
        demo.testPartOneClasses();  // Part1: Class & Inheritance Test
        demo.partThree();           // Part3: Waiting Queue
        demo.partFourA();           // Part4A: Ride History
        demo.partFourB();           // Part4B: History Sorting
        demo.partFive();            // Part5: Cycle Operation
        demo.partSix();             // Part6: File Export
        demo.partSeven();           // Part7: File Import
    }

    // --------------------------
    // Part1: Test Class Creation & Inheritance
    // --------------------------
    private void testPartOneClasses() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part1: Class & Inheritance Test ===");

        // 1. Create Employee (operator)
        Employee rollerOperator = new Employee(
                "Zhang San", 30, "0412345678",
                "EMP-1001", "Roller Coaster Operator"
        );

        // 2. Create Visitor (tourist)
        Visitor vipVisitor = new Visitor(
                "Li Si", 25, "0487654321",
                "VIS-12345", true
        );

        // 3. Create Ride (attraction)
        Ride speedCoaster = new Ride(
                "Speed Roller Coaster", "Thrill Ride",
                4, rollerOperator
        );

        // 4. Print object information (verify encapsulation and inheritance)
        System.out.println("\n【Employee Object】");
        System.out.println(rollerOperator);
        System.out.println("\n【Visitor Object】");
        System.out.println(vipVisitor);
        System.out.println("\n【Ride Object】");
        System.out.println(speedCoaster);
    }

    // --------------------------
    // Part3: Waiting Queue Demonstration
    // --------------------------
    public void partThree() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part3: Waiting Line (Queue) Demo ===");

        // 1. Create Ride and operator
        Employee ferrisOperator = new Employee(
                "Wang Wu", 28, "0498765432",
                "EMP-1002", "Ferris Wheel Operator"
        );
        Ride giantFerrisWheel = new Ride(
                "Giant Ferris Wheel", "Family Ride",
                6, ferrisOperator
        );

        // 2. Add 5 visitors to the queue
        System.out.println("\n【Step1: Add 5 visitors to the queue】");
        giantFerrisWheel.addVisitorToQueue(new Visitor("Mia", 22, "mia@test.com", "VIS-301", true));
        giantFerrisWheel.addVisitorToQueue(new Visitor("Lucas", 29, "lucas@test.com", "VIS-302", false));
        giantFerrisWheel.addVisitorToQueue(new Visitor("Sophia", 21, "sophia@test.com", "VIS-303", true));
        giantFerrisWheel.addVisitorToQueue(new Visitor("Ethan", 35, "ethan@test.com", "VIS-304", false));
        giantFerrisWheel.addVisitorToQueue(new Visitor("Olivia", 27, "olivia@test.com", "VIS-305", true));

        // 3. Remove 1 visitor
        System.out.println("\n【Step2: Remove 1 visitor (FIFO)】");
        giantFerrisWheel.removeVisitorFromQueue();

        // 4. Print remaining queue
        System.out.println("\n【Step3: Print remaining queue】");
        giantFerrisWheel.printQueue();
    }

    // --------------------------
    // Part4A: Ride History Demonstration
    // --------------------------
    public void partFourA() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part4A: Ride History Management Demo ===");

        // 1. Create Ride and operator
        Employee hauntedOperator = new Employee(
                "Liu Yang", 33, "0477665544",
                "EMP-1003", "Haunted House Operator"
        );
        Ride hauntedHouse = new Ride(
                "Haunted House", "Horror Ride",
                3, hauntedOperator
        );

        // 2. Add 5 visitors to history
        System.out.println("\n【Step1: Add 5 visitors to history】");
        hauntedHouse.addVisitorToHistory(new Visitor("Noah", 30, "noah@test.com", "VIS-401", false));
        hauntedHouse.addVisitorToHistory(new Visitor("Emma", 22, "emma@test.com", "VIS-402", true));
        hauntedHouse.addVisitorToHistory(new Visitor("Liam", 28, "liam@test.com", "VIS-403", false));
        hauntedHouse.addVisitorToHistory(new Visitor("Ava", 25, "ava@test.com", "VIS-404", true));
        hauntedHouse.addVisitorToHistory(new Visitor("Elijah", 24, "elijah@test.com", "VIS-405", false));

        // 3. Check if visitor exists in history
        System.out.println("\n【Step2: Check if visitor exists in history】");
        Visitor checkEmma = new Visitor("Emma", 22, "emma@test.com", "VIS-402", true);
        hauntedHouse.checkVisitorFromHistory(checkEmma);
        Visitor checkJack = new Visitor("Jack", 30, "jack@test.com", "VIS-9999", false);
        hauntedHouse.checkVisitorFromHistory(checkJack);

        // 4. Print total number of historical visitors
        System.out.println("\n【Step3: Print total historical visitors】");
        hauntedHouse.numberOfVisitors();

        // 5. Print all historical visitors
        System.out.println("\n【Step4: Print all historical visitors】");
        hauntedHouse.printRideHistory();
    }

    // --------------------------
    // Part4B: Ride History Sorting Demonstration
    // --------------------------
    public void partFourB() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part4B: Ride History Sorting Demo ===");

        // 1. Create Ride, operator and comparator
        Employee bumperOperator = new Employee(
                "Zhang Wei", 31, "0466554433",
                "EMP-1004", "Bumper Cars Operator"
        );
        Ride bumperCars = new Ride(
                "Bumper Cars", "Family Ride",
                4, bumperOperator
        );
        VisitorComparator visitorComparator = new VisitorComparator();  // Custom comparator

        // 2. Add 5 visitors to history
        System.out.println("\n【Step1: Add 5 visitors to history (unsorted)】");
        bumperCars.addVisitorToHistory(new Visitor("Noah", 30, "noah@test.com", "VIS-501", false));
        bumperCars.addVisitorToHistory(new Visitor("Emma", 22, "emma@test.com", "VIS-502", true));
        bumperCars.addVisitorToHistory(new Visitor("Liam", 28, "liam@test.com", "VIS-503", false));
        bumperCars.addVisitorToHistory(new Visitor("Ava", 25, "ava@test.com", "VIS-504", true));
        bumperCars.addVisitorToHistory(new Visitor("Noah", 24, "noah2@test.com", "VIS-505", false));

        // 3. Print history before sorting
        System.out.println("\n【Step2: Print history before sorting】");
        bumperCars.printRideHistory();

        // 4. Sort the history
        System.out.println("\n【Step3: Sort history by rules】");
        bumperCars.sortRideHistory(visitorComparator);

        // 5. Print history after sorting
        System.out.println("\n【Step4: Print history after sorting】");
        bumperCars.printRideHistory();
    }

    // --------------------------
    // Part5: Ride Cycle Operation Demonstration
    // --------------------------
    public void partFive() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part5: Run Ride Cycle Demo ===");

        // 1. Create Ride and operator
        Employee coasterOperator = new Employee(
                "Chen Qi", 35, "0455443322",
                "EMP-1005", "Speed Coaster Operator"
        );
        Ride speedCoaster = new Ride(
                "Speed Coaster", "Thrill Ride",
                4, coasterOperator  // Maximum 4 riders per cycle
        );

        // 2. Add 10 visitors to the queue
        System.out.println("\n【Step1: Add 10 visitors to the queue】");
        for (int i = 1; i <= 10; i++) {
            speedCoaster.addVisitorToQueue(new Visitor(
                    "Visitor" + i, 20 + i,
                    "visitor" + i + "@test.com",
                    "VIS-60" + i, i % 2 == 0  // Even numbers are members
            ));
        }

        // 3. Print queue before operation
        System.out.println("\n【Step2: Print queue before operation】");
        speedCoaster.printQueue();

        // 4. Run 1 cycle
        System.out.println("\n【Step3: Run 1 cycle】");
        speedCoaster.runOneCycle();

        // 5. Print queue after operation
        System.out.println("\n【Step4: Print queue after operation】");
        speedCoaster.printQueue();

        // 6. Print history after operation
        System.out.println("\n【Step5: Print history after operation】");
        speedCoaster.printRideHistory();
    }

    // --------------------------
    // Part6: Export File Demonstration
    // --------------------------
    public void partSix() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part6: Export Ride History to CSV ===");

        // 1. Create Ride and operator, add 5 visitors to history
        Employee waterOperator = new Employee(
                "Yang Ba", 29, "0444332211",
                "EMP-1006", "Water Ride Operator"
        );
        Ride waterSlide = new Ride(
                "Tornado Water Slide", "Water Ride",
                2, waterOperator
        );
        System.out.println("\n【Step1: Add 5 visitors to history】");
        waterSlide.addVisitorToHistory(new Visitor("Mia", 23, "mia@test.com", "VIS-701", true));
        waterSlide.addVisitorToHistory(new Visitor("Lucas", 29, "lucas@test.com", "VIS-702", false));
        waterSlide.addVisitorToHistory(new Visitor("Sophia", 21, "sophia@test.com", "VIS-703", true));
        waterSlide.addVisitorToHistory(new Visitor("Ethan", 35, "ethan@test.com", "VIS-704", false));
        waterSlide.addVisitorToHistory(new Visitor("Olivia", 27, "olivia@test.com", "VIS-705", true));

        // 2. Export history to CSV file
        System.out.println("\n【Step2: Export history to file】");
        waterSlide.exportRideHistory(CSV_FILE_PATH);
        System.out.println("[Note] File saved to project root directory: " + CSV_FILE_PATH);
    }

    // --------------------------
    // Part7: Import File Demonstration
    // --------------------------
    public void partSeven() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== Part7: Import Ride History from CSV ===");

        // 1. Create new Ride object (for import)
        Employee importOperator = new Employee(
                "Huang Jiu", 32, "0433221100",
                "EMP-1007", "Import Test Operator"
        );
        Ride importRide = new Ride(
                "Import Test Ride", "Family Ride",
                5, importOperator
        );

        // 2. Import CSV file exported from Part6
        System.out.println("\n【Step1: Import history from file】");
        importRide.importRideHistory(CSV_FILE_PATH);

        // 3. Print total number of visitors after import
        System.out.println("\n【Step2: Print total visitors after import】");
        importRide.numberOfVisitors();

        // 4. Print all visitors after import
        System.out.println("\n【Step3: Print visitor details after import】");
        importRide.printRideHistory();
    }
}