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

public class CPUJson {
	public static void main(String[] args) {
		DecimalFormat df=new DecimalFormat("#0.00");
		double sum=0;
		double avg=0;
		double max=0;
		String val="";
		String line;
		int i=0;
		static BufferedReader br = null;	
		Connection myConn = null;
    		Statement myStmt = null;
    		ResultSet myRs = null;
    	
		try {
			JSONObject obj = new JSONObject();
    			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project" , "project");
			 myStmt = myConn.createStatement();
    			PreparedStatement pStmt = myConn.prepareStatement("INSERT into analysis (transactionname, average, maximum) values(?,?,?)");

	    		StringBuilder sb = new StringBuilder();
			
			br = new BufferedReader(new FileReader("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"));
			while ((line = br.readLine()) != null) {
				final StringTokenizer tok = new StringTokenizer(line, " ", false);
					for(int j=0;j<=11;j++)
					{
						if(j==8)
						{
							Double CPUvalue = Double.parseDouble(tok.nextElement().toString());
							if(max<CPUvalue)
								max=CPUvalue;
							sum+=CPUvalue;
							i++;
							val="\""+i+"s\": ";
							sb.append(val).append(CPUvalue+",").append("\n");
						}
						else
						{
							tok.nextToken();
						}
					}
					
				pStmt.setString(1,"Transaction1");				
				
			}
			
			avg=(double)sum/(double)i;

			obj.put("Transaction 1",sb);
			obj.put("CpuAvg:",df.format(avg));
			obj.put("CpuMax:",max);
			System.out.println(obj);
			
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
