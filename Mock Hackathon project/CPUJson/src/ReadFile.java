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

public class ReadFile {
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
			ReadFile rd=new ReadFile();
		DecimalFormat df=new DecimalFormat("#0.00");
		String val="";
		int i=0;
		double sum=0;
		double avg=0;
			JSONObject obj = new JSONObject();
			JSONObject obj1 = new JSONObject();
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\hi\\Desktop\\New folder\\hackathon_project-master\\sample-input.txt"));
			try {
				while ((line = br.readLine()) != null) {
					StringTokenizer stringTokenizer = new StringTokenizer(line," ");

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
						val=i+"s";
						obj.put(val,CPUvalue);
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
//			System.out.println(obj1);
			 

			 rd.Database();
			
		}  
		
	
	}
	


