package cache;

public class CacheDTO {
    private String id;
    private String url;
    private String create_time;
    private String data_size; // This can become string array later.
    private String data_name; // This can become string array or be deleted according to our decision.

    public CacheDTO(){}

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getCreate_time(){
        return create_time;
    }

    public void setCreate_time(String create_time){
        this.create_time = create_time;
    }
}
