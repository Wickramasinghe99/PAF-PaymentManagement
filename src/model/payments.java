package model;

import java.sql.*;

public class payments {
	
	public String insertPayments(String ID, String Date, String Time, String Amount) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into payments values ( ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,ID);
			preparedStmt.setString(2,Date);
			preparedStmt.setString(3,Time);
			preparedStmt.setString(4,Amount);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			//output = "Inserted Successfully";
			String newItems = readPayments(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";
			
		}
		catch(Exception e) {
			
			
			//output = "Error while inserting";
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readPayments() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>Payment ID</th><th>Payment Date</th><th>Payment Time</th><th>Payment Amount</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from payments";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				
//			
//				//add a row into the html table
//				
//					+ "		<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='paymentID' type='hidden' value='"+paymentID+"'><input name='itemCode' type='hidden' value='"+paymentDate+"'><input name='paymentTime' type='hidden' value='"+paymentTime+"'><input name='paymentAmount' type='hidden' value='"+paymentAmount+"'></form></td>"
//						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='paymentID' type='hidden' value='"+paymentID+"'><input name='itemCode' type='hidden' value='"+paymentDate+"'></form></td>"						+ "		</tr>";
				
				//output += "<tr>"
				//		+ "			<td>" +paymentDate+ "</td><td>" +paymentTime+ "</td><td>" +paymentAmount+ "</td>"
				//		+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='paymentID' type='hidden' value='"+paymentID+"'><input name='paymentDate' type='hidden' value='"+paymentDate+"'><input name='paymentTime' type='hidden' value='"+paymentTime+"'><input name='PaymentAmount' type='hidden' value='"+paymentAmount+"'></form></td>"
				//		+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='paymentID' type='hidden' value='"+paymentID+"'></form></td>"
				//		+ "		</tr>";
				
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentDate = rs.getString("paymentDate");
				String paymentTime = rs.getString("paymentTime");
				String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				
				 // Add into the html table
				output += "<tr>"
						+ "	<td>" +paymentID+ "</td><td>" +paymentDate+ "</td><td>" +paymentTime+ "</td><td>" +paymentAmount+ "</td>";
				 
				 // buttons
				output += "<td><input name='btnUpdate' "
						+ "type='button' value='Update' "
						+ " class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' "
						+ "type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' "
						+ "data-id='"
				 + paymentID + "'>" + "</td></tr>";
				
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the items";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updatePayment(String ID, String Date, String Time, String Amount) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update payments set  paymentID=?, paymentDate=?, paymentTime=?, paymentAmount=? where paymentID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,ID);
			preparedStmt.setString(2,Date);
			preparedStmt.setString(3,Time);
			preparedStmt.setString(4,Amount);
			preparedStmt.setDouble(5,Double.parseDouble(ID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			//output = "Updated Successfully";
			
			String newItems = readPayments(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";
			
		}
		catch(Exception e) {
			
			//output = "Error while updating";
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deletePayment(String paymentID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from payments where paymentID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(paymentID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}
