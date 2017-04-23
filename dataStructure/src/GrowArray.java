import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GrowArray {
	private	int used;   
	private int[] data; 
	private void grow() {
		int[] temp = data;
		data = new int[temp.length * 2 ];
		for(int i = 0; i < temp.length; i++) {
			data[i] = temp[i];
		}
	}

	public GrowArray() { data = null; used = 0; }
	public GrowArray(int initialSize) {
		data = new int[initialSize];
		used = 0;
	}

 	public void addEnd(int v) {
		if (used == data.length)
			grow();
		data[used] = v;
		used++;
	}
 	
	public void addStart(int v) {
		if(used == data.length)
			grow();
		for (int i = used - 1; i >=0; i--)
            data[i+1] = data[i];
        data[0] = v;
        used++;
	}
	
	public void removeEnd() {
		data[used-1] = 0;
		used--;
	}
	
	public void removeStart() {
		for(int i = 0; i < used; i++){
			data[i] = data[i+1];
		}
		used--;
	}
	
	public void insert(int i, int v) { //O(n^2)
		int[] temp = data; //O(1)
		for (int j = 0; j < i; j++)
			data[j] = temp[j];
		data[i] = v;
		used++;
		for (int k = i + 1; k < data.length; k++)
			data[k] = temp[k-1];
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		 for (int i = 0; i < data.length; i++)
			b.append(data[i]);
		return b.toString();
	}
	// add the other methods from BadGrowArray
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("hw4a.txt");
		String command = null,para = null;
		String[] str ;
		GrowArray a = new GrowArray(2);
		int start,step,end,num;
		Scanner s = null;
		s = new Scanner(file);
		while (s.hasNextLine()) {
			command = s.next();
			if(command.equals("OUTPUT")){
			   System.out.println(a);
			   }
				   
			if(command.equals("ADD_FRONT")){
			   para = s.next();
			   str = para.split("\\:");
			   start = Integer.parseInt(str[0]);
			   step = Integer.parseInt(str[1]);
			   end = Integer.parseInt(str[2]);
			   for(int m=start;m<=end;m+=step)
			     a.addStart(m);
			    }
					
		    if(command.equals("ADD_BACK")){
		   	   para = s.next();
			   str = para.split("\\:");
			   start = Integer.parseInt(str[0]);
			   step = Integer.parseInt(str[1]);
			   end = Integer.parseInt(str[2]);

			   for(int m=start;m<=end;m+=step)
				   a.addEnd(m);
			  }
					
		    if(command.equals("REMOVE_FRONT")){
				para = s.next();
				num = Integer.parseInt(para);
				for(int j= 0;j<num;j++)	
					a.removeStart();
				}
			   
		     if(command.equals("REMOVE_BACK")){
				para = s.next();
				num = Integer.parseInt(para);
				for(int j= 0;j<num;j++)	
				 a.removeEnd();
				}
			}
		s.close();
	}
}
