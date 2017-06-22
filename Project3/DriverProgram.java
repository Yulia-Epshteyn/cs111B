package Project3;

public class DriverProgram {
    public static void main(String[] args) {

        Student stud1 = new Student("s1", "123456", true);
        Student stud2 = new Student("s2", "123AAA", false);
        Student stud3 = new Student("s3", "123BBB", true);
        Student stud4 = new Student("s4", "AAA456", true);
        Student stud5 = new Student("s5", "BBB456", true);
        Student stud6 = new Student("s6", "CCC456", true);

//        for testing Course:
//        Course newCourse = new Course("CS111B", 5);

        CourseAL newCourse = new CourseAL("CS111B", 5);

        System.out.println(newCourse);

        newCourse.printRoster();

        newCourse.addStudent(stud1);
        newCourse.addStudent(stud2);
        newCourse.addStudent(stud3);
        newCourse.addStudent(stud4);
        newCourse.addStudent(stud5);

        newCourse.addStudent(stud6);

        newCourse.printRoster();

        newCourse.dropStudent(stud3);

        newCourse.printRoster();

        newCourse.addStudent(stud6);

        newCourse.printRoster();

    }

}
