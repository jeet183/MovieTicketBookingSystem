package ticket;
import java.sql.*;
import java.util.List;
import java.util.*;

public class TP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url ="jdbc:mysql://localhost:3306/ticket_booking?serverTimezone=UTC";
		String user ="root";
		String pass ="";
		List<Movie> movies = new ArrayList<Movie>();
		HashMap<Integer,Integer> map = new HashMap <Integer,Integer> ();
		try {
			String movie_name = "Dil Bechara";
//			String theatre_name = "Prithvi";
			String city = "Mumbai";
			String area = "Juhu";
			int movie_id = 0;
			int theatre_id = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection(url,user,pass);
			Statement stmt=con.createStatement();
			String query1 = "SELECT movie_id,movie_name FROM movies WHERE movie_name ='"+ movie_name +"'";
			ResultSet res1=stmt.executeQuery(query1);
//			System.out.println(res1);
//			String returnVal ="";
			while (res1.next()) {
				System.out.println(res1.getInt("movie_id") + " " + res1.getString("movie_name"));
				movie_id = res1.getInt("movie_id");
		}
			System.out.println("");
			String query2 = "SELECT theatre_id,theatre_name,city,area FROM theatres WHERE  city = '"+ city +"'AND area = '"+ area +"'";
			ResultSet res2=stmt.executeQuery(query2);
//			System.out.println(res2);
//			String returnVal ="";
			while (res2.next()) {
				System.out.println(res2.getInt("theatre_id") + " " + res2.getString("theatre_name")+ " " + res2.getString("city") + " " + res2.getString("area"));
				theatre_id = res2.getInt("theatre_id");
				System.out.println(movie_id);
				System.out.println(theatre_id);
				map.put(theatre_id,movie_id);
				
		}
			
		System.out.println(map);
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
    			System.out.println(theatre_id + " " + movie_id + " " +res3.getInt("tickets_left"));
    			seats = res3.getInt("tickets_left");
//    			System.out.println(1);
    	}
    		String query4 = "SELECT theatre_name FROM theatres WHERE  theatre_id = '"+ theatre_id +"'";
			ResultSet res4=stmt.executeQuery(query4);
			while (res4.next()) {
				System.out.println(res4.getString("theatre_name"));
				theatre_name = res4.getString("theatre_name");
			}
    	System.out.println(seats);
    	Movie movie = new Movie(); 
        movie.setSeats(seats); 
        movie.setMovieName(movie_name); 
        movie.setTheatreName(theatre_name); 
        movie.setCityName(city);
        movie.setAreaName(area);
        movies.add(movie);
        } 

//		String query3 = "SELECT tickets_left FROM tickets WHERE movie_id ='"+ movie_id +"' AND theatre_id = '"+ theatre_id +"' ";
//		ResultSet res3=stmt.executeQuery(query3);
//		while (res3.next()) {
//			System.out.println(theatre_id + " " + movie_id + " " +res3.getInt("tickets_left"));
//	}
        System.out.println("");
for (Movie m:movies) { 
            
            // System.out.println("bc "+s.getBranch()); 
//            System.out.println("Movie ID: " + m.getMovieId()); 
            System.out.println("Movie name: " + m.getMovieName()); 
//            System.out.println("Theatre ID: " + m.getTheatreId()); 
            System.out.println("Theatre name: " + m.getTheatreName());
            System.out.println("City: " + m.getCityName());
            System.out.println("Area: " + m.getAreaName());
            System.out.println("Seats Left: " + m.getSeats());
            System.out.println("");
         }
			con.close();
			

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
