/**
 * 
 */
package rmi;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author bowen
 *	2018-08-24
 *	构造并注册了一个WarehouseImpl对象
 */
public class WarehouseServer {
	public static void main(String[] args) throws RemoteException,NamingException{
		System.out.println("Constructing server implementation...");
		WarehouseImpl centralWarehouse=new WarehouseImpl();
		
		System.out.println("binding server implementation to registry...");
		Context context=new InitialContext();
		context.bind("rmi:central_warehouse",centralWarehouse);
		
		System.out.println("waiting for invocations from clients...");
	}
}
