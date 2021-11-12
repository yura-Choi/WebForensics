package urls;

public class UrlsDTO {
    private String id;
    private String url;
    private String title;
    private String visit_count;
    private String typed_count;
    private String last_visit_time;
    private String hidden;

    public UrlsDTO(){
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getVisit_count(){
        return visit_count;
    }

    public void setVisit_count(String visit_count){
        this.visit_count = visit_count;
    }

    public String getTyped_count(){
        return typed_count;
    }

    public void setTyped_count(String typed_count){
        this.typed_count = typed_count;
    }

    public String getLast_visit_time(){ return last_visit_time; }

    public void setLast_visit_time(String last_visit_time){
        this.last_visit_time = last_visit_time;
    }

    public String getHidden(){
        return hidden;
    }

    public void setHidden(String hidden){
        this.hidden = hidden;
    }



}
