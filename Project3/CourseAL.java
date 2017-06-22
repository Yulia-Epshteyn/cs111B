package Project3;

import java.util.ArrayList;

public class CourseAL {

    private String courseName;
    private int maxEnrolledStudent;
    private ArrayList<Student> roster;

    public CourseAL(String initName, int initMaxEnrolledStudent) {
        courseName = initName;
        maxEnrolledStudent = initMaxEnrolledStudent;
        roster = new ArrayList<Student>();
    }

    //getters
    public String getCourseName() {
        return courseName;
    }

    public int getMaxEnrolledStudent() {
        return maxEnrolledStudent;
    }

    //setters
    public void setCourseName(String newCourseName) {
        courseName = newCourseName;
    }

    public void setMaxEnrolledStudent(int newMaxEnrolledStudent) {
        if (newMaxEnrolledStudent > 0) {
            maxEnrolledStudent = newMaxEnrolledStudent;
        } else {
            System.out.println("Invalid input! The new number of student must be positive");
        }
    }

    public String toString() {
        return "Course name: " + courseName + ";\nThe maximum number enrolled students: " + maxEnrolledStudent + ";";
    }

    public boolean addStudent(Student s) {
        if (s.getTuitionPaid() && roster.size() < getMaxEnrolledStudent()) {
            roster.add(s);
            System.out.println("\n" + s.getStudentName() + " added in the " + getCourseName() + ".");
            return true;
        } else {
            System.out.println("\nSorry, we can not add " + s.getStudentName() + ".");
            return false;
        }
    }

    public boolean dropStudent(Student s) {

       if (roster.contains(s)){
           roster.remove(s);
           System.out.println("\n" + s.getStudentName() + " was deleted from the roster \n");
           return true;
       }

        System.out.println("\n" + s.getStudentName() + " was not found in the roster \n");
        return false;
    }


    public void printRoster() {
        if (roster.size() == 0){
            System.out.println("There are no students enrolled yet in " + getCourseName());
        }
        else {
            System.out.println("");
            System.out.println(roster.size() + " students in the " + getCourseName() + " roster.");
            for (Student stud : roster) {
                if (stud != null) {
                    System.out.println(stud);
                }
            }
        }

    }
}
