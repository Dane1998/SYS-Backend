
package dto;

/**
 *
 * @author Dane
 */
public class RestaurantLocationDTO {
    private String address;
    private String city;
    private String latitude;
    private String longitude;
    private String zipcode;

    public RestaurantLocationDTO(String address, String city, String latitude, String longitude, String zipcode) {
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getZipcode() {
        return zipcode;
    }
    
    
}
