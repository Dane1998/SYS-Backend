/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class ForMentorDTO {
    /*
     URL: http://dummy.restapiexample.com/api/v1/employee/1
    {"status":"success",
    "data":
        {
        "id":1,
        "employee_name":"Tiger Nixon",
        "employee_salary":320800,
        "employee_age":61,
        "profile_image":""  
        },
    "message":"Successfully! Record has been fetched."}
    */
    private String status;
    private MentorDTO data;
    private String message;

    public ForMentorDTO(String status, MentorDTO data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public MentorDTO getMentor() {
        return data;
    }
    
    
}
