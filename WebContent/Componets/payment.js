$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});



 // SAVE ============================================
   
$(document).on("click", "#btnSave", function(event)
	{
		
		// Clear alerts---------------------
		
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		 
		// Form validation-------------------
	
		var status = validateItemForm();
		if (status != true)
	  {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	   }
	
		
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax( 
			{ 
				 url : "paymentAPI", 
				 type : type, 
				 data : $("#formItem").serialize(), 
				 dataType : "text", 
				 complete : function(response, status) 
			 { 
				 onItemSaveComplete(response.responseText, status); 
			 } 
		});
		
	});
	
	
	
	//itemssavecomplefunction
	

	function onItemSaveComplete(response, status) 
	{ 
	  if (status == "success") 
	   { 
				 var resultSet = JSON.parse(response); 
				 
				 if (resultSet.status.trim() == "success") 
				 
				 { 
					 $("#alertSuccess").text("Successfully saved."); 
					 $("#alertSuccess").show(); 
					 $("#divItemsGrid").html(resultSet.data); 
					 
				 } else if (resultSet.status.trim() == "error") 
				 
				 { 
					 $("#alertError").text(resultSet.data); 
					 $("#alertError").show();
					  
				 } 
		 
		 } else if (status == "error") 
		 
		 { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
			 
		 } else
		 
		 { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
			 
		 } 
		
	
		 $("#hidItemIDSave").val(""); 
		 $("#formItem")[0].reset(); 
	}
	

	
	
// update
	
	$(document).on("click", ".btnUpdate", function(event) 
	{ 
		 $("#hidItemIDSave").val($(this).closest("tr").find('td:eq(0)').text());
		 
		 $("#paymentid").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#paymentdate").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#paymenttime").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#amount").val($(this).closest("tr").find('td:eq(3)').text()); 
		 
	});
	
	
	
//delete
	
$(document).on("click", ".btnRemove", function(event) 
	{ 
		 $.ajax( 
			 { 
					 url : "paymentAPI", 
					 type : "DELETE", 
					 data : "id=" + $(this).data("id"),
					 dataType : "text", 
					 complete : function(response, status) 
				 { 
			     onItemDeleteComplete(response.responseText, status); 
			     } 
		 }); 
	});
	


//deletecompletion

function onItemDeleteComplete(response, status) 
{ 
	  if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 
			 if (resultSet.status.trim() == "success") 
				 { 
					 $("#alertSuccess").text("Successfully deleted."); 
					 $("#alertSuccess").show(); 
					 
					 $("#divItemsGrid").html(resultSet.data); 
			 } else if (resultSet.status.trim() == "error") 
				 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
			 
	} else if (status == "error") 
			 
	{ 
	     $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	} else
			 
	 { 
	     $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
		
		
	}	
		
	// CLIENT-MODEL================================================================
	
	function validateItemForm()
	{
	
		// CODE
		
		if ($("#paymentid").val().trim() == "")
		 {
			 return "Insert id.";
		 }
		// NAME
		
		if ($("#paymentdate").val().trim() == "")
		 {
		 	return "Insert date.";
		 }
		 
		 if ($("#paymenttime").val().trim() == "")
		 {
			 return "Insert time.";
		 }
		 
		
		if ($("#amount").val().trim() == "")
		 {
		 	return "Insert amount.";
		 }
		 
	
	 
	return true;
	


}	

