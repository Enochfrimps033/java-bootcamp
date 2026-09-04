import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

    static class Employee {
        private final int id;
        private final String name;
        private final byte[] profileData = new byte[256];

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String mode = args[0].toLowerCase();
        switch (mode) {
            case "leak" -> demonstrateLeak();
            case "fix" -> demonstrateFix();
            default -> printUsage();
        }
    }

    private static void demonstrateLeak() {
        System.out.println("===== Memory Leak Demonstration =====");
        System.out.println("Adding employees to a static list that is never cleared...");
        MemoryMonitor.printMemoryReport("Before Allocation");

        List<Employee> employees = LEAK_HOLDER.employees;
        int targetCount = 1_000_000;
        int step = 100_000;

        // TODO: add Employee(i, "Employee-" + i) for i=1..targetCount
        for (int employeeId = 1; employeeId <= targetCount; employeeId++) {
            employees.add(new Employee(employeeId, "Employee-" + employeeId));

            // TODO: every step objects, print count + MemoryMonitor.printMemoryReport
            if (employeeId % step == 0) {
                System.out.println("Employees created: " + employeeId);
                MemoryMonitor.printMemoryReport("After " + employeeId+ " Employees");
            }

        }
    }
    private static void demonstrateFix() {
        System.out.println("===== Memory Leak Fix Demonstration =====");
        MemoryMonitor.printMemoryReport("Before Allocation");

        // TODO: create local ArrayList; add 500_000 employees; print After Allocation
        List<Employee> employees = new ArrayList<>();
        for(int employeeId =1; employeeId<=500000; employeeId++){
            employees.add(new Employee(employeeId, "Employee-" + employeeId));

        }

            // TODO: clear list, null it, trigger GC, print After GC
        MemoryMonitor.printMemoryReport("After Allocation");
        employees.clear();
        employees = null;
        System.gc();
        MemoryMonitor.printMemoryReport("After GC");



        }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java MemoryLeakDemo leak");
        System.out.println("  java MemoryLeakDemo fix");
    }

    private static class LeakHolder {
        private final List<Employee> employees = new ArrayList<>();
    }

    private static final LeakHolder LEAK_HOLDER = new LeakHolder();
}

