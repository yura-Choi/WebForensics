package downloads;

public class DownloadsDTO {
    private int id;
    private String guid;
    private String current_path;
    private String target_path;
    private String start_time;
    //받은 파일 크기가 커봤자 시간 데이터에 비해 엄청 작기에 숫자로 표시함
    //하지만 int로 하기엔 크니 long 사용
    //찾아보니 2기가 정도 파일만 해도 int 넘음
    private long received_bytes;
    private long total_bytes;
    private int state;
    private int danger_type;
    private int interrupt_reason;
    private String hash;
    private String end_time;
    private int opened;
    private String last_access_time;
    //자바에 transient가 이미 있어서 앞에 download를 붙임
    private String dowload_transient;
    private String referrer;
    private String site_url;
    private String tab_url;
    private String tab_referrer_url;
    private String http_method;
    private String by_ext_id;
    private String by_ext_name;
    private String etag;
    //이건 확실히 날짜 형식 통일성을 위해 수정 필요할 수도 있음
    private String last_modified;
    private String mime_type;
    private String original_mime_type;

    public DownloadsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCurrent_path() {
        return current_path;
    }

    public void setCurrent_path(String current_path) {
        this.current_path = current_path;
    }

    public String getTarget_path() {
        return target_path;
    }

    public void setTarget_path(String target_path) {
        this.target_path = target_path;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public long getReceived_bytes() {
        return received_bytes;
    }

    public void setReceived_bytes(long received_bytes) {
        this.received_bytes = received_bytes;
    }

    public long getTotal_bytes() {
        return total_bytes;
    }

    public void setTotal_bytes(long total_bytes) {
        this.total_bytes = total_bytes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDanger_type() {
        return danger_type;
    }

    public void setDanger_type(int danger_type) {
        this.danger_type = danger_type;
    }

    public int getInterrupt_reason() {
        return interrupt_reason;
    }

    public void setInterrupt_reason(int interrupt_reason) {
        this.interrupt_reason = interrupt_reason;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    public String getLast_access_time() {
        return last_access_time;
    }

    public void setLast_access_time(String last_access_time) {
        this.last_access_time = last_access_time;
    }

    public String getDowload_transient() {
        return dowload_transient;
    }

    public void setDowload_transient(String dowload_transient) {
        this.dowload_transient = dowload_transient;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    public String getTab_url() {
        return tab_url;
    }

    public void setTab_url(String tab_url) {
        this.tab_url = tab_url;
    }

    public String getTab_referrer_url() {
        return tab_referrer_url;
    }

    public void setTab_referrer_url(String tab_referrer_url) {
        this.tab_referrer_url = tab_referrer_url;
    }

    public String getHttp_method() {
        return http_method;
    }

    public void setHttp_method(String http_method) {
        this.http_method = http_method;
    }

    public String getBy_ext_id() {
        return by_ext_id;
    }

    public void setBy_ext_id(String by_ext_id) {
        this.by_ext_id = by_ext_id;
    }

    public String getBy_ext_name() {
        return by_ext_name;
    }

    public void setBy_ext_name(String by_ext_name) {
        this.by_ext_name = by_ext_name;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getOriginal_mime_type() {
        return original_mime_type;
    }

    public void setOriginal_mime_type(String original_mime_type) {
        this.original_mime_type = original_mime_type;
    }
}
