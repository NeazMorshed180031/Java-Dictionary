package addresses;

/*******
 * <p> Title: QatarBusinessAddress Class </p>
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

public class QatarBusinessAddress extends GenericAddress {
	
	protected String businessLine2;
	protected String businessLine3;

	/**********
	 * Default constructor
	 * 
	 */
	public QatarBusinessAddress() {
		super();
	}

	
	/**********
	 * Fully-specified constructor
	 * 
	 * @param n - Business Name
	 * @param bnl2 - Business Name Line 2
	 * @param bnl3 - Business Name Line 3
	 * @param p - Post Office Box
	 * @param c - City
	 * @param cn - Country Name
	 * 
	 */
	public QatarBusinessAddress(String n, String bnl2, String bnl3, String p, String c, String cn) {
		super(n, p, c, cn);
		businessLine2 = bnl2;
		businessLine3 = bnl3;
	}
	
	/**********
	 * Overridden toString method for this class showing the values of all of the attributes
	 * 
	 * @return a string formatted to show the address in format appropriate for the United States
	 */
	
	public String toString() {
		return name + "\n" + businessLine2 + "\n" + businessLine3 + "\n" + address + "\n" + city + ", " + country;
	}
}