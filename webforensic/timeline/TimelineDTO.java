package timeline;

public class TimelineDTO {
    private String id;
    private String table_type;
    private String url;
    private String access_time;

    public TimelineDTO() { }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTable_type() { return table_type; }

    public void setTable_type(String table_type) { this.table_type = table_type; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getAccess_time() { return access_time; }

    public void setAccess_time(String access_time) { this.access_time = access_time; }
}
