import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adding sample courses
        courses.add(new Course("CS101", "Introduction to Computer Science", "Fundamentals of programming", 30));
        courses.add(new Course("MA101", "Calculus", "Limits, derivatives, and integrals", 25));
        courses.add(new Course("ENG101", "English Composition", "Writing and critical thinking", 20));

        // Sample student
        Student student = new Student(1, "John Doe");
        students.add(student);

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    registerCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    displayStudentDetails(student);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n----- Menu -----");
        System.out.println("1. Display Course Listing");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. Display Student Details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayCourseListing() {
        System.out.println("\n----- Course Listing -----");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerCourse(Student student) {
        displayCourseListing();
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();

        Course selectedCourse = findCourse(courseCode);
        if (selectedCourse != null) {
            if (student.registerCourse(selectedCourse)) {
                System.out.println("Successfully registered for: " + selectedCourse.getCourseCode());
            } else {
                System.out.println("Failed to register. Course is full.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse(Student student) {
        System.out.println("\n----- Drop Course -----");
        System.out.println(student);
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.nextLine();

        Course selectedCourse = findCourse(courseCode);
        if (selectedCourse != null) {
            if (student.dropCourse(selectedCourse)) {
                System.out.println("Successfully dropped course: " + selectedCourse.getCourseCode());
            } else {
                System.out.println("You are not registered in this course.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void displayStudentDetails(Student student) {
        System.out.println("\n----- Student Details -----");
        System.out.println(student);
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Course Class
    private static class Course {
        private String courseCode;
        private String title;
        private String description;
        private int capacity;
        private int enrolledStudents;

        public Course(String courseCode, String title, String description, int capacity) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolledStudents = 0;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getEnrolledStudents() {
            return enrolledStudents;
        }

        public boolean enrollStudent() {
            if (enrolledStudents < capacity) {
                enrolledStudents++;
                return true;
            }
            return false;
        }

        public boolean dropStudent() {
            if (enrolledStudents > 0) {
                enrolledStudents--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Course: " + courseCode + " - " + title + "\nDescription: " + description +
                   "\nCapacity: " + capacity + "\nEnrolled Students: " + enrolledStudents + "\n";
        }
    }

    // Student Class
    private static class Student {
        private int studentId;
        private String name;
        private List<Course> registeredCourses;

        public Student(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public int getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public List<Course> getRegisteredCourses() {
            return registeredCourses;
        }

        public boolean registerCourse(Course course) {
            if (course.enrollStudent()) {
                registeredCourses.add(course);
                return true;
            }
            return false;
        }

        public boolean dropCourse(Course course) {
            if (registeredCourses.contains(course)) {
                registeredCourses.remove(course);
                course.dropStudent();
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Student ID: ").append(studentId).append("\n");
            sb.append("Name: ").append(name).append("\n");
            sb.append("Registered Courses:\n");
            if (registeredCourses.isEmpty()) {
                sb.append("No registered courses\n");
            } else {
                for (Course course : registeredCourses) {
                    sb.append(course.getCourseCode()).append(" - ").append(course.getTitle()).append("\n");
                }
            }
            return sb.toString();
        }
    }
}
