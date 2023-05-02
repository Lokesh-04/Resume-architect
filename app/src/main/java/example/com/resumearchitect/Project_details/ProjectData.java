package example.com.resumearchitect.Project_details;

public class ProjectData {
    private String pro_name;
    private String tech_stk;
    private String pro_desc;
    private String pro_link;

    public ProjectData(String pro_name, String tech_stk, String pro_desc, String pro_link) {
        this.pro_name = pro_name;
        this.tech_stk = tech_stk;
        this.pro_desc = pro_desc;
        this.pro_link = pro_link;
    }

    public String getPro_name() {
        return pro_name;
    }

    public String getTech_stk() {
        return tech_stk;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public String getPro_link() {
        return pro_link;
    }
}
