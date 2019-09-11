/**
 * 
 */
package rmi;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * @author bowen
 *	2018-08-24
 */
public class WarehouseClient {
	public static void main(String[] args) throws NamingException,RemoteException{
		Context context=new InitialContext();
		
		System.out.println("RMI registry binding: ");
		NamingEnumeration<NameClassPair> nameEnum=context.list("rmi://localhost");
		while(nameEnum.hasMoreElements()){
			System.out.println(nameEnum.nextElement().getName());
		
		}
		String url="rmi://localhost/central_warehouse";
		WarehouseImpl warehouseImpl=(WarehouseImpl)context.lookup(url);
		
		String desc="Blackwell Toaster";
		double price=warehouseImpl.getPrice(desc);
		System.out.println(desc+": "+price);
	}
}
