/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class PackageDTO {

    //car:
    private int year;
    private String plates;
    private String color;
    private String carName;

    private String chuckJoke;
    private String dadJoke;
    //mentor:
    private String MentorName;
    private int phone;
    private int age;

    //target
    private String title;
    private String email;
    private String details;

    public PackageDTO(CarDTO car, ChuckDTO chuck, DadDTO dad, MentorDTO mentor, TargetDTO target) {
        this.year = car.getYear();
        this.plates = car.getPlates();
        this.color = car.getColor();
        this.carName = car.getName();
        this.chuckJoke = chuck.getJoke();
        this.dadJoke = dad.getJoke();
        this.MentorName = mentor.getName();
        this.phone = mentor.getPhone();
        this.age = mentor.getAge();
        this.title = target.getTitle();
        this.email = target.getEmail();
        this.details = target.getDetails();
    }

    @Override
    public String toString() {
        return "PackageDTO{" + "year=" + year + ", plates=" + plates 
                + ", color=" + color + ",\n carName=" + carName 
                + ", chuckJoke=" + chuckJoke + ", dadJoke=" + dadJoke + 
                ", \nMentorName=" + MentorName + ", phone=" + phone + ", "
                + "age=" + age + ", title=" + title + ", \nemail=" + email + 
                ", details=" + details + '}';
    }

}
