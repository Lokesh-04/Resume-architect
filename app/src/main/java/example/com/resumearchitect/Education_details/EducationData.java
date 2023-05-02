package example.com.resumearchitect.Education_details;

public class EducationData {
    private String courseName;
    private String university;
    private String passoutYear;
    private String grade;

    public EducationData(String courseName, String university, String passoutYear, String grade) {
        this.courseName = courseName;
        this.university = university;
        this.passoutYear = passoutYear;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getUniversity() {
        return university;
    }

    public String getPassoutYear() {
        return passoutYear;
    }

    public String getGrade() {
        return grade;
    }
}
