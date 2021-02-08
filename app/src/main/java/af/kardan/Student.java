package af.kardan;

public class Student {
    private String studentName, DOB, program, Term;

    public Student(String studentName, String DOB, String program, String term) {
        this.studentName = studentName;
        this.DOB = DOB;
        this.program = program;
        Term = term;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }
}
