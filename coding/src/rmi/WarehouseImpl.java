/**
 * 
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bowen
 *	2018-08-24
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {
	
	private Map<String,Double> prices;
	
	public WarehouseImpl() throws RemoteException{
		prices=new HashMap<>();
		prices.put("Blackwell Toaster", 24.95);
		prices.put("ZapXpress Microwave Oven", 49.95);
	}
	/* (non-Javadoc)
	 * @see rmi.Warehouse#getPrice(java.lang.String)
	 */
	@Override
	public double getPrice(String description) throws RemoteException {
		Double price=prices.get(description);
		return price==null?0:price;
	}

}
