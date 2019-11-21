package domain;

public class Movie {    
	private String title;     
	private int year;    
	private double price;

	public Movie()    {
		this("",0,0.0);
	}        

	public Movie(String title, int year, double price)     {        
		setTitle(title);        
		setYear(year);        
		setPrice(price);    
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void setYear(int year){ 
		if (year > 9000)throw new IllegalArgumentException();
		this.year = year;    
	}
	
	public double getPrice(){        
		return this.price; 
	}
	
	public void setPrice(double price){   
		if (price < 0 || price > 50.0){
			throw new IllegalArgumentException("Prijs moet tussen 0 en 50.0 € liggen");
		}
		this.price = price; 
	}
	
	public boolean equals (Object o){
		Movie m = (Movie)o;
		return this.getTitle().equals(m.getTitle());
	}
	
	public String toString(){
		return getTitle()+"-"+getYear();
	}

}

