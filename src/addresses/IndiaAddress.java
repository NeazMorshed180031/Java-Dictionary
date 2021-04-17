package addresses;

/*******
 * <p> Title: IndiaAddress Class </p>
 * 
 * <p> Description: A demonstration of a hierarchy of classes inheriting common data </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019-08-27 </p>
 * 
 * @author Neaz Morshed
 * 
 * @version 1.00	2019-08-27 A set of classes that support an array of different kinds of addresses
 * 
 */

public class IndiaAddress extends GenericAddress {
	
	protected String state;
	protected String pincode;

	/**********
	 * Default constructor
	 * 
	 */
	public IndiaAddress() {
		super();
	}

	
	/**********
	 * Fully-specified constructor
	 * 
	 * @param n - Name
	 * @param a - Address
	 * @param c - City
	 * @param z - Pincode
	 * @param s - State
	 * @param cn - Country Name
	 * 
	 */
	public IndiaAddress(String n, String a, String c, String z, String s, String cn) {
		super(n, a, c, cn);
		state = s;
		pincode = z;
	}
	
	/**********
	 * Overridden toString method for this class showing the values of all of the attributes
	 * 
	 * @return a string formatted to show the address in format appropriate for the India
	 */
	
	public String toString() {
		return name + "\n" + address  + "\n" + city + ", " + pincode + " "+ state+" \n" + country;
	}
}