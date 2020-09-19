package ticket;

import java.sql.*;
import ticket.Search;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;


//java -Dfile.encoding=Cp1252 -classpath "C:\Users\DeepiakP\eclipse-work-space\bankingSystem\bin;D:\mysql-connector-java-8.0.21\mysql-connector-java-8.0.21\mysql-connector-java-8.0.21.jar" bankingSystem.SearchServer
@SuppressWarnings("serial")
public class SearchQuery extends UnicastRemoteObject implements Search {
	private static Integer position = 0;

    String url ="jdbc:mysql://localhost:3306/ticket_booking?serverTimezone=UTC";
	String user ="root";
	String pass ="";
	
	String url1 ="jdbc:mysql://localhost:3306/ticket_booking_backup?serverTimezone=UTC";
	
	
	
   

    SearchQuery() throws RemoteException{
        super();
    }


    public List<Movie> checkSeatsAvailable(String movie_name ,String city , String area) throws RemoteException{
        System.out.println("Client is quering for the availability of seats of movie at a particular city and area .......");
        List<Movie> movies = new ArrayList<Movie>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection(url,user,pass);
			
			HashMap<Integer,Integer> map = new HashMap <Integer,Integer> ();
			int movie_id = 0;
			int theatre_id = 0;
			Statement stmt=con.createStatement();
			String query1 = "SELECT movie_id,movie_name FROM movies WHERE movie_name ='"+ movie_name +"'";
			ResultSet res1=stmt.executeQuery(query1);
//			System.out.println(res1);
//			String returnVal ="";
			while (res1.next()) {
//				System.out.println(res1.getInt("movie_id") + " " + res1.getString("movie_name"));
				movie_id = res1.getInt("movie_id");
		}
//			System.out.println("");
			String query2 = "SELECT theatre_id,theatre_name,city,area FROM theatres WHERE  city = '"+ city +"'AND area = '"+ area +"'";
			ResultSet res2=stmt.executeQuery(query2);
//			System.out.println(res2);
//			String returnVal ="";
			while (res2.next()) {
//				System.out.println(res2.getInt("theatre_id") + " " + res2.getString("theatre_name")+ " " + res2.getString("city") + " " + res2.getString("area"));
				theatre_id = res2.getInt("theatre_id");
//				System.out.println(movie_id);
//				System.out.println(theatre_id);
				map.put(theatre_id,movie_id);
				
		}
			
//		System.out.println(map);
        for (Map.Entry<Integer,Integer> mapElement : map.entrySet()) { 
            theatre_id = (int)mapElement.getKey(); 
  
            // Add some bonus marks 
            // to all the students and print it 
             movie_id = (int)mapElement.getValue() ;
             int seats = 0;
             String theatre_name = "" ;
//             String city;
             
  
//            System.out.println(theatre_id + " : " + movie_id);
            String query3 = "SELECT tickets_left FROM tickets WHERE movie_id ='"+ movie_id +"' AND theatre_id = '"+ theatre_id +"' ";
    		ResultSet res3=stmt.executeQuery(query3);
    		if (!res3.isBeforeFirst() ) {    
    		    continue; 
    		} 
    		while (res3.next()) {
//    			System.out.println(theatre_id + " " + movie_id + " " +res3.getInt("tickets_left"));
    			seats = res3.getInt("tickets_left");
//    			System.out.println(1);
    	}
    		String query4 = "SELECT theatre_name FROM theatres WHERE  theatre_id = '"+ theatre_id +"'";
			ResultSet res4=stmt.executeQuery(query4);
			while (res4.next()) {
//				System.out.println(res4.getString("theatre_name"));
				theatre_name = res4.getString("theatre_name");
			}
//    	System.out.println(seats);
    	Movie movie = new Movie(); 
        movie.setSeats(seats); 
        movie.setMovieName(movie_name); 
        movie.setTheatreName(theatre_name); 
        movie.setCityName(city);
        movie.setAreaName(area);
        movies.add(movie);
        } 
        return movies;
		}
		catch(Exception e) {
			e.printStackTrace();
//			return e.toString();
			Movie movie = new Movie();
			movie.setSeats(0); 
	        movie.setMovieName(null); 
	        movie.setTheatreName(null); 
	        movie.setCityName(null);
	        movie.setAreaName(null);
	        movies.add(movie);
	        return movies;
		}

        
    }
    public String bookTicket(String movie_name,String theatre_name ,String city , String area, int seats) throws RemoteException{
        System.out.println("Client is booking tickets for a movie .......");
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection(url,user,pass);
			Connection con1 =DriverManager.getConnection(url1,user,pass);
			
			
			Statement stmt=con.createStatement();  
			Statement stmt1=con1.createStatement();  
			
			
			int movie_id1 = 0;
			int theatre_id1 = 0;
			int movie_id2 = 0;
			int theatre_id2 = 0;
			int seats1 = 0;
			int seats2=0;
//			String theatre_name = "";
//			String theatre_name2 = "";
			String query1 = "SELECT movie_id,movie_name FROM movies WHERE movie_name ='"+ movie_name +"'";
			ResultSet res1=stmt.executeQuery(query1);
//			System.out.println(res1);
//			String returnVal ="";
			while (res1.next()) {
				System.out.println(res1.getInt("movie_id") + " " + res1.getString("movie_name"));
				movie_id1 = res1.getInt("movie_id");
		}
			ResultSet res2=stmt1.executeQuery(query1);
//			System.out.println(res1);
//			String returnVal ="";
			while (res2.next()) {
				System.out.println(res2.getInt("movie_id") + " " + res2.getString("movie_name"));
				movie_id2 = res2.getInt("movie_id");
		}
			if (movie_id1!= movie_id2) {
				return  "---****-----*****---Server Down---****-----*****---";
			}
			
//			System.out.println("");
			String query2 = "SELECT theatre_id,theatre_name,city,area FROM theatres WHERE  theatre_name ='"+ theatre_name +"'AND city = '"+ city +"'AND area = '"+ area +"'";
			ResultSet res3=stmt.executeQuery(query2);
//			System.out.println(res2);
//			String returnVal ="";
			while (res3.next()) {
				System.out.println(res3.getInt("theatre_id") + " " + res3.getString("theatre_name")+ " " + res3.getString("city") + " " + res3.getString("area"));
				theatre_id1 = res3.getInt("theatre_id");
			}
//			String query2 = "SELECT theatre_id,theatre_name,city,area FROM theatres WHERE  city = '"+ city +"'AND area = '"+ area +"'";
			ResultSet res4=stmt1.executeQuery(query2);
//			System.out.println(res2);
//			String returnVal ="";
			while (res4.next()) {
				System.out.println(res4.getInt("theatre_id") + " " + res4.getString("theatre_name")+ " " + res4.getString("city") + " " + res4.getString("area"));
				theatre_id2 = res4.getInt("theatre_id");
			}
			if (theatre_id1!= theatre_id2) {
				return  "---****-----*****---Server Down---****-----*****---";
			}

			String query3 = "SELECT tickets_left FROM tickets WHERE movie_id ='"+ movie_id1 +"' AND theatre_id = '"+ theatre_id1 +"' ";
    		ResultSet res5=stmt.executeQuery(query3);
    		while (res5.next()) {
    			System.out.println(theatre_id1 + " " + movie_id1 + " " +res5.getInt("tickets_left"));
    			seats1 = res5.getInt("tickets_left");
    			System.out.println(seats1);
    	}
    		ResultSet res6=stmt1.executeQuery(query3);
    		while (res6.next()) {
    			System.out.println(theatre_id2 + " " + movie_id2 + " " +res6.getInt("tickets_left"));
    			seats2 = res6.getInt("tickets_left");
    			System.out.println(seats2);
    	}
    		if (seats1!= seats2) {
				return  "---****-----*****---Server Down---****-----*****---";
			}
    		else {
    			System.out.println("Seats available = "+seats1);
    			System.out.println("Seats required = "+seats);
    			if (seats<=seats1) {
    				seats1 = seats1-seats;
//    				seats2 = seats2-seats;
    				String query = "UPDATE tickets SET tickets_left='"+seats1+"' WHERE movie_id='"+movie_id1+"'AND theatre_id='"+theatre_id1+"'";
    				stmt.executeUpdate(query);  
    				stmt1.executeUpdate(query);  
    				return " ---****-----*****---Tickets Booked for the movie---****-----*****---";
    			}
    			else {
    				return " ---****-----*****---That many seats not available---****-----*****---";
    			}
    		}
        }
		catch(Exception e) {
			e.printStackTrace();
			return e.toString();
		}

        
        
        
     
    }
        @Override
	public String getFreeServer() throws RemoteException {
		// TODO Auto-generated method stub
		HashMap <Integer,String> map=new HashMap<Integer, String>();  

	    map.put(1,"1901");  
	    map.put(2,"1902");  
//	    map.put(3,"1903");  
//	    map.put(4,"1904");  
	    
 
	    synchronized (position) {
            if (position > 1) {
                position = 0;
            }
            
            position++;
        }
	    String target =(String) map.get(position);
        return target;
	}
        
     

    }





