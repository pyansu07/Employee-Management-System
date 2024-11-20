import java.io.*;
import java.util.*;

class MainMenu {
    public void display() {
        System.out.println("\t*******************************************");
        System.out.println("\t      EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("Press 1 : Add Employee Details");
        System.out.println("Press 2 : View Employee Details");
        System.out.println("Press 3 : Remove Employee");
        System.out.println("Press 4 : Update Employee Details");
        System.out.println("Press 5 : Exit the EMS Portal");
    }
}

class EmployeeDetails {
    String id, name, fatherName, email, position, contact, salary;

    public void inputDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        id = sc.nextLine();
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Father's Name: ");
        fatherName = sc.nextLine();
        System.out.print("Enter Email: ");
        email = sc.nextLine();
        System.out.print("Enter Position: ");
        position = sc.nextLine();
        System.out.print("Enter Contact: ");
        contact = sc.nextLine();
        System.out.print("Enter Salary: ");
        salary = sc.nextLine();
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Father's Name: " + fatherName + "\n" +
               "Email: " + email + "\n" +
               "Position: " + position + "\n" +
               "Contact: " + contact + "\n" +
               "Salary: " + salary;
    }
}

class EmployeeManager {
    private static final String FILE_PREFIX = "employee_";

    public void addEmployee(EmployeeDetails employee) throws IOException {
        File file = new File(FILE_PREFIX + employee.id + ".txt");
        if (file.exists()) {
            System.out.println("Employee already exists!");
            return;
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(employee.toString());
        }
        System.out.println("Employee added successfully!");
    }

    public void viewEmployee(String id) throws IOException {
        File file = new File(FILE_PREFIX + id + ".txt");
        if (!file.exists()) {
            System.out.println("Employee not found!");
            return;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
    }

    public void removeEmployee(String id) {
        File file = new File(FILE_PREFIX + id + ".txt");
        if (file.exists() && file.delete()) {
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void updateEmployee(String id, String oldDetail, String newDetail) throws IOException {
        File file = new File(FILE_PREFIX + id + ".txt");
        if (!file.exists()) {
            System.out.println("Employee not found!");
            return;
        }
        StringBuilder fileContent = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                fileContent.append(sc.nextLine()).append("\n");
            }
        }
        String updatedContent = fileContent.toString().replace(oldDetail, newDetail);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(updatedContent);
        }
        System.out.println("Employee details updated successfully!");
    }
}

class ExitApplication {
    public void exit() {
        System.out.println("\nThank you for using the Employee Management System!");
        System.exit(0);
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MainMenu menu = new MainMenu();
        EmployeeManager manager = new EmployeeManager();

        while (true) {
            menu.display();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        EmployeeDetails newEmployee = new EmployeeDetails();
                        newEmployee.inputDetails();
                        manager.addEmployee(newEmployee);
                        break;

                    case 2:
                        System.out.print("Enter Employee ID to view details: ");
                        String viewId = sc.nextLine();
                        manager.viewEmployee(viewId);
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to remove: ");
                        String removeId = sc.nextLine();
                        manager.removeEmployee(removeId);
                        break;

                    case 4:
                        System.out.print("Enter Employee ID to update details: ");
                        String updateId = sc.nextLine();
                        System.out.print("Enter the detail to replace: ");
                        String oldDetail = sc.nextLine();
                        System.out.print("Enter the new detail: ");
                        String newDetail = sc.nextLine();
                        manager.updateEmployee(updateId, oldDetail, newDetail);
                        break;

                    case 5:
                        new ExitApplication().exit();
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
