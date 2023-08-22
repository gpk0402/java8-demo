package com.epam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsDemo {

	public static void main(String[] args) {

		EmployeesLog employeesLog = new EmployeesLog();
		List<Employee> employees = employeesLog.getEmployees();

		// using collect
		System.out.println("Employee count based on gender:");
		employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())).entrySet()
				.forEach(System.out::println);

		System.out.println("\nEmployee average age based on gender:");
		employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)))
				.entrySet().forEach(System.out::println);

		// using map and distinct
		System.out.println("\nVarious departments");
		employees.stream().map(employee -> employee.getDepartment()).distinct().forEach(System.out::println);

		// using comparator and max
		System.out.println("\nMax salary employee name: ");
		employees.stream().max(Comparator.comparing(emp -> emp.getSalary()))
				.ifPresent(emp -> System.out.println(emp.getName()));

		// using comparator and min
		System.out.println("\nMin salary employee details: ");
		System.out.println(employees.stream().min(Comparator.comparing(employee -> employee.getYearOfJoining())));

		// using sorted
		System.out.println("\n2nd highest salary employee details");
		System.out.println(employees.stream().sorted(Comparator.comparing(emp -> emp.getSalary())).skip(1).findFirst());

		// using filter
		System.out.println("\nAll employees joined after 2015");
		employees.stream().filter(employee -> employee.getYearOfJoining() >= 2015).map(employee -> employee.getName())
				.forEach(System.out::println);
		
		// using partitioningBy to segregate employees based on age
		employees.stream()
        .collect(Collectors.partitioningBy(employee -> employee.getAge() <= 25))
        .forEach((key, empList) -> System.out.println(key + " : " + empList));
		

	}

}
