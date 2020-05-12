// -----------------------------------------------------
// Assignment 4 
// Part 1
// Written by: Hamzah Sheikh 40103993 & Muhammad Ferreira 40113326 
// -----------------------------------------------------
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;  



public class CVR 
{
	Scanner scan = new Scanner(System.in);
	
	//this will be used to generate random alpha numeric numbers
	private final static String alphaNumeric="ABDCEFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
	//key
	private String VIN; 
	
	//threshold (determines which ADT to use)
	private int threshold; 
	
	//length of key
	private int KeyLength; 
	
	//this is an object of Archive which will hold the data associated with VIN
	private Account value; 
	
	//TBD
	//private Collection<Account> activeVINS;
	
	//HashMap to store all the key-value pairs 
	//the value come in the form of a stack because, 
	//multiple events can be associated with the same  
	//VIN, and must be shown in reverse-chronological order
	private TreeMap<String, Account> treeRecords;  
	
	private Hashtable<String, Account> a = new Hashtable<String, Account>(); 
	private LinkedList<String> seqRecords = new LinkedList<String>();
	

	
	private boolean treeTabl=false; 
	
	//default constructor
	public CVR(int threshold) throws Exception  
	{
		this.setThreshold(threshold); 
		if (threshold>=1000) 
		{
			treeRecords=new TreeMap<>(new lexicographicComparator()); 
			this.treeTabl=true; 
			
		} 
		else 
		{ 
			this.treeTabl=false; 
		}
	}
	
	
	//threshold getters and setters 
	public int getThreshold() 
	{ 
		return threshold;
	} 
	//for this one we have to keep in mind the restriction set 
	//on us in the instructions
	public void setThreshold(int threshold)
	{ 
		while ( threshold < 100|| threshold > 900000 ) {	
			System.out.println("CHANGE VALUE OF THE THRESHOLD!");
			threshold = scan.nextInt();
		}
		this.threshold = threshold;	
	} 
	
	
	//VINLength getters and setters 
	public int getKeyLength() 
	{ 
		return KeyLength;
	} 
		//again for this one. we need to take the 
		//instructions into account for this special 
		//case 
	public void setKeyLength(int keyLength) throws Exception
    {	
		while ( keyLength < 10|| keyLength > 17  ) {	
			System.out.println("length value is invalid");
			keyLength = scan.nextInt();
		}
		this.KeyLength = keyLength;	
	}
	
	
	
	
	
	
	
	
	
	//Now onto the methods 
	//Generate method 
	//This method should randomly generate a sequence 
	//containing n new non-existing valid keys 
	//***Must determine whether the output is a sequence or not
	
	public String generate(int size) throws Exception 
	{ 
		
		char[] Arr= alphaNumeric.toCharArray(); 
		String[] ender=new String[size];
		  
		
		//generating random number between 10 and 17 
		Random r= new Random(); 
		int low=10; 
		int high=17; 
		for(int x=0; x<size;x++) 
		{  
			int highLow=r.nextInt(KeyLength)+10;
			StringBuilder newString=new StringBuilder();
			//making string between length of 10 and 17 randomly
			for(int i=0; i<KeyLength; i++) 
			{ 
				newString.append(Arr[new Random().nextInt(Arr.length)]); 
			} 
			/////////////////// 
			String newVIN=newString.toString(); 
			//System.out.println(newVIN);  
			
			//if threshold is>1000
			if(treeTabl==true)  
			{   
				//if treeRecords contains the new VIN, reduc x by one so  
				//the method makes another random string to add to the  
				//xth index
				if(treeRecords.containsKey(newVIN)) 
				{
					x--; 
				}
				//else add to xth index of arrray
				else 
				{ 
					ender[x]=newString.toString(); 
				}  
			}  
			//if threshol is<1000
			else 
			{ 
				//if seqRecords contains the new VIN, reduc x by one so  
				//the method makes another random string to add to the  
				//xth index
				if(seqRecords.contains(newVIN)) 
				{ 
					x--;
				}  
				//else add to xth index of arrray
				else 
				{ 
					ender[x]=newString.toString();
				} 
			}
		}   
		//System.out.println("hello");
		System.out.println(Arrays.toString(ender));
		return Arrays.toString(ender);
	} 
	

	
	
	
	
	
	
	//re-visit
	//allKeys method will return a sorted sequence of all keys 
	public Object[] allKeys() 
	{ 
		if(treeTabl==true) 
		{ 
			
			Object[] arr=treeRecords.keySet().toArray();
			return arr;
		}
		else 
		{  
			Object[] q = seqRecords.toArray();
			Arrays.sort(q);
			return q;
		}
	}
	
	
	
	
	
	
	//add method 
	//****must check to see if must be resized later
	public void add(String VIN, Account value) throws Exception
	{ 
		String test1=value.getOwner();
		//System.out.println(treeTabl);
		if(treeTabl==true) 
		{	
			//If VIN does not match VIN associated with account
			if(!VIN.equals(value.getVIN())) 
			{  
				System.out.println("Something went wrong :/");
				throw new Exception("VIN does not match account");  
			}   
			//if treeRecords already contains this VIN
			else if(treeRecords.containsKey(VIN)) 
			{ 
				
				//the following operation requires a pop, so I must check if stack is empty
				if(!value.getAccids().isEmpty()) 
				{
					 
					
					//if(!value.getOwner().equals("") || !value.getOwner().equals(null)) 
					if(test1!=null && !test1.isEmpty())
					{
						treeRecords.get(VIN).setOwner(value.getOwner()); 
					}
					//This has to do with an assumption that we are only adding 
					//one accident at a time, this means the given object will always 
					//have a stack with only one element inside of it, unless 
					//the stack is empty, which is dealt with above
					treeRecords.get(VIN).addAccids(value.getAccids().pop()); 
					//we will also add the account owner name 
					
					System.out.println("VIN exists, adding to record"); 
				} 
				//if stack is empty and VIN is already in the system 
				//but account has no owner, set account owner to "value 
				//owner (this may end up being the same result
				else if(test1!=null && !test1.isEmpty())
				{ 
					treeRecords.get(VIN).setOwner(value.getOwner()); 
					System.out.println("VIN exists, adding to record");
				}  
				else 
				{ 
					System.out.println("values already exists");
				}
				
			} 
			else 
			{  
				treeRecords.put(VIN, value); 
				System.out.println("New account made, record added!");
			} 
		}   
		
		
		
		//This case we will use a sequence
		else 
		{ 
			if(!seqRecords.contains(VIN)) 
			{ 
				
				
				a.put(VIN,value);
				seqRecords.add(VIN);
				System.out.println("New account made, record added!");	
			}
			else
			{
				if(!value.getAccids().isEmpty()) 
				{
					
					a.get(VIN).addAccids(value.getAccids().pop()); 
					 
					
					//if(!value.getOwner().equals("") || !value.getOwner().equals(null)) 
					if(test1!=null && !test1.isEmpty())
					{
						a.get(VIN).setOwner(value.getOwner()); 
					} 
				} 
				else if(test1!=null && !test1.isEmpty())
				{ 
					 
					
					//if(!value.getOwner().equals("") || !value.getOwner().equals(null)) 
					if(test1!=null && !test1.isEmpty())
					{
						a.get(VIN).setOwner(value.getOwner()); 
					}
				} 
				System.out.println("VIN exists, adding to record");
			}
		
		}
	} 
	
	
	//remove method 
	//***must check to see if must be resized later 
	public void remove(String VIN) 
	{ 
		if(treeTabl==true) 
		{	
			if(treeRecords.containsKey(VIN)) 
			{ 
				treeRecords.remove(VIN); 
				//resize here 
				//
			} 
			else 
			{ 
				System.out.println("Key does not exist in treeTable");
			} 
		} 
		else 
		{ 
			if(a.containsKey(VIN)) 
			{	
				seqRecords.remove(VIN);
				a.remove(VIN);
			}
			else 
			{ 
				System.out.println("Key does not exist");
			} 
		}
	} 
	
	//getValues method 
	public Account getValues(String VIN) 
	{ 
		if(treeTabl == true) 
		{
			if(treeRecords.containsKey(VIN)) 
			{ 
				return treeRecords.get(VIN);
			} 
			else 
			{ 
				System.out.println("This VIN could not be found in directory"); 
				return null;
			} 
		} 
		else 
		{ 
			if(a.containsKey(VIN))
				return a.get(VIN);
		
			System.out.println("This VIN could not be found in directory"); 
			return null;
		}
	}  
	
	//nextKey method 
	public String nextKey(String VIN) 
	{ 
		//unfinished, not sure what to call here
		if(treeTabl==true) 
		{ 
			if(treeRecords.containsKey(VIN)) 
			{
				return treeRecords.higherKey(VIN); 
			} 
			return "Not Found";
		} 
		else 
		{ 
			Object[] s;
			s=seqRecords.toArray();
			Arrays.sort(s);
			
			for(int i=0; i<s.length;i++) 
			{ 
				if(s[i].equals(VIN)) 
				{ 
					if(i==s.length-1) 
					{ 
						return "does not exist";
					} 
					return s[i+1].toString();
				}
			} 
			return "not found";
		}
	}
	
	//prevKey methods 
		public String prevKey(String VIN) 
		{ 
			//unfinished, not sure what to call here
			if(treeTabl==true) 
			{ 
				if(treeRecords.containsKey(VIN)) 
				{
					return treeRecords.lowerKey(VIN); 
				} 
				return "Not Found";
			} 
			else 
			{ 
				Object[] s;
				s=seqRecords.toArray();
				Arrays.sort(s);
				
				for(int i=0; i<s.length;i++) 
				{ 
					if(s[i].equals(VIN)) 
					{ 
						if(i==0) 
						{ 
							return "does not exist";
						} 
						return s[i-1].toString();
					}
				} 
				return "not found";
			}
		}
	
	//previous Accidents method 
	public Stack<String> prevAccids(String VIN)
	{ 
		if(treeTabl == true) 
		{ 
			if(treeRecords.containsKey(VIN)) 
			{ 
				
				Stack<String> temp;  
				temp=treeRecords.get(VIN).getAccids();  
				return temp;
			} 
			return null;

 		} 
		else 
		{ 
			if(a.containsKey(VIN)) 
			{ 
				
				Stack<String> temp;  
				temp=a.get(VIN).getAccids();  
				return temp;
			} 
			return null;
		}
	}
	
	//driver method
	public static void main(String[] args) throws Exception 
	{ 
		CVR hello= new CVR(100);  
		CVR hello2= new CVR(1000);
		try 
		{
			//Testing stuff
			//System.out.println("hello");
			//hello.generate(5);  
			Account abdcg=new Account("bdsj4jandnj4", "Muhammad Ferreira", "perfect record");  
			Account abdcg1=new Account("adsj4jandnj4","Myriam Ferreira", "Fender Bender"); 
			Account abdcg2= new Account("adsj4jandnj4", null, null); 
			Account abdcg3 = new Account("adsj4jandnj4"); 
			Account abdcg4=new Account("bdsj4jandnj4", null, "actually, 1 fender bender..."); 
			Account abdcg5=new Account("cdsj4jandnj4", "Hamzah Sheikh", "smooched jasmine"); 
			Account abdcg6=new Account("adsj4jandnj3", "Muhammad Ferreira", "actually, 1 fender bender...");
			
			
			//adding Accounts to CVR
			hello.add("bdsj4jandnj4", abdcg); 
			System.out.println(); 
			hello.add("adsj4jandnj4", abdcg1);  
			System.out.println();
			hello.add("adsj4jandnj4", abdcg2); 
			System.out.println();
			hello.add("adsj4jandnj4", abdcg3); 
			System.out.println();
			hello.add("bdsj4jandnj4", abdcg4); 
			System.out.println();
			hello.add("cdsj4jandnj4", abdcg5); 
			System.out.println();
			hello.add("adsj4jandnj3", abdcg6);
			System.out.println(); 
			System.out.println();
			//Testing allKeys method
			Object[] q=hello.allKeys();
			System.out.println(Arrays.toString(q)); 
			
			System.out.println(); 
			System.out.println(); 
			
			//Testing getValues method
			System.out.println(hello.getValues("bdsj4jandnj4"));  
			System.out.println(); 
			System.out.println(); 
			
			//testing Remove
			hello.remove("bdsj4jandnj4");
			System.out.println(Arrays.toString(hello.allKeys())); 
			System.out.println(); 
			System.out.println(); 
			
			//Testing prevAccids
			Stack<String> stack=hello.prevAccids("adsj4jandnj4"); 
			while(stack!=null && !stack.isEmpty()) 
			{ 
				System.out.println(stack.pop());
			}
			System.out.println(); 
			System.out.println(); 
			
			//Testing nextKey 
			System.out.println(hello.nextKey("adsj4jandnj3")); 
			System.out.println(); 
			System.out.println(); 
			
			//Testing prevKey 
			System.out.println(hello.prevKey("adsj4jandnj3")); 
			System.out.println(); 
			System.out.println();
			//Testing generate  
			hello.setKeyLength(10);
			hello.generate(10); 
			
			
			
			
			//Adding file
			String s="ar_test_file10.txt";
			File fileName=new File(s);
			Scanner scanny= new Scanner(fileName); 
			Scanner scanny2= new Scanner(fileName);
			 int r=0; 
			 //to find threshold size
			 while(scanny.hasNextLine()) 
				{
					r+=1;  
					scanny.nextLine();
				}  
			 scanny.close(); 
			 System.out.println(r);
			hello2.setThreshold(r); 
			
			
			while(scanny2.hasNextLine()) 
			{ 
				String tempString=scanny2.nextLine(); 
				if(tempString.length()>=10 && tempString.length()<=17) 
				{
					Account temp=new Account(tempString); 
					hello2.add(tempString, temp); 
				} 
			} 
			scanny2.close(); 
			
			
			
			
		
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
