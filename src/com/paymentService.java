package com;

import model.payments;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class paymentService {
	
	payments paymentObj = new payments();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		
		  return paymentObj.readPayments();
		 //return "Hello";
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertPayments(@FormParam("paymentID") String paymentID,
							@FormParam("paymentDate") String paymentDate,
							@FormParam("paymentTime") String paymentTime,
							@FormParam("paymentAmount") String paymentAmount)
	{
		
		String output = paymentObj.insertPayments(paymentID, paymentDate, paymentTime, paymentAmount);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
		//Convert input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		
		//Read values from JSON object
		String paymentID = paymentObject.get("paymentID").getAsString();
		String paymentDate = paymentObject.get("paymentDate").getAsString();
		String paymentTime = paymentObject.get("paymentTime").getAsString();
		String paymentAmount = paymentObject.get("paymentAmount").getAsString();
		
		String output = paymentObj.updatePayment(paymentID, paymentDate, paymentTime, paymentAmount);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String paymentID = doc.select("paymentID").text();
		
		String output = paymentObj.deletePayment(paymentID);
		return output;
		
	}

}
