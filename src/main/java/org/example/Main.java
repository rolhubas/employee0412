package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws SQLException {
        Employee x1 = new Employee(2,"Tadas", "Ramanuskas", 10);
        //  EmployeeDAO.create(x1);
        // EmployeeDAO.update(x1);
       // EmployeeDAO.delete(3);
        String paieska = "Rimas";
       // EmployeeDAO.visiIrasai();
        for (Employee x666 : EmployeeDAO.roditivisus()){
            System.out.println(x666);
        }

      /* ArrayList<Employee> x = EmployeeDAO.searchByName(paieska);
        if (x.isEmpty()){
            System.out.println("Irasu nerasta");
        }else {
            System.out.println(x.get(0));
        }
        for (Employee xxx : EmployeeDAO.searchByName(paieska)) {
            System.out.println(xxx);
        }*/

        //  System.out.println("paieska pagal varda " +paieska+ " : " +EmployeeDAO.suskaiciuotiPagalVardus(paieska));
        //   System.out.println("paieska pagal varda " +paieska+ " : " +EmployeeDAO.suskaiciuotiPagalVardus2(paieska)); // veikia

        // System.out.println("paieska pagal varda " +paieska+ " : " +EmployeeDAO.suskaiciuotiPagalVardus3Klaustukas("Rimas"));
    }
}
/*  String paieska = "Rimas";
       ArrayList<Employee> x = EmployeeDAO.searchByName(paieska);
       if (x.isEmpty()){
           System.out.println("Irasu nerasta");
       }else {
           System.out.println(x.get(0));
       }*/