package ipl.ipl;

/**
 * Created by adicool on 9/4/17.
 */

public class Fixtures {
    private String title;
    private String date;
    private String location;
    private String time;

    public Fixtures(String title, String date, String location, String time) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}
