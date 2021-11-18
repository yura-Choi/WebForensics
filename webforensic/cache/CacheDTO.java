package cache;

public class CacheDTO {
    private String id;
    private String url;
    private String create_time;
    private String data_size;
    private String data_type;
    private String data_name;

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

    public String getData_size(){
        return data_size;
    }

    public void setData_size(String data_size){
        this.data_size = data_size;
    }

    public String getData_type() { return data_type; }

    public void setData_type(String data_type) { this.data_type = data_type; }

    public String getData_name() { return data_name; }

    public void setData_name(String data_name) { this.data_name = data_name; }

}
