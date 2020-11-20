/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class MentorDTO {

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
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;

    public MentorDTO(int id, String employee_name, int employee_salary, int employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public String getName() {
        return employee_name;
    }

    public int getPhone() {
        return employee_salary;
    }

    public int getAge() {
        return employee_age;
    }

    
}
