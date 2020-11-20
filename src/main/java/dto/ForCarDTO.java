/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class ForCarDTO {
    /*
    URL: https://reqres.in/api/car/10
    {"data":
        {
        "id":10,
        "name":"mimosa",
        "year":2009,
        "color":"#F0C05A",
        "pantone_value":"14-0848"
        },
    "ad":
           {
            "company": "StatusCode Weekly",
            "url":"http://statuscode.org/",
            "text":"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things."
           }
     }
    
    
    */
    
    
    private CarDTO data;
    private AdDTO ad;

    public ForCarDTO(CarDTO data, AdDTO ad) {
        this.data = data;
        this.ad = ad;
    }

    public CarDTO getCar() {
        return data;
    }
    
    
}

 class AdDTO{
    private String company;
    private String url;
    private String text;

    public AdDTO(String company, String url, String text) {
        this.company = company;
        this.url = url;
        this.text = text;
    }
    
    
}
