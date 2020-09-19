package ticket;

@SuppressWarnings("serial")
public class Movie implements java.io.Serializable {   
	   private int seats;   
	   private String theatre_name, movie_name,city,area;    
	  
//	   public int getMovieId() { 
//	      return movie_id; 
//	   }
//	   public int getTheatreId() { 
//	      return theatre_id; 
//	   }  
	   public String getMovieName() { 
	      return movie_name; 
	   }

	   public int getSeats(){
	      return seats;
	   } 

	   public String getTheatreName() { 
	      return theatre_name; 
	   }
	   
	   public String getCityName() {
		   return city;
	   }
	   public String getAreaName() {
		   return area;
	   }
//	   public void setMovieID(int movie_id) { 
//	      this.movie_id = movie_id; 
//	   }
//	   public void setTheatreID(int theatre_id) { 
//	      this.theatre_id = theatre_id; 
//	   }
	   public void setSeats(int seats) { 
	      this.seats = seats; 
	   } 
	   public void setMovieName(String movie_name) { 
	      this.movie_name = movie_name; 
	   }
	   public void setTheatreName(String theatre_name) { 
	      this.theatre_name = theatre_name; 
	   }
	   public void setAreaName(String area) { 
		      this.area = area; 
		   }
	   public void setCityName(String city) { 
		      this.city = city; 
		   }

	}
