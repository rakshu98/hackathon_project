import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import org.json.simple.JSONObject;

public class ReadFile {
	static BufferedReader br = null;
		public static void main(String[] args) {
		DecimalFormat df=new DecimalFormat("#0.00");
		double sum=0;
		double avg=0;
		double max=0;
		int i=0;
		
	Connection myConn = null;
    	Statement myStmt = null;
    	ResultSet myRs = null;
    	
	try {
		JSONObject obj = new JSONObject();
    		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project" , "project");
		 myStmt = myConn.createStatement();
    		PreparedStatement pStmt = myConn.prepareStatement("INSERT into analysis (transactionname, average, maximum) values(?,?,?)");
  	
		String line;
		br = new BufferedReader(new FileReader("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"));
		while ((line = br.readLine()) != null) {
		StringTokenizer stringTokenizer = new StringTokenizer(line," ");
		pStmt.setString(1,"Transaction1");
		while (stringTokenizer.hasMoreElements()) {
			int counter=0;
			while(counter<8) {
				stringTokenizer.nextElement().toString();
				counter++;
			}
			
		Double CPUvalue = Double.parseDouble(stringTokenizer.nextElement().toString());
		if(max<CPUvalue)
			max=CPUvalue;
		sum+=CPUvalue;				
		while(counter<11) {
			stringTokenizer.nextElement().toString();
			counter++;
			}
			i++;
			String val=i+"s";
			obj.put(val, CPUvalue);			
		}
	}
		avg=(double)sum/(double)i;
		System.out.println("Transaction 1");
		System.out.println(obj);
		System.out.println("Avg: "+df.format(avg));
		System.out.println("Max: "+max);
		pStmt.setString(2,df.format(avg));
		pStmt.setDouble(3,max);
		 pStmt.execute();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
