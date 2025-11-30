/**
 * 主类：整合Part1-7所有模块的演示入口（Part1要求）
 * 包含各模块的演示方法，可直接运行验证功能
 */
public class AssignmentTwo {
    // 主方法：程序入口（Part1要求）
    public static void main(String[] args) {
        System.out.println("=== PROG2004 Theme Park Management System (A2) ===");
        AssignmentTwo demo = new AssignmentTwo();

        // 按模块顺序演示（可注释不需要的模块）
        demo.testPartOneClasses();  // Part1：类与继承测试
        demo.partThree();           // Part3：等待队列
        demo.partFourA();           // Part4A：骑行历史
        demo.partFourB();           // Part4B：历史排序
        demo.partFive();            // Part5：周期运行
        demo.partSix();             // Part6：导出文件
        demo.partSeven();           // Part7：导入文件
    }

    // --------------------------
    // Part1：测试类创建与继承（Part1要求，可选调试用）
    // --------------------------
    private static void testPartOneClasses() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part1: Class & Inheritance Test ===");

        // 1. 创建Employee（操作员）
        Employee operator = new Employee(
                "Zhang San", 30, "0412345678",
                "EMP-1001", "Roller Coaster Operator"
        );

        // 2. 创建Visitor（游客）
        Visitor vip = new Visitor(
                "Li Si", 25, "0487654321",
                "VIS-12345", true
        );

        // 3. 创建Ride（项目）
        Ride rollerCoaster = new Ride(
                "Speed Roller Coaster", "Thrill Ride",
                4, operator
        );

        // 4. 打印对象信息（验证封装与继承）
        System.out.println("\n【Employee对象】");
        System.out.println(operator);
        System.out.println("\n【Visitor对象】");
        System.out.println(vip);
        System.out.println("\n【Ride对象】");
        System.out.println(rollerCoaster);
    }

    // --------------------------
    // Part3：等待队列演示（Part3要求）
    // --------------------------
    public void partThree() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part3: Waiting Line (Queue) Demo ===");

        // 1. 创建Ride和操作员
        Employee ferrisOperator = new Employee(
                "Wang Wu", 28, "0498765432",
                "EMP-1002", "Ferris Wheel Operator"
        );
        Ride ferrisWheel = new Ride(
                "Giant Ferris Wheel", "Family Ride",
                6, ferrisOperator
        );

        // 2. 添加5个游客到队列（Part3要求：至少5个）
        System.out.println("\n【Step1：添加5个游客到队列】");
        ferrisWheel.addVisitorToQueue(new Visitor("Mia", 22, "mia@test.com", "VIS-001", true));
        ferrisWheel.addVisitorToQueue(new Visitor("Lucas", 29, "lucas@test.com", "VIS-002", false));
        ferrisWheel.addVisitorToQueue(new Visitor("Sophia", 21, "sophia@test.com", "VIS-003", true));
        ferrisWheel.addVisitorToQueue(new Visitor("Ethan", 35, "ethan@test.com", "VIS-004", false));
        ferrisWheel.addVisitorToQueue(new Visitor("Olivia", 27, "olivia@test.com", "VIS-005", true));

        // 3. 移除1个游客（Part3要求）
        System.out.println("\n【Step2：移除1个游客（FIFO）】");
        ferrisWheel.removeVisitorFromQueue();

        // 4. 打印剩余队列（Part3要求）
        System.out.println("\n【Step3：打印剩余队列】");
        ferrisWheel.printQueue();
    }

    // --------------------------
    // Part4A：骑行历史演示（Part4A要求）
    // --------------------------
    public void partFourA() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part4A: Ride History Demo ===");

        // 1. 创建Ride和操作员
        Employee hauntedOperator = new Employee(
                "Liu Yang", 33, "0477665544",
                "EMP-1003", "Haunted House Operator"
        );
        Ride hauntedHouse = new Ride(
                "Haunted House", "Horror Ride",
                3, hauntedOperator
        );

        // 2. 添加5个游客到历史（Part4A要求：至少5个）
        System.out.println("\n【Step1：添加5个游客到历史】");
        hauntedHouse.addVisitorToHistory(new Visitor("Noah", 30, "noah@test.com", "VIS-101", false));
        hauntedHouse.addVisitorToHistory(new Visitor("Emma", 22, "emma@test.com", "VIS-102", true));
        hauntedHouse.addVisitorToHistory(new Visitor("Liam", 28, "liam@test.com", "VIS-103", false));
        hauntedHouse.addVisitorToHistory(new Visitor("Ava", 25, "ava@test.com", "VIS-104", true));
        hauntedHouse.addVisitorToHistory(new Visitor("Elijah", 24, "elijah@test.com", "VIS-105", false));

        // 3. 检查游客是否在历史中（Part4A要求）
        System.out.println("\n【Step2：检查游客是否在历史】");
        Visitor checkEmma = new Visitor("Emma", 22, "emma@test.com", "VIS-102", true);
        hauntedHouse.checkVisitorFromHistory(checkEmma);
        Visitor checkJack = new Visitor("Jack", 30, "jack@test.com", "VIS-999", false);
        hauntedHouse.checkVisitorFromHistory(checkJack);

        // 4. 打印历史人数（Part4A要求）
        System.out.println("\n【Step3：打印历史人数】");
        hauntedHouse.numberOfVisitors();

        // 5. 打印所有历史游客（Part4A要求：用Iterator）
        System.out.println("\n【Step4：打印所有历史游客】");
        hauntedHouse.printRideHistory();
    }

    // --------------------------
    // Part4B：历史排序演示（Part4B要求）
    // --------------------------
    public void partFourB() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part4B: Ride History Sorting Demo ===");

        // 1. 创建Ride、操作员和比较器
        Employee bumperOperator = new Employee(
                "Zhang Wei", 31, "0466554433",
                "EMP-1004", "Bumper Cars Operator"
        );
        Ride bumperCars = new Ride(
                "Bumper Cars", "Family Ride",
                4, bumperOperator
        );
        VisitorComparator comparator = new VisitorComparator();  // 自定义比较器

        // 2. 添加5个游客到历史（Part4B要求：至少5个）
        System.out.println("\n【Step1：添加5个游客到历史（未排序）】");
        bumperCars.addVisitorToHistory(new Visitor("Noah", 30, "noah@test.com", "VIS-201", false));
        bumperCars.addVisitorToHistory(new Visitor("Emma", 22, "emma@test.com", "VIS-202", true));
        bumperCars.addVisitorToHistory(new Visitor("Liam", 28, "liam@test.com", "VIS-203", false));
        bumperCars.addVisitorToHistory(new Visitor("Ava", 25, "ava@test.com", "VIS-204", true));
        bumperCars.addVisitorToHistory(new Visitor("Noah", 24, "noah2@test.com", "VIS-205", false));

        // 3. 打印排序前的历史（Part4B要求）
        System.out.println("\n【Step2：打印排序前的历史】");
        bumperCars.printRideHistory();

        // 4. 排序历史（Part4B要求）
        System.out.println("\n【Step3：排序历史（会员优先→年龄升序）】");
        bumperCars.sortRideHistory(comparator);

        // 5. 打印排序后的历史（Part4B要求）
        System.out.println("\n【Step4：打印排序后的历史】");
        bumperCars.printRideHistory();
    }

    // --------------------------
    // Part5：周期运行演示（Part5要求）
    // --------------------------
    public void partFive() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part5: Run Ride Cycle Demo ===");

        // 1. 创建Ride和操作员
        Employee rollerOperator = new Employee(
                "Chen Qi", 35, "0455443322",
                "EMP-1005", "Roller Coaster Operator"
        );
        Ride speedCoaster = new Ride(
                "Speed Coaster", "Thrill Ride",
                4, rollerOperator  // 单周期载客4人
        );

        // 2. 添加10个游客到队列（Part5要求：至少10个）
        System.out.println("\n【Step1：添加10个游客到队列】");
        for (int i = 1; i <= 10; i++) {
            speedCoaster.addVisitorToQueue(new Visitor(
                    "Visitor" + i, 20 + i,
                    "visitor" + i + "@test.com",
                    "VIS-30" + i, i % 2 == 0  // 偶数为会员
            ));
        }

        // 3. 打印运行前的队列（Part5要求）
        System.out.println("\n【Step2：打印运行前的队列】");
        speedCoaster.printQueue();

        // 4. 运行1个周期（Part5要求）
        System.out.println("\n【Step3：运行1个周期】");
        speedCoaster.runOneCycle();

        // 5. 打印运行后的队列（Part5要求）
        System.out.println("\n【Step4：打印运行后的队列】");
        speedCoaster.printQueue();

        // 6. 打印运行后的历史（Part5要求）
        System.out.println("\n【Step5：打印运行后的历史】");
        speedCoaster.printRideHistory();
    }

    // --------------------------
    // Part6：导出文件演示（Part6要求）
    // --------------------------
    public void partSix() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part6: Export Ride History to File ===");

        // 1. 创建Ride和操作员，添加5个游客（Part6要求：至少5个）
        Employee waterOperator = new Employee(
                "Yang Ba", 29, "0444332211",
                "EMP-1006", "Water Ride Operator"
        );
        Ride waterSlide = new Ride(
                "Tornado Water Slide", "Water Ride",
                2, waterOperator
        );
        System.out.println("\n【Step1：添加5个游客到历史】");
        waterSlide.addVisitorToHistory(new Visitor("Mia", 23, "mia@test.com", "VIS-401", true));
        waterSlide.addVisitorToHistory(new Visitor("Lucas", 29, "lucas@test.com", "VIS-402", false));
        waterSlide.addVisitorToHistory(new Visitor("Sophia", 21, "sophia@test.com", "VIS-403", true));
        waterSlide.addVisitorToHistory(new Visitor("Ethan", 35, "ethan@test.com", "VIS-404", false));
        waterSlide.addVisitorToHistory(new Visitor("Olivia", 27, "olivia@test.com", "VIS-405", true));

        // 2. 导出历史到CSV文件（Part6要求）
        String exportPath = "water_slide_history.csv";  // 文件路径（可自定义）
        System.out.println("\n【Step2：导出历史到文件】");
        waterSlide.exportRideHistory(exportPath);
    }

    // --------------------------
    // Part7：导入文件演示（Part7要求）
    // --------------------------
    public void partSeven() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== Part7: Import Ride History from File ===");

        // 1. 创建Ride和操作员
        Employee importOperator = new Employee(
                "Huang Jiu", 32, "0433221100",
                "EMP-1007", "Import Test Operator"
        );
        Ride importRide = new Ride(
                "Import Test Ride", "Family Ride",
                5, importOperator
        );

        // 2. 导入Part6导出的文件（Part7要求）
        String importPath = "water_slide_history.csv";  // 与Part6的导出路径一致
        System.out.println("\n【Step1：从文件导入历史】");
        importRide.importRideHistory(importPath);

        // 3. 打印导入后的人数（Part7要求）
        System.out.println("\n【Step2：打印导入后的人数】");
        importRide.numberOfVisitors();

        // 4. 打印导入后的所有游客（Part7要求）
        System.out.println("\n【Step3：打印导入后的所有游客】");
        importRide.printRideHistory();
    }
}