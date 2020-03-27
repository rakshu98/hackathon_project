import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.*;
import org.json.JSONException;
public class Code {
	public static void main(String[] args) throws Exception
	{
		BufferedReader reader=null;
		String val="";
		float kb_val,mb_val=0,Total=0,c=0,max=0,avg=0;
		File mem_file = new File("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"); 
		reader= new BufferedReader(new FileReader(mem_file));	
		int lineNum = 0;
		String line = null;
		while ( (line = reader.readLine() ) != null )
		 {
			line=line.trim();
		    lineNum++;
		    if ( lineNum % 2 == 0 )
			  continue;
		    String[] array = line.split("   ");
		   val=array[1].trim();
		   kb_val=Float.parseFloat(val);
		   mb_val=(kb_val/1000);
		   if(max<mb_val)
		   {
			   max=mb_val;
		   }
		   
		  // System.out.println(mb_val);
		  Total=Total+mb_val;
		  c++;
		 }
		 avg=(Total/c);
		 
		 System.out.println("Maximum Value:"+max);
		 System.out.println("Total:"+Total);
		 System.out.println("no of entry:"+c);
		 System.out.println("avg:"+avg);
		 /*HashMap<String, Float> mem = new HashMap<String, Float>();

		    mem.put("AverageMemory(MB)", avg);
		    mem.put("MaximumMemory(MB", max);
		    */
	
		 JSONObject obj=new JSONObject();
		 obj.put("AverageMemory(MB)", avg);
		 obj.put("MaximumMemory(MB)", max);
		
		 FileWriter file1=new FileWriter("C:\\Users\\842359\\Desktop\\hackathon\\me.json");
			file1.write(obj.toJSONString());
			file1.flush();
			System.out.println(obj);
		 
	}
}
