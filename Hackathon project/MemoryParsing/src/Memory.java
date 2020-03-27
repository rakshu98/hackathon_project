
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import org.json.simple.JSONObject;

public class Memory {
	static float avg,max=0;
	static JSONObject obj = new JSONObject();
	static JSONObject obj1 = new JSONObject();
	
	public void DatabaseConnectivity()
	{
		try {
		Connection myConn = null;
	    	Statement myStmt = null;
	    	ResultSet myRs = null;
		
	    	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/memory", "memory" , "memory");
		myStmt = myConn.createStatement();
		PreparedStatement pStmt = myConn.prepareStatement("INSERT into cpumemory (transactionname, average, maximum) values(?,?,?)");
	
		pStmt.setString(1,"Sample 1");
		pStmt.setFloat(2,avg);
		pStmt.setFloat(3,max);
		pStmt.execute();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void FileWriter() throws IOException
	{
		FileWriter file1=new FileWriter("MemoryOutput.json");
		file1.write(obj1.toJSONString());
		file1.flush();		
	}
	
	
	public static void main(String[] args) throws IOException {
		
			Memory mem=new Memory();
			
			File file = new File("C:\\Users\\hi\\Desktop\\hackathon_project-master\\Memory.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			DecimalFormat df=new DecimalFormat("#0.00");
			float value;
			String st="";
			int count=0,sum=0;
					
			while ((st = br.readLine()) != null)
			{
				st=st.trim();
				String[] array = st.split(" ");
				if (array[0].equals("TOTAL")) 
				{
					value=Float.parseFloat(array[3].trim())/1024;
					if(max < value)
					{
						max=value;					
					}
					String itr=count+"s";
					obj.put(itr,value);
					sum+=value;
					count=count+1;				
				}
			}
			avg=(float)sum/(float)(count-1);
			String maximum = df.format(max)+" MB";
			String average = df.format(avg)+" MB";
				
			obj1.put("Memoryvalues",obj);
			obj1.put("Usecase","sample");
			obj1.put("MaxMem",maximum);
			obj1.put("AverageMem",average);				
			System.out.println(obj1);
			mem.DatabaseConnectivity();
			mem.FileWriter();
									
		}
	
}
