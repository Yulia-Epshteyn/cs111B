package Project3;

public class Course {
    private String courseName;
    private int maxEnrolledStudent;
    private int numOfStudents = 0;
    private Student[] roster;

    public Course(String initName, int initMaxEnrolledStudent) {
        courseName = initName;
        maxEnrolledStudent = initMaxEnrolledStudent;
        roster = new Student[maxEnrolledStudent];
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
        if (s.getTuitionPaid() && numOfStudents < getMaxEnrolledStudent()) {
            roster[numOfStudents] = s;
            numOfStudents++;
            System.out.println("\n" + s.getStudentName() + " added in the " + getCourseName()+".");
            return true;
        } else {
            System.out.println("\nSorry, we can not add " + s.getStudentName() + ".");
            return false;
        }
    }

    public boolean dropStudent(Student s) {
        Student[] temp = new Student[roster.length];

        int count = 0;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == s) {
                roster[i] = null;
                numOfStudents--;

                for (int j = 0; j < temp.length; j++) {
                    if (roster[j] != null) {
                        temp[count] = roster[j];
                        count++;
                    }
                }
                System.arraycopy(temp, 0, roster, 0, roster.length);

                System.out.println("\n" + s.getStudentName() + " was deleted from the roster \n");
                return true;
            }
        }
        System.out.println("\n" + s.getStudentName() + " was not found in the roster \n");
        return false;
    }


    public void printRoster() {
        if (numOfStudents == 0){
            System.out.println("There are no students enrolled yet in " + getCourseName());
        }
        else {
            System.out.println("");
            System.out.println(numOfStudents + " students in the " + getCourseName() + " roster.");
            for (Student stud : roster) {
                if (stud != null) {
                    System.out.println(stud);
                }
            }
        }

    }
}


