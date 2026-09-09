

package com.academy.analytics;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class ReportService {

    private final EmployeeService employeeService;

    public ReportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- CORE (menus 1–9) — keep throwing until implemented ---

    public void displayDashboard() {
        List<Employee> employees = employeeService.getEmployees();

        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        long departmentCount = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .count();

        long activeCount = employees.stream()
                .filter(Employee::isActive)
                .count();

        long inactiveCount = employees.size() - activeCount;

        Optional<Employee> topPerformer =
                employeeService.findTopPerformer();

        Optional<String> highestPaidDepartment =
                employeeService.findDepartmentWithHighestAverageSalary();

        List<Employee> top5Salaries =
                employeeService.getTopSalaries(5);

        System.out.println("=== Dashboard ===");
        System.out.printf("Average Salary : %.0f%n", salaryStats.getAverage());
        System.out.printf("Maximum Salary : %.0f%n", salaryStats.getMax());
        System.out.printf("Minimum Salary : %.0f%n", salaryStats.getMin());
        System.out.println("Department Count : " + departmentCount);
        System.out.println("Active Employees : " + activeCount);
        System.out.println("Inactive Employees : " + inactiveCount);

        topPerformer.ifPresent(
                emp -> System.out.println("Top Performer : " + emp)
        );

        highestPaidDepartment.ifPresent(
                dept -> System.out.println("Highest Paid Department : " + dept)
        );

        System.out.println("Top 5 Salaries:");
        top5Salaries.forEach(System.out::println);
    }


    public void displayEmployeesByDepartment() {
        employeeService.displayGroupedEmployees();
    }

    public void displaySalaryReport() {
        employeeService.displayReductions();
        System.out.println();
        employeeService.displaySummaryStatistics();
        System.out.println();
        employeeService.displayPartitionedEmployees();
    }

    public void displayTopPerformers() {
        System.out.println("Top Performers (Rating >= 4):");
        // TODO (menu 4): employeeService.getTopPerformers(4).forEach(...)


        employeeService.getTopPerformers(4)
                .forEach(System.out::println);
    }

    public void displayHighestSalary() {
        employeeService.displayHighestPaidEmployeeOptional();
    }

    public void displayDepartmentStatistics() {
        // TODO (menu 6): getDepartmentStatistics(); print count/avg/max/min per dept

        employeeService.getDepartmentStatistics()
                .forEach((department, stats) -> {
                    System.out.println("Department: " + department);
                    System.out.println("Count: " + stats.getCount());
                    System.out.println("Average: " + stats.getAverage());
                    System.out.println("Max: " + stats.getMax());
                    System.out.println("Min: " + stats.getMin());
                    System.out.println();
                });
    }

    public void displayActiveEmployees() {
        employeeService.displayActiveEmployees();
    }

    // --- BONUS (menu 21) ---

    public void displayBonusInsights() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }
}
