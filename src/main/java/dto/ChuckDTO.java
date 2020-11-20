/*
 * CA3
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author magda
 */
public class ChuckDTO {

    private ArrayList categories = new ArrayList();
    private String created_at;
    private String icon_url;
    private String id;
    private String updated_at;
    private String url;
    private String value;

    public ChuckDTO(String created_at, String icon_url, String id, String updated_at, String url, String value) {
        this.created_at = created_at;
        this.icon_url = icon_url;
        this.id = id;
        this.updated_at = updated_at;
        this.url = url;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getJoke() {
        return value;
    }

}
