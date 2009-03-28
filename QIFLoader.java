/* AUTHOR: David Spadea
 * DATE: 7 February 2009
 * 
 * This code is released to the public under the terms of the GNU GPL version 3.
 * A copy of this code may be found at: http://www.gnu.org/licenses/gpl-3.0.html
 * 
 */

/* This class is an example program using the QIFReader. You can either
   build your program using this as a starting point or just scrap it. 
   Either way, this is not an integral part of the library.
*/

import java.util.*;

import QIF.*;

public class QIFLoader {

	public static void main(String[] args) {
		
		QIFReader reader = new QIFReader();
		QIFTransaction transaction;
		
		reader.open(args[0]);
		
		double total = 0;
		int count = 0;
		while ( reader.more() )
		{	
			transaction = reader.next();
			
			HashMap hmtransaction = transaction.getFriendlyHash();

			System.out.println("Orig: " + transaction.toString());
			System.out.println("Friendly: " + hmtransaction.toString());
			
			total += (Double) hmtransaction.get("amount");
			count++;
		}
		
		System.out.println();
		System.out.printf("%d transactions\n", count);
		System.out.printf("Transaction *net* total: $%.2f\n", total);
	}
}
