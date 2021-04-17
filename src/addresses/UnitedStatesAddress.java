package addresses;

/*******
 * <p> Title: UnitedStatesAddress Class </p>
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

public class UnitedStatesAddress extends GenericAddress {
	
	protected String state;
	protected String zipcode;

	/**********
	 * Default constructor
	 * 
	 */
	public UnitedStatesAddress() {
		super();
	}

	
	/**********
	 * Fully-specified constructor
	 * 
	 * @param n - Name
	 * @param a - Address
	 * @param c - City
	 * @param s - State
	 * @param z - zipcode
	 * @param cn - Country Name
	 * 
	 */
	public UnitedStatesAddress(String n, String a, String c, String s, String z, String cn) {
		super(n, a, c, cn);
		state = s;
		zipcode = z;
	}
	
	/**********
	 * Overridden toString method for this class showing the values of all of the attributes
	 * 
	 * @return a string formatted to show the address in format appropriate for the United States
	 */
	
	public String toString() {
		return name + "\n" + address  + "\n" + city + ", " + state + " " + zipcode + "\n" + country;
	}
}