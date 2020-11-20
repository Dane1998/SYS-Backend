/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class DadDTO {

    private String id;
    private String joke;
    private int status;

    public DadDTO(String id, String joke, int status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getJoke() {
        return joke;
    }

}
