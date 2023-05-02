package example.com.resumearchitect.Coursework_details;

public class CourseworkData {
    private String crs_name;
    private String crs_ctf;

    public CourseworkData(String crs_name, String crs_ctf) {
        this.crs_name = crs_name;
        this.crs_ctf = crs_ctf;
    }

    public String getCrs_name() {
        return crs_name;
    }

    public String getCrs_ctf() {
        return crs_ctf;
    }

}
