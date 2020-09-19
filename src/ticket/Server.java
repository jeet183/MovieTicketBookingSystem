package ticket;
//java -Dfile.encoding=Cp1252 -classpath "C:\Users\Jeet Mishra\eclipse-workspace\MovieTicketBooking\bin;C:\Users\Jeet Mishra\Music\DS\mysql-connector-java-8.0.21\mysql-connector-java-8.0.21.jar" ticket.Server
import java.rmi.*;
import java.rmi.registry.*;
public class Server {

    public static void main(String args[]){
    	
    ///https://stackoverflow.com/questions/49118941/how-can-i-create-multiple-servers-with-java-rmi
        System.out.println("The Server has started");
        try {
            ///Create the object of the interface

            Search obj = new SearchQuery();

            //rmi registry within sever
            //port number 1900     
            LocateRegistry.createRegistry(1900);
            Naming.rebind("rmi://localhost:1900"+"/jb",obj);
            
            LocateRegistry.createRegistry(1901);
            Naming.rebind("rmi://localhost:1901"+"/jb",obj);
            
            LocateRegistry.createRegistry(1902);
            Naming.rebind("rmi://localhost:1902"+"/jb",obj);
            
//            LocateRegistry.createRegistry(1903);
//            Naming.rebind("rmi://localhost:1903"+"/jb",obj);
//            
//            
//            LocateRegistry.createRegistry(1904);
//            Naming.rebind("rmi://localhost:1904"+"/jb",obj);
//            
            
            /// try and connect to children 
            
    

        }
        catch (Exception ae){
            System.out.println(ae);
        }
    }
}
