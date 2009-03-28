/* AUTHOR: David Spadea
 * DATE: 7 February 2009
 * 
 * This code is released to the public under the terms of the GNU GPL version 3.
 * A copy of this code may be found at: http://www.gnu.org/licenses/gpl-3.0.html
 * 
 */

package QIF;

import java.util.*;

public class QIFTransaction extends HashMap {

	public static void QIFTransaction()
	{
		
	}
	
	public QIFTransaction(HashMap newtrans) 
	{
		for ( Object key : newtrans.keySet() )
		{
			this.put(key, newtrans.get(key));
		}
		
		if (this.containsKey("T")) // amount
		{
			String amount = (String) this.get("T");
			this.put("T", Double.parseDouble(amount.replaceAll("[,$]", "")) );
		}
	}
	
	public HashMap getFriendlyHash() 
	{
		HashMap typemap = new HashMap();
		HashMap friendly = new HashMap();
		
		// Standard fields
		typemap.put("D", "date");
		typemap.put("T", "amount");
		typemap.put("P", "description");
		typemap.put("C", "cleared"); 
		typemap.put("N", "checknumber");
		typemap.put("A", "address");
		typemap.put("M", "memo");
		typemap.put("L", "category");
		typemap.put("F", "reimbursable");
		
		/*
		 * Going to have to do subclasses for Splits 
		 * and Investments because many of the fields are 
		 * re-defined. I think QIFTransaction will wind
		 * up just being a base class, and all of this
		 * work moved to subclasses. 
		 * 
		 */
		
		
		/*
		// Split / Investment common fields
		typemap.put("$", "amount");
		
		// Splits
		typemap.put("S", "splitcategory");
		typemap.put("E", "splitmemo");
		typemap.put("%", "splitpercent");
		
		// Investment types
		
		typemap.put("N", "action"); // Buy, sell...
		typemap.put("Y", "securityname"); 
		typemap.put("I", "price");
		typemap.put("Q", "quantity");
		typemap.put("O", "commission");
		*/
		
		for ( Object key : this.keySet() )
		{
			if ( ! typemap.containsKey(key))
			{
				/*
				 *  I don't want to get into inconsistent
				 * behavior here by falling back to the original key
				 * instead of a mapped one. I'd rather simply not
				 * include it and do the mapping in the next version
				 * of the library. That way we don't have people
				 * relying on the original code and then having it 
				 * break once the mapping is added.
				 * 
				 * The work-around, of course, is not to use
				 * getFriendlyHash(). That way the data is 
				 * still accessible, but in a way that won't 
				 * change in future releases.
				 * 
				 * This strategy should also be subclass-friendly.
				 */
				
				continue;
			}
			
			friendly.put(typemap.get(key), this.get(key));
		}
		
		return friendly;
	}

}