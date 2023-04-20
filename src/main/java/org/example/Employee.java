package org.example;


public class Employee {
    int empId;
    String name;
    String surname;
    double salary;
    public  Employee(){

    }

    public Employee(int empId, String name, String surname, double salary) {
        this.empId = empId;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
    public Employee( String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Salary=" + salary +
                '}';
    }
}
