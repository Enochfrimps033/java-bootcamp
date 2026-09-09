package com.academy.analytics;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;
public class EmployeeService {

    private final List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    // --- CORE (menus 1–9) — keep throwing until implemented ---

    public void displayAllEmployees() {
        System.out.println("Total Employees : " + employees.size());
        System.out.println("Employee List");
      employees.stream()
              .forEach(emp->
                  System.out.println("All Employees:"+ emp));

    }

    public void displayActiveEmployees() {
        System.out.println("Active Employees:");
        employees.stream()
                .filter(emp->emp.isActive())
                        .forEach(emp ->System.out.println( emp));
    }

    public void displayGroupedEmployees() {
        employees.stream()
                .collect(Collectors.groupingBy(emp-> emp.getDepartment()))
                .forEach((department,list)-> {
                    System.out.println(department);
                    list.forEach(emp -> System.out.println(emp));
                });
    }


    public void displayReductions() {
      Optional<Double>  highest = employees.stream()
                .map(emp -> emp.getSalary())
                .reduce(Double::max);


        Optional<Double> lowest = employees.stream()
                .map(emp -> emp.getSalary())
                    .reduce(Double::min);



        double total = employees.stream()
                .mapToDouble(emp -> emp.getSalary())
                .sum();

                double average = employees.stream()
                        .mapToDouble(emp -> emp.getSalary())
                        .average()
                        .orElse(0);


                System.out.println("Highest Salary : " + highest.orElse(0.0));
                System.out.println("Lowest Salary : " + lowest.orElse(0.0));
                System.out.printf("Total Salary : %.0f%n", total);
                System.out.printf("Average Salary : %.0f%n", average);


        }

    public void displaySummaryStatistics() {
        // TODO (menu 3): summarizingDouble salary; print max/min/avg/sum/count
        DoubleSummaryStatistics stats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Count: " + stats.getCount());
    }



    public void displayPartitionedEmployees() {
        // TODO (menu 3): partitioningBy salary > 100_000
        Map<Boolean, List<Employee>> partitioned =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                emp -> emp.getSalary() > 100000
                        ));

        System.out.println("Over 100000: " + partitioned.get(true));
        System.out.println("100000 or less: " + partitioned.get(false));
    }

    public void displayHighestPaidEmployeeOptional() {
        // TODO (menu 5): max by salary; ifPresentOrElse
        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresentOrElse(
                        emp -> System.out.println("Highest paid: " + emp),
                        () -> System.out.println("No employees found")
                );
    }

    public Optional<Employee> findTopPerformer() {
        // TODO (menu 8 dashboard): max by rating then salary
        return employees.stream()
                .max(
                        Comparator.comparingDouble(Employee::getRating)
                                .thenComparingDouble(Employee::getSalary)
                );
    }

    public List<Employee> getTopSalaries(int count) {
        // TODO (menu 8 dashboard): sorted salary desc; limit count; toList
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(count)
                .toList();

    }

    public List<Employee> getTopPerformers(int minimumRating) {
        // TODO (menu 4): filter rating >= minimum; sort; toList
        return employees.stream()
                .filter(emp -> emp.getRating() >= minimumRating)
                .sorted(
                        Comparator.comparingDouble(Employee::getRating)
                                .reversed()
                )
                .toList();

    }

    public Map<String, DoubleSummaryStatistics> getDepartmentStatistics() {
        // TODO (menu 6): groupingBy department + summarizingDouble salary
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summarizingDouble(Employee::getSalary)
                        )
                );
    }

    public Optional<String> findDepartmentWithHighestAverageSalary() {
        // TODO (menu 8 dashboard): groupingBy averagingDouble; max entry; map key
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)
                        )
                )
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    // --- BONUS / DEMO (menus 10–21) — stub so explorers do not crash ---

    public void demonstrateLambdas() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void demonstrateFunctionalInterfaces() {
        System.out.println("Bonus / full-path feature — implement after CORE");

    }

    public void demonstrateStreamSources() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayHighSalaryEmployees() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayItEmployees() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayFilteredItTopPerformers() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void demonstrateMapping() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void demonstrateSorting() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayDistinctDepartments() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayTopAndNextSalaries() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void displayCounts() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public void demonstrateCollectors() {
        System.out.println("Bonus / full-path feature — implement after CORE");
    }

    public Optional<Employee> findHighestPaidEmployee() {
        System.out.println("Bonus / full-path feature — implement after CORE");
        return Optional.empty();
    }

    public Optional<Double> findSecondHighestSalary() {
        System.out.println("Bonus / full-path feature — implement after CORE");
        return Optional.empty();
    }

    public Optional<Employee> findEmployeeWithLongestName() {
        System.out.println("Bonus / full-path feature — implement after CORE");
        return Optional.empty();
    }

    public Map<String, Long> generateSalaryHistogram() {
        System.out.println("Bonus / full-path feature — implement after CORE");
        return Map.of();
    }

    public String collectEmployeeSummary() {
        System.out.println("Bonus / full-path feature — implement after CORE");
        return "";
    }
}
