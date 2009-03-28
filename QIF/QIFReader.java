/* AUTHOR: David Spadea
 * DATE: 7 February 2009
 * 
 * This code is released to the public under the terms of the GNU GPL version 3.
 * A copy of this code may be found at: http://www.gnu.org/licenses/gpl-3.0.html
 * 
 */

package QIF;

import java.util.*;
import java.io.*;

public class QIFReader {
	
	BufferedReader qifin;
	String AccountType;
	
	public static void QIFReader()
	{
		
	}
	
	public void open( String qifpath )
	{
		try {
			if ( qifin != null )
			{
				qifin.close();
			}
			
			qifin = new BufferedReader(new FileReader(new File(qifpath)));
			
			AccountType = qifin.readLine();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public QIFTransaction next()
	{
		QIFTransaction transaction = null;
		try {
			transaction = readRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transaction;
	}
	
	public boolean more()
	{
		boolean result = false;
		
		try {
			result = qifin.ready();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
			
	}
	
	private QIFTransaction readRecord()
	{
		String attrib = new String();
		HashMap transaction = new HashMap();
		
		try {
			while ( qifin.ready() )
			{
				try {
					attrib = qifin.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String type = attrib.substring(0,1);
				
				if (type.startsWith("^"))
				{
					break;
				}
				
				String value = attrib.substring(1, attrib.length());

				transaction.put(type, value);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QIFTransaction trans = new QIFTransaction(transaction);
		return trans;
	}
	
}
