import java.util.Stack;

//this method is similar to a node, contains 
//VIN, Owner, Accidents details
public class Account 
{
	private String VIN; 
	private String owner; 
	private Stack<String> accidents; 
	
	public Account() {}; 
	public Account(String VIN) 
	{ 
		this.VIN=VIN; 
		this.owner=null; 
		accidents= new Stack<String>();
	}
	
	public Account(String VIN, String owner, String acc) 
	{ 
		this.VIN=VIN; 
		this.owner=owner; 
		accidents= new Stack<String>();
		accidents.push(acc);
	}  
	//mutators
	public void setVIN(String VIN) 
	{ 
		this.VIN=VIN;
	} 
	public String getVIN() 
	{ 
		return VIN;
	} 
	
	public void setOwner(String owner) 
	{ 
		this.owner=owner;
	} 
	public String getOwner() 
	{ 
		return owner;
	} 
	
	public void addAccids(String accid) 
	{ 
		accidents.push(accid);
	} 
	public Stack<String> getAccids() 
	{ 
		return accidents;
	} 
	
	public String toString() 
	{ 
		return "VIN is: "+VIN+", Account holder is: "+owner+", and reported accident is: "+accidents.toString();
	}
	
}
