package addresses;

/*******
 * <p> Title: GenericAddress Class </p>
 * 
 * <p> Description: A demonstration of a hierarchy of classes inheriting common data </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2018-08-20 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00	2018-08-20 A set of classes that support an array of different kinds of addresses
 * 
 */


public class GenericAddress {
	protected String name;
	protected String address;
	protected String city;
	protected String country;

	/**********
	 * Default constructor
	 * It returns the name, address, city and country 
	 */
	public GenericAddress() {
		name = "";
		address = "";
		city = "";
		country = "";
	}
	
	/**********
	 * Fully-specified constructor
	 * 
	 * @param n - Name
	 * @param a - Address
	 * @param c - City
	 * @param cn - Country Name
	 * 
	 * 
	 */
	public GenericAddress(String n, String a, String c, String cn){
		name = n;
		address = a;
		city = c;
		country = cn;
	}
	
	/**********
	 * Overridden toString method for this class showing the values of all of the attributes
	 * 
	 * @return a string formatted to show the address in a generic form
	 */
	public String toString() {
		return name + "\n" + address  + "\n" + city + "\n" + country;
	}
}
