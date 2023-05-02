package example.com.resumearchitect.Experience_details;

public class ExperienceData {
    private String exp_name;
    private String exp_type;
    private String timeline;
    private String desc;

    public ExperienceData(String exp_name, String exp_type, String timeline, String desc) {
        this.exp_name = exp_name;
        this.exp_type = exp_type;
        this.timeline = timeline;
        this.desc = desc;
    }

    public String getExp_name() {
        return exp_name;
    }

    public String getExp_type() {
        return exp_type;
    }

    public String getTimeline() {
        return timeline;
    }

    public String getDesc() {
        return desc;
    }
}
