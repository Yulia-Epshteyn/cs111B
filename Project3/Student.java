package Project3;

public class Student {
    private String studentName;
    private String id;
    private boolean isTuitionPaid;

    public Student(String initName, String initId, boolean initTuitionPaid){
        studentName = initName;
        id = initId;
        isTuitionPaid = initTuitionPaid;
    }

    // getters
    public String getStudentName() {
        return studentName;
    }
    public String getId() {
        return id;
    }
    public boolean getTuitionPaid() {
        return isTuitionPaid;
    }

    //setters
    public void setStudentName(String newName) {
        studentName = newName;
    }
    public void setId(String newId) {
        id = newId;
    }
    public void setTuitionPaid(boolean newIsTuitionPaid) {
        isTuitionPaid = newIsTuitionPaid;
    }

    public String toString(){
        return  "\nName: " + studentName + ";\nID: " + id + ";\nTuition paid: " + isTuitionPaid + "." ;

    }
}


