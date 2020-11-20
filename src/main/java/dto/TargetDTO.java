package dto;

/**
 *
 * @author magda
 */
public class TargetDTO {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public TargetDTO(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public String getTitle() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDetails() {
        return body;
    }

    
    
}
