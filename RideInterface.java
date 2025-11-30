/**
 * Ride接口：定义所有Ride对象必须实现的核心行为（Part2要求）
 * 覆盖队列管理（Part3）、骑行历史（Part4）、周期运行（Part5）功能
 */
public interface RideInterface {
    // --------------------------
    // Part3：等待队列管理方法
    // --------------------------
    void addVisitorToQueue(Visitor visitor);   // 添加游客到队列（FIFO）
    void removeVisitorFromQueue();             // 从队列移除队首游客
    void printQueue();                         // 打印队列所有游客

    // --------------------------
    // Part4：骑行历史管理方法
    // --------------------------
    void addVisitorToHistory(Visitor visitor); // 添加游客到骑行历史
    boolean checkVisitorFromHistory(Visitor visitor); // 检查游客是否在历史中
    int numberOfVisitors();                    // 返回历史游客总数
    void printRideHistory();                   // 打印历史所有游客（需用Iterator）

    // --------------------------
    // Part5：骑行周期运行方法
    // --------------------------
    void runOneCycle();                        // 运行一次骑行周期
}