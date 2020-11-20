/*
 * CA3
 */
package dto;

/**
 *
 * @author magda
 */
public class CarDTO {
    /*
    {"id":10,"name":"mimosa","year":2009,"color":"#F0C05A","pantone_value":"14-0848"}
    */
    private int id;
    private String name;
    private int year;
    private String color;
    private String pantone_value;

    public CarDTO(int id, String name, int year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPlates() {
        return pantone_value;
    }
    
    
}
