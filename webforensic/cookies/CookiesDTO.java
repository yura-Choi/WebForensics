package cookies;

public class CookiesDTO {
    private String creation_utc;
    private String top_frame_site_key;
    private String host_key;
    private String name;
    private String value;
    private String encrypted_value;
    private String path;
    private String expires_utc;
    private int is_secure;
    private int is_httponly;
    private String last_access_utc;
    private int has_expires;
    private int is_persistent;
    private int priority;
    private int samesite;
    private int source_scheme;
    private int source_port;
    private int is_same_party;
    private String url;

    public CookiesDTO() {
    }

    public String getCreation_utc() {
        return creation_utc;
    }

    public void setCreation_utc(String creation_utc) {
        this.creation_utc = creation_utc;
    }

    public String getTop_frame_site_key() {
        return top_frame_site_key;
    }

    public void setTop_frame_site_key(String top_frame_site_key) {
        this.top_frame_site_key = top_frame_site_key;
    }

    public String getHost_key() {
        return host_key;
    }

    public void setHost_key(String host_key) {
        this.host_key = host_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEncrypted_value() {
        return encrypted_value;
    }

    public void setEncrypted_value(String encrypted_value) {
        this.encrypted_value = encrypted_value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExpires_utc() {
        return expires_utc;
    }

    public void setExpires_utc(String expires_utc) {
        this.expires_utc = expires_utc;
    }

    public int getIs_secure() {
        return is_secure;
    }

    public void setIs_secure(int is_secure) {
        this.is_secure = is_secure;
    }

    public int getIs_httponly() {
        return is_httponly;
    }

    public void setIs_httponly(int is_httponly) {
        this.is_httponly = is_httponly;
    }

    public String getLast_access_utc() {
        return last_access_utc;
    }

    public void setLast_access_utc(String last_access_utc) {
        this.last_access_utc = last_access_utc;
    }

    public int getHas_expires() {
        return has_expires;
    }

    public void setHas_expires(int has_expires) {
        this.has_expires = has_expires;
    }

    public int getIs_persistent() {
        return is_persistent;
    }

    public void setIs_persistent(int is_persistent) {
        this.is_persistent = is_persistent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSamesite() {
        return samesite;
    }

    public void setSamesite(int samesite) {
        this.samesite = samesite;
    }

    public int getSource_scheme() {
        return source_scheme;
    }

    public void setSource_scheme(int source_scheme) {
        this.source_scheme = source_scheme;
    }

    public int getSource_port() {
        return source_port;
    }

    public void setSource_port(int source_port) {
        this.source_port = source_port;
    }

    public int getIs_same_party() {
        return is_same_party;
    }

    public void setIs_same_party(int is_same_party) {
        this.is_same_party = is_same_party;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

}
