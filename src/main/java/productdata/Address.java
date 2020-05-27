package productdata;

/**
 * Class that represents a Adress data of Product
 */
public class Address {
    private String street; //Поле не может быть null
    private Location town; //Поле может быть null

    /**
     * Standard constructor
     * @param street Name of a street
     * @param town Coordinates of Town
     * @throws NullPointerException If you are trying to build a Address with a null street argument
     */
    public Address(String street, Location town) throws NullPointerException {
        if(street == null) throw new NullPointerException();
        this.street = street;
        this.town = town;
    }

    @Override
    public String toString(){
        return (street + ";" + (town == null ? "не указано": town.output()));
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Address){
            return ((Address) obj).street.equals(street) && ((Address) obj).town.equals(town);
        }
        else return false;
    }

}