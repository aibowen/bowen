/**
 * 
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author bowen
 *	2018-08-24
 */
public interface Warehouse extends Remote {
	double getPrice(String description)throws RemoteException;
}
