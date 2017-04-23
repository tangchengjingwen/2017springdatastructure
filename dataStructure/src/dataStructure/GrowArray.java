package dataStructure;

public class GrowArray {
	private	int used;     // the number of elements in use
	private int[] data; //capacity is data.length.
	
	private void grow() {
		int[] temp = data;
		data = new int[2 * data.length];
		for(int i = 0; i < used; i ++)
			data[i] = temp[i];
	}

	public GrowArray( ) { data = null; used = 0; }
	
	public GrowArray(int initialSize) {
		data = new int[initialSize];
		used = 0;
	}

 	public void addEnd(int v) {
 		if(data == null) {
 			data = new int[1];
 			data[used] = v;
 			used ++ ;
 			grow();
 			return;
 		}
 		else if (used >= data.length){
			grow();
		}
 		data[used] = v;
 		used++;

	}
 	
	public void addStart(int v) {
		if(data == null) {
			data = new int[1];
			data[0] = v;
			used++;
			grow();
			return;
		}
		int[] temp = data;
	    if(used < data.length){
			data[0] = v;
			for(int i = 0; i < used; i++ ) {
				data[i + 1] = temp[i];
			}
			used++;
			return;
		}
		
		else {
			grow();
			data[0] = v;
			for(int i = 0; i < used; i++ ) {
				data[i + 1] = temp[i];
			}
			used++;
			return;
		}
		
			
	}
	
	public void removeEnd() {
		if(data.length == 0)
			return;
		data = new int[used--];
	}
	
	public void removeStart() {
		if(data.length == 0)
			return;
		int[] temp = data;
		data = new int[data.length - 1];
		for(int i = 0; i < used; i++)
			data[i] = temp[i + 1];
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		for (int i = 0; i < data.length; i++) {
			b.append(data[i]).append(",");
		}
		b.append("]");
		return b.toString();
		
	}
	// add the other methods from BadGrowArray
	public static void main(String[] args) throws NullPointerException{
		GrowArray a = new GrowArray();
		for (int i = 0; i < 10; i++)
			a.addEnd(i);
		System.out.println(a);
		for (int i = 0; i < 10; i++)
			a.addStart(i);
		System.out.println(a);
		
	}
}