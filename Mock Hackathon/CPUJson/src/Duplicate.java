
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import org.json.simple.JSONObject;

public class Duplicate {
	static BufferedReader br = null;
	double avg=0;
	static double max=0;
	String transactionname;
	
	
/*	public void Database()
	{
		System.out.println("Inside Database call");
		Connection myConn = null;
    	Statement myStmt = null;
    	ResultSet myRs = null;

		try {
			PrintWriter printWriter = new PrintWriter("htmloutput.html");
			System.out.println("Printwriter created");
			printWriter.println("<table border=1>");
			printWriter.println("<caption>MAX AND AVERAGE VALUES OF TRANSACTION CPU DATA</caption>");
			printWriter.println("<tr><th>TRANSACTION NAME</th><th>AVERAGE CPU TIME</th><th>MAXIMUM CPU TIME</th></tr>");
			
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project" , "project");
			myStmt = myConn.createStatement();
    		PreparedStatement pStmt = myConn.prepareStatement("INSERT into analysis (transactionname, average, maximum) values(?,?,?)");
    		System.out.println(avg+"    "+max);
    		pStmt.setString(1,"Transaction1");
    		pStmt.setDouble(2,avg);
			pStmt.setDouble(3,max);
			pStmt.execute();	
			/*
			System.out.println("DB inserted");
			String sql1 = "SELECT * FROM analysis";
			ResultSet rs = myStmt.executeQuery(sql1);
			System.out.println("resultset started");
		    while (rs.next()) 
		    {
		    	String transactionname = rs.getString(1);
				double average = rs.getDouble(2);
				double max  = rs.getDouble(3);
				printWriter.println("<tr>" + "<td>" + transactionname + "</td>" + "<td>" + average + "</td>"+ "<td>" + max + "</td>" + "</tr>" + "\n");
			}
		    
		    printWriter.println("</table>");
			System.out.println("Printwriter close");
		   */
/*		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	*/
		public static void main(String[] args) {
			System.out.println("Main started");
		DecimalFormat df=new DecimalFormat("#0.00");
		double sum=0;
		String val="";
		String line;
		int i=0;
		Duplicate dup=new Duplicate();
				    
		try {
			JSONObject obj = new JSONObject();
			JSONObject obj1 = new JSONObject();
    	 	
    		StringBuilder sb = new StringBuilder();
    		
			br = new BufferedReader(new FileReader("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"));
			System.out.println("CSV Parsing");
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
//							sb.append(val).append(CPUvalue+",").append("\n");
							obj.put(val,CPUvalue);
						}
						else
						{
							tok.nextToken();
						}
					}
			}
			System.out.println("CSV calculation end");
			
			dup.avg=(double)sum/(double)i;
			System.out.println("dup.avg:"+dup.avg);
			obj1.put("Transaction 1",sb);
			obj1.put("CpuAvg:",dup.avg);
			obj1.put("CpuMax:",max);
			obj1.put("values:",obj);
//			System.out.println(obj);
//			System.out.println(obj1);
			System.out.println("JSONObjects Inserted");
			
			
			
			
			Connection myConn = null;
	    	Statement myStmt = null;
	    	ResultSet myRs = null;

			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project" , "project");
				myStmt = myConn.createStatement();
	    		PreparedStatement pStmt = myConn.prepareStatement("INSERT into analysis (transactionname, average, maximum) values(?,?,?)");
	    		System.out.println(dup.avg+"    "+max);
	    		pStmt.setString(1,"Transaction1");
	    		pStmt.setDouble(2,dup.avg);
				pStmt.setDouble(3,max);
				pStmt.execute();	
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		 
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
				try {
					br.close();
					} catch (IOException e) {
						e.printStackTrace();
						}
		}
		
	}
	

}

