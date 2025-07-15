import java.util.*;
import java.io.*;

// Class to represent individual students
class Student {
    String name;
    String rollNumber;
    String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade;
    }
}

// Class to manage student operations
public class StudentManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        loadStudents();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addStudent(); break;
                case "2": editStudent(); break;
                case "3": removeStudent(); break;
                case "4": searchStudent(); break;
                case "5": displayAll(); break;
                case "6": saveStudents(); System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter roll number: ");
        String roll = sc.nextLine().trim();
        System.out.print("Enter grade: ");
        String grade = sc.nextLine().trim();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        students.add(new Student(name, roll, grade));
        System.out.println("Student added successfully.");
    }

    static void editStudent() {
        System.out.print("Enter roll number to edit: ");
        String roll = sc.nextLine().trim();
        Student found = null;

        for (Student s : students) {
            if (s.rollNumber.equalsIgnoreCase(roll)) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = sc.nextLine().trim();
        System.out.print("Enter new grade (leave blank to keep current): ");
        String grade = sc.nextLine().trim();

        if (!name.isEmpty()) found.name = name;
        if (!grade.isEmpty()) found.grade = grade;

        System.out.println("Student updated.");
    }

    static void removeStudent() {
        System.out.print("Enter roll number to remove: ");
        String roll = sc.nextLine().trim();
        boolean removed = students.removeIf(s -> s.rollNumber.equalsIgnoreCase(roll));
        if (removed) {
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String roll = sc.nextLine().trim();
        for (Student s : students) {
            if (s.rollNumber.equalsIgnoreCase(roll)) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("\n--- All Students ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void saveStudents() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                out.println(s.name + "," + s.rollNumber + "," + s.grade);
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    static void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
    }
}
