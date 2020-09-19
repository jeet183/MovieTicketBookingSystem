package ticket;
import java.util.*;

import java.rmi.*;
////https://www.geeksforgeeks.org/remote-method-invocation-in-java/#:~:text=Through%20RMI%2C%20object%20running%20in,calls%20on%20the%20server%20object.
/////This is an Remote interface which specifies the methods which can be invoked by the remote clients
public interface Search extends Remote{

//// The method should throw this exception
public String getFreeServer() throws RemoteException;


public List<Movie> checkSeatsAvailable(String movie_name , String city , String area) throws RemoteException;

public String bookTicket(String movie_name,String theatre_name ,String city , String area, int seats) throws RemoteException;

//public String deposit(String accNo,float amount) throws RemoteException;



}