package HashMap;

public class HashMap {
	private int table[];
	
	public HashMap(int init) {
		
		table = new int [init];
	}
	
	public HashMap() {
		this(16);
	}
	
	public int hash (int v)  {
		return v % table.length;
	}
	
	public void add(int v) {
		int i = hash(v);
		while(table[i] != 0) 
			i = (i + 1) % table.length;
		{
			table[i] = v;
			return;
		}
		
		
		
		
	
	}
	
	
	
	
	
	

}
