
package dto;

/**
 *
 * @author Dane
 */
public class RestaurantLocationDTO {
    private String address;
    private String city;
    private double latitude;
    private double longitude;
    private int zipcode;

    public RestaurantLocationDTO(String address, String city, double latitude, double longitude, int zipcode) {
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getZipcode() {
        return zipcode;
    }
    
    
}
