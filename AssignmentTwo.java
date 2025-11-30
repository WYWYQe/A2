import java.util.Queue;

/**
 * Theme Park Management System - Assignment Two Implementation
 * Demonstrates core functionality for theme park ride management, including Part1 class initialization
 * and Part3 waiting queue operations, with placeholders for future expansion (Parts4-7)
 */
public class AssignmentTwo {
    // Main method: Program entry point (supports demo execution for all parts)
    public static void main(String[] args) {
        System.out.println("=== PROG2004 Theme Park Management System (A2) ===");
        testPartOneClasses();

        AssignmentTwo demo = new AssignmentTwo();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part3: Waiting Line (Queue) Demonstration ===");
        demo.partThree();
    }

    /**
     * Helper method to verify Part1 class creation and property assignment
     * Tests Employee, Visitor, and Ride object initialization with complete attribute configuration
     */
    private static void testPartOneClasses() {
        // 1. Create Employee instance with complete Part1 attributes
        Employee rollerCoasterOperator = new Employee(
                "Zhang San", 30, "0412345678",
                "EMP-1001", "Roller Coaster"
        );

        // 2. Create Visitor instance with member status configuration
        Visitor vipVisitor = new Visitor(
                "Li Si", 25, "0487654321",
                "VIS-123456", true
        );

        // 3. Create Ride instance associated with operator (Part1 requirement)
        Ride rollerCoaster = new Ride(
                "Speed Roller Coaster", "Roller Coaster", 4,
                rollerCoasterOperator
        );

        // Verify encapsulation and inheritance through object printing
        System.out.println("\n【Testing Employee Object】");
        System.out.println(rollerCoasterOperator);

        System.out.println("\n【Testing Visitor Object】");
        System.out.println(vipVisitor);

        System.out.println("\n【Testing Ride Object】");
        System.out.println(rollerCoaster);
    }

    // ---------------------- Placeholder methods for future expansion ----------------------

    public void partThree() {
        // Step 1: Initialize Ride with associated Operator (Part1 mandatory requirement)
        System.out.println("\n【Step 1: Initialize Ride and Operator】");
        Employee ferrisWheelOperator = new Employee(
                "Wang Wu", 28, "0498765432",
                "EMP-1002", "Ferris Wheel Operator" // Employee ID and position (Part1 specific attributes)
        );
        Ride giantFerrisWheel = new Ride(
                "Giant Ferris Wheel", "Family Ride", 6, // Ride name, type, max riders per cycle (Part1 attributes)
                ferrisWheelOperator // Associate operator with ride
        );
        System.out.println("Successfully created Ride: " + giantFerrisWheel.getRideName());
        System.out.println("Assigned Operator: " + giantFerrisWheel.getOperator().getName());

        // Step 2: Add minimum 5 Visitors to waiting queue (Part3 mandatory requirement)
        System.out.println("\n【Step 2: Add 5 Visitors to Waiting Queue】");
        // Create 5 visitors with mixed member/non-member status (covers all Visitor attributes)
        Visitor visitor1 = new Visitor("Zhao Liu", 22, "0411223344", "VIS-654321", true);
        Visitor visitor2 = new Visitor("Chen Qi", 35, "0422334455", "VIS-654322", false);
        Visitor visitor3 = new Visitor("Yang Ba", 18, "0433445566", "VIS-654323", true);
        Visitor visitor4 = new Visitor("Huang Jiu", 40, "0444556677", "VIS-654324", false);
        Visitor visitor5 = new Visitor("Zhou Shi", 27, "0455667788", "VIS-654325", true);
        // Add visitors using Ride's queue method (auto-generates success/failure messages)
        giantFerrisWheel.addVisitorToQueue(visitor1);
        giantFerrisWheel.addVisitorToQueue(visitor2);
        giantFerrisWheel.addVisitorToQueue(visitor3);
        giantFerrisWheel.addVisitorToQueue(visitor4);
        giantFerrisWheel.addVisitorToQueue(visitor5);

        // Step 3: Remove 1 visitor from queue (FIFO principle - removes front of queue)
        System.out.println("\n【Step 3: Remove 1 Visitor from Queue (FIFO)】");
        giantFerrisWheel.removeVisitorFromQueue();

        // Step 4: Print remaining visitors to verify removal result
        System.out.println("\n【Step 4: Print Remaining Visitors in Queue】");
        giantFerrisWheel.printQueue();
    }

    /**
     * Will implement visitor ride history tracking functionality
     */
    public void partFourA() {}

    /**
     * Will implement sorting algorithms for ride history data
     */
    public void partFourB() {}

    /**
     * Part Five: Run Ride Cycle (placeholder for future implementation)
     * Will implement ride operation cycle execution logic
     */
    public void partFive() {}

    /**
     * Part Six: Write to File (placeholder for future implementation)
     * Will implement data persistence to file storage
     */
    public void partSix() {}

    /**
     * Part Seven: Read from File (placeholder for future implementation)
     * Will implement data retrieval from file storage
     */
    public void partSeven() {}
}