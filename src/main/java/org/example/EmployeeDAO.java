package org.example;


import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db2";

    public static void create(Employee xEmploye) {
        //String query = "INSERT INTO `employee`( `name`, `surname`, `salary`) VALUES ('Ona','Sadaite','1900')";
        String query = "INSERT INTO `employee`( `name`, `surname`, `salary`) VALUES (?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, xEmploye.getName());
            statement.setString(2, xEmploye.getSurname());
            statement.setDouble(3, xEmploye.getSalary());
            statement.executeUpdate();
            System.out.println("Irasas DB sukurtas sekmingai");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Ivyko klaida kuriant nauja irasa DB. placiau : " + e.getMessage());
        }
    }

    public static void update(Employee x) throws SQLException {
        //UPDATE `employee` SET `name`='Kazys',`surname`='Stalauskas',`salary`='5000' WHERE `emp_id`=2;
        String eilute = "UPDATE `employee` SET `name`=?,`surname`=?,`salary`=? WHERE `emp_id`=?";
        try {
            Connection con = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = con.prepareStatement(eilute);
            statement.setString(1, x.getName());
            statement.setString(2, x.getSurname());
            statement.setDouble(3, x.getSalary());
            statement.setInt(4, x.getEmpId());
            statement.executeUpdate();
            System.out.println("Irasas atnaujintas sekmingai");
            statement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Įvyko klaida atnaujinant įrašą DB. Plačiau: " + e.getMessage());
        }

    }

    public static void delete(int id) {
        String query = "DELETE FROM `employee` WHERE emp_id = ?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Irasas istrintas sekmingai ");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Įvyko klaida Istrinant įrašą DB. Plačiau: "+ e.getMessage());

        }
    }
    public static void visiIrasai() {
        String query = "SELECT * FROM `employee`";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()){
                System.out.println("Id: " + rs.getInt("emp_id"));
                System.out.println("name" + rs.getString("name"));
                System.out.println("Surname "+ rs.getString("surname"));
                System.out.println("Alga "+rs.getDouble("salary"));

            }


           // System.out.println("Irasas  sekmingai ");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Įvyko klaida Spausdinant įrašus DB. Plačiau: "+ e.getMessage());

        }
    }
    public static ArrayList<Employee> roditivisus() throws SQLException {
        String eilute = "SELECT * FROM `employee`";
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(eilute);

            ResultSet resultSet = statement.executeQuery(eilute);
            while (resultSet.next()) {

                int empId = resultSet.getInt("emp_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                double salary = resultSet.getDouble("salary");

                employees.add(new Employee(empId, name, surname, salary));
            }

            // statement.executeUpdate(); // mta klaida bet paieska atlieka
            System.out.println("Uzklausa ivykdyta sekmingai");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ivyko klaida Spausdinant sarasa DB. Placiau: " + e.getMessage());
        }
        return employees;

    }



    public static ArrayList<Employee> searchByName(String empName) throws SQLException {
        String eilute = "SELECT * FROM `employee` WHERE name LIKE '" + empName + "'";
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(eilute);
            // statement.setString(1,empName);
            ResultSet resultSet = statement.executeQuery(eilute);
            while (resultSet.next()) {

                int empId = resultSet.getInt("emp_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                double salary = resultSet.getDouble("salary");

                employees.add(new Employee(empId, name, surname, salary));
            }

            // statement.executeUpdate(); // mta klaida bet paieska atlieka
            System.out.println("Uzklausa ivykdyta sekmingai");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ivyko klaida ieskant iraso DB. Placiau: " + e.getMessage());
        }
        return employees;

    }

    public static int suskaiciuotiPagalVardus(String name) {

        String sqlEilute = "SELECT COUNT(emp_id) FROM `employee` WHERE name '" + name + "'"; // '"+name+"'
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(sqlEilute);
            statement.setString(1, "emp_id");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("emp_id");
            }
            System.out.println("Uzklausa ivykdyta sekmingai");

        } catch (SQLException e) {
            System.out.println("Nepavyko suskaiciuoti. Placiau : " + e.getMessage());

        }
        return count;
    }

    public static int suskaiciuotiPagalVardus2(String name) {

        String eilute = "SELECT COUNT(emp_id) FROM `employee` WHERE name LIKE '" + name + "'"; // '"+name+"'
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(eilute);
            // statement.setString(1,empName);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(eilute);

            while (rs.next()) {
                count = (rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Nepavyko suskaiciuoti. Placiau : " + e.getMessage());

        }
        return count;
    }

    public static int suskaiciuotiPagalVardus3Klaustukas(String name) {

        String eilute = "SELECT COUNT(emp_id) FROM `employee` WHERE name LIKE ?"; // '"+name+"'
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(eilute);
            statement.setString(1, name);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(eilute);

            while (rs.next()) {
                count = (rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Nepavyko suskaiciuoti. Placiau : " + e.getMessage());

        }
        return count;
    }
  /*  public Integer count(String tableName)  {
        String query = String.format("SELECT COUNT(emp_id) FROM `employee` WHERE name LIKE ?", tableName);
        try (Statement s = connection.createStatement()) {
            try (ResultSet resultSet = queryExecutor.executeQuery(s, query)) {
                Preconditions.checkArgument(resultSet.next(), "Result set is empty");
                return resultSet.getInt("size");
            }
        } catch (SQLException e) {
            throw new CrateException(e);
        }
    }*/


}

