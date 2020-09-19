package ticket;
// java -Dfile.encoding=Cp1252 -classpath "C:\Users\Jeet Mishra\eclipse-workspace\MovieTicketBooking\bin;C:\Users\Jeet Mishra\Music\DS\mysql-connector-java-8.0.21\mysql-connector-java-8.0.21.jar" ticket.Client
import java.io.IOException;
import java.util.*;  
import java.io.InputStream;
import java.rmi.*;
import java.util.concurrent.ConcurrentHashMap;
public class Client{

	
	
	
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Client c = new Client();
      
        try{
            Search access1 = (Search)Naming.lookup("rmi://localhost:1900"+"/jb");
            String position = access1.getFreeServer();
            
            //// query child server 
            String url = "rmi://localhost:"+position;
            System.out.println("Server at port "+url);
            Search access = (Search)Naming.lookup(url+"/jb");
            
            int choice =0;
            while(choice!=-1){

                List<Movie> movies = new ArrayList<Movie>();
                String answer;
                System.out.println("Enter "+"\n"+"1:Check Seats Available for a Movie"+"\n"+"2:Book a Movie Ticket"+"\n");
                choice = Integer.parseInt(sc.nextLine());
                if (choice==1){
                    System.out.println("Enter Movie name: ");

                    String movie_name = sc.nextLine();
                    
                    System.out.println("Enter City name: ");

                    String city = sc.nextLine();
                    
                    System.out.println("Enter Area name: ");

                    String area = sc.nextLine();

                    
                    movies = access.checkSeatsAvailable(movie_name,city,area); //1
                    int size = movies.size();
                    if (size==0) {
                    	System.out.println("Currently, this movie is not available at any theatre in this area and city");
                    }
                    
                    else {
                    System.out.println("");
                    for (Movie m:movies) { 
                                
                                // System.out.println("bc "+s.getBranch()); 
//                                System.out.println("Movie ID: " + m.getMovieId()); 
                                System.out.println("Movie name: " + m.getMovieName()); 
//                                System.out.println("Theatre ID: " + m.getTheatreId()); 
                                System.out.println("Theatre name: " + m.getTheatreName());
                                System.out.println("City: " + m.getCityName());
                                System.out.println("Area: " + m.getAreaName());
                                System.out.println("Seats Left: " + m.getSeats());
                                System.out.println("");
                             }
                }
                }
                else if(choice ==2){
                	System.out.println("Enter Movie name");

                    String movie_name = sc.nextLine();
                    
                    System.out.println("Enter Theatre name");

                    String theatre_name = sc.nextLine();

                    System.out.println("Enter City name");

                    String city = sc.nextLine();
                    
                    System.out.println("Enter Location name");

                    String area = sc.nextLine();
                    
                    System.out.println("Enter No of seats required");

                    int seats = Integer.parseInt(sc.nextLine());


                    answer = access.bookTicket(movie_name,theatre_name,city,area,seats); //2
                    
                    System.out.println(answer);
                }
                else if (choice==-1){
                    break;
                }
                else{
                    System.out.println("Wrong Input");
                }

            }
            sc.close();
        }

        catch (Exception ae){
            System.out.println(ae);
        }
    }
}
