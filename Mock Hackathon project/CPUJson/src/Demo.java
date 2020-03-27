import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public class Demo {
	static BufferedReader br = null;
	static String average;
	static double max=0;
	
	public void Database() {
		Connection myConn = null;
    	Statement myStmt = null;
    	ResultSet myRs = null;
    	try {
    		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project" , "project");
			 myStmt = myConn.createStatement();
    		PreparedStatement pStmt = myConn.prepareStatement("INSERT into analysis (transactionname, average, maximum) values(?,?,?)");
			pStmt.setString(1,"Transaction1");	
    		pStmt.setString(2,average);
			pStmt.setDouble(3,max);
			pStmt.execute();
    	}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Demo rd=new Demo();
		DecimalFormat df=new DecimalFormat("#0.00");
		double sum=0;
		double avg=0;
		double max=0;
		String val="";
		String line;
		int i=0;
		 BufferedReader br = null;	
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
    	br = new BufferedReader(new FileReader("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"));
			try {
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
								val=i+"s";
								obj.put(val,CPUvalue);
							}
							else
							{
								tok.nextToken();
							}
						}
						
					
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			avg=(double)sum/(double)i;
			average=df.format(avg);
			obj1.put("Usecase","Transaction 1");
			obj1.put("CpuAvg:",average);
			obj1.put("CpuMax:",max);
			obj1.put("CPUvalues",obj);
			System.out.println(obj1);
			 rd.Database();
				
	}  
	

}