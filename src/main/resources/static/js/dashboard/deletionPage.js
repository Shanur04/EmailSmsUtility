/*
 * This file contains JAVSCRIPT used in deletion homepage

 */
// --------------JAVASCRIPT written below is related to CITYALLOCATION DELETION--------
// Event handles the city dropdown list
 $(document).ready(function(){
 $("#loading").hide();
 });
 
$(document).on('change', '#cityId', function() {
	$("#cityBtn").removeAttr('disabled');
});


$(".nav-tabs li.nav-item a.nav-link").click(function() {
	  $(".nav-tabs li.nav-item a.nav-link").removeClass('active');
	});

$(function() {
	$("#cityBtn").click(
			function() {
				if ($("#cityId").val() == 0 || $("#cityId").val() == null) {
					alert("Please choose appropriate option from the list !");
					return;

				}
				var choice = $("#cityId").val();
				var cityName = $('#cityId option:selected').text();
				if (!(choice == null || choice == 0)) {
					if (confirm("Press OK to delete Allocation for "
							+ $('#cityId option:selected').text() + "?")) {
						//var token = $("meta[name='_csrf']").attr("content");
						$("#loading").show();
						$.ajax({
							type : 'POST',
							url : 'deleteCityAllocation/?city=' + choice,
							datatype : "text",
							/*headers : {
								'X-CSRF-TOKEN' : token
							},*/

							success : function(response) {
								$("#loading").hide();
								alert(response + " for " + cityName);
								$("#cityId").val("");
								location.reload();
							}

							,
							error : function(response) {
								$("#loading").hide();
								alert(response);
								$("#cityId").val("");
								location.reload();
							}

						});

					}

				}
			});
});
// Event handling city dropdown ends here



// Event for Click of city deletion link
$(document).on(
		'click',
		'#cityDeletionTab',
		function(e) {
			console.log('in')
			/**
			 * Javascript to open selected tab and hide the unselected ones
			 */
			/*$("#indiCandidateTab").attr("hidden", "true");
			$("#infoDiv").attr("hidden", "true");
			$("#centreSlotDeletionTab").attr("hidden", "true");
			$("#groupDeletionTab").attr("hidden", "true");
			$("#slotDeletionTab").attr("hidden", "true");
			$("#candidateDeletionTab").attr("hidden", "true");
			$("#cityDeletionTab").removeAttr("hidden");*/
			$("#infoDiv").attr("hidden", "true");

			/**
			 * This ajax call will fetch the name of cities along with thier ids
			 * and populate dropdown list
			 */
			$("#loading").show();
			//var token = $("meta[name='_csrf']").attr("content");
		//	var header = $("meta[name='_csrf_header']").attr("content");
			$.ajax({
				type : 'GET',
				url : 'getAllCities',
				async: false,
				processData : false,
				contentType : "application/json; charset=utf-8",
				dataType : "JSON",
				success : function(response) {
					$("#loading").hide();
					var ddn = "<option value=0 >--</option>";
					for (var i = 0; i < response.length; i++) {
						ddn += "<option value=" + response[i].examCityId + " >"
								+ response[i].examCityName + "</option>";
					}
					$("#cityId").html(ddn);

				},
				error : function(response) {
					$("#loading").hide();
					alert("Please try again!");
				}

			});

		});
// ----------CITY ALLOCATION DELETION ENDS HERE-------------
//--------------Slot Deletion Starts here---------
$(document).on(
		'click',
		'#slotDeletionTab',
		function(e) {
			
			$("#infoDiv").attr("hidden", "true");
			
			// This ajax calls will be acknowledged by MiscellaneousController
			$("#loading").show();
			//var token = $("meta[name='_csrf']").attr("content");
			$.ajax({
				type : 'GET',
				url : 'getExamSlots',
				/*headers : {
					'X-CSRF-TOKEN' : token
				},*/
				// contentType : "application/json; charset=utf-8",
				// dataType : 'json',
				success : function(data, xhr, text) {
					$("#loading").hide();
					console.log(text);
					if (xhr.status == 204) {
						alert("Slots have not been decided yet");
						return;
					}
					console.log(data);
					var ddn = "";
					for (var i = 0; i < data.length; i++) {
						ddn += "<option value=" + data[i].examSlotId + " >"
								+ data[i].examSlotName + "</option>";

					}
					// centreSlotArray = data;
					// console.log(centreSlotArray);
					$("#slotId").html(ddn);
					 $('#slotId').multiselect({
				            includeSelectAllOption: true,
				            buttonWidth: '400px',
				            enableFiltering: true,
				            filterBehavior: 'text',
				            enableCaseInsensitiveFiltering: true,
				            filterPlaceholder: 'Search ...',

				        });
					//alert(data);
				},
				error : function(response) {
					$("#loading").hide();
					alert("Please try again!");
					console.log(response);
				}

			});

		});

$(function() {

	$("#slotId").change(function() {
		$("#slotBtn").removeAttr('disabled');

	});
});
$(function() {

	$("#slotBtn").click(
			function() {
				if ($("#slotId").val() == 0 || $("#slotId").val() == null) {
					alert("Please Choose appropriate option from list");
					return;
				}
				if (confirm("Press OK to delete allocation for"
						+ $('#slotId option:selected').text())) {
					var selected = $("#slotId option:selected");
					var message = [];
			        selected.each(function () {
			            message.push({"slotId":$(this).val()});
			        });
					console.log(message);
					
					$("#loading").show();
					//var token = $("meta[name='_csrf']").attr("content");
					$.ajax({
						type : 'POST',
						url : 'deleteSlot',
						/*headers : {
							'X-CSRF-TOKEN' : token
						},*/
						contentType : "application/json; charset=utf-8",
						dataType : "text",
						data : JSON.stringify(message),
						success : function(data, xhr, text) {
							$("#loading").hide();
							alert(data + " for "
									+ $('#slotId option:selected').text());
							
							$("#slotId").multiselect("clearSelection");

							 $("#slotId").multiselect( 'refresh' );

						},
						error : function(error) {
							$("#loading").hide();
							
						}
					});

				} else
					document.reload();
				return;

			});
});

/**
 * Javascript written below is related to centreSlot Deletion tab
 * (centreSlotArray is used to make selection of centre and respective slots
 * responsive)
 */
var centreSlotArray = [];
$(document).on(
		'click',
		'#centreSlotDeletionTab',
		function(e) {
			
			$("#infoDiv").attr("hidden", "true");
			
			// AJAX CALL to populate dropdown lists for this division
			$("#loading").show();
			//var token = $("meta[name='_csrf']").attr("content");
			$.ajax({
				type : 'GET',
				url : 'getCentreSlotDetails',
				/*headers : {
					'X-CSRF-TOKEN' : token
				},*/
				contentType : "application/json; charset=utf-8",
				dataType : "JSON",
				success : function(data, xhr, text) {
					$("#loading").hide();
					if (xhr.status == 204) {
						alert("Allocation has not been done yet! ");
						return;
					}

					var ddn = "<option value=0 >--</option>";
					for (var i = 0; i < data.length; i++) {
						ddn += "<option value=" + data[i].centreId + " >"+data[i].centerCode+" / "
								+ data[i].centreName + "</option>";

					}
					centreSlotArray = data;
					console.log(centreSlotArray);
					$("#centreId").html(ddn);
					
					

				},
				error : function(response) {
					$("#loading").hide();
					alert("Please Try again!");
				}

			});

		});
// This part will deal to show only available slots at that particular
// centre
function getValueByCentreId(data) {
	var i, len = centreSlotArray.length;
	console.log(data);
	for (i = 0; i < len; i++) {
		if (parseInt(centreSlotArray[i].centreId) == (parseInt(data))) {
			return centreSlotArray[i];
		}
	}

	return -1;
}

$(function() {

	$("#centreId").change(
			function() {
				
				$("#centreSlotBtn").removeAttr('disabled');
				var ddnSlot = "";
				var obj = getValueByCentreId($("#centreId").val());
				if ($("#centreId").val() !== '0') {
					for ( var key in obj.slotDetails) {

						ddnSlot += "<option value=" + key + " >"
								+ obj.slotDetails[key] + "</option>";

					}
					$("#centreSlotId").html(ddnSlot);
					
					$('#centreSlotId').multiselect("destroy");
					 $('#centreSlotId').multiselect({
				            includeSelectAllOption: true,
				            enable:true,
				            buttonWidth: '400px',
				            enableFiltering: true,
				            filterBehavior: 'text',
				            enableCaseInsensitiveFiltering: true,
				            filterPlaceholder: 'Search ...',

				        });
					 $('#centreSlotId').multiselect('clearSelection');
					 $('#centreSlotId').multiselect("enable");
				}
				else
					{
					ddnSlot="";
					document.getElementById('centreSlotId').innerHTML=ddnSlot;
					 $('#centreSlotId').multiselect("disable");
					}
				
				
			});
});
$(function() {

	$("#centreSlotBtn")
			.click(
					function() {
						var slotId = $("#centreSlotId").val();
						var centreId = $("#centreId").val();
						if ($("#centreId").val() == 0
								|| $("#centreId").val() == null) {
							alert("Please choose appropriate option from the list!");
							return;
						}
						if (slotId == null || slotId == 0) {
							alert("Please choose appropriate slots");

						} else {
							var myData = $("#centreSlotId option:selected");
							var message2 = [];
							myData.each(function () {
								console.log($(this).val());
					            message2.push({"centreId" : parseInt(centreId),"slotId":parseInt($(this).val())});
					        });
							console.log(message2);
							/*var myData = {
								"centreId" : parseInt(centreId),
								"slotId" : parseInt(slotId)
							};*/
							if (confirm("Click OK to delete CentreSlot Allocation for "
									+ $('#centreId option:selected').text()
									+ " "
									+ $('#centreSlotId option:selected').text())) {
								sendAjaxToDeleteCentreSlot(message2);
							}
						}
					});

});
function sendAjaxToDeleteCentreSlot(body) {
	console.log(JSON.stringify(body));
	$("#loading").show();
	//var token = $("meta[name='_csrf']").attr("content");
	$.ajax({
		type : 'POST',
		url : 'deleteCentreSlot',
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		data : JSON.stringify(body),
		success : function(data, xhr, text) {
			$("#loading").hide();
			alert(data);
			location.reload();
		},
		error : function(data, xhr, text) {
			$("#loading").hide();
			console.log("failed");
			alert(data);
			location.reload();
		}
	});

	console.log(body);
}

// -----------------CENTRE SLOT DELETION ENDS HERE---------------------
//------------------GROUP DELETION STARTS HERE-----------------
/*$(document).on(
		'click',
		'#groupDeletionTab',
		function(e) {
			
			$("#infoDiv").attr("hidden", "true");
			
			$("#loader").html("").show();
			var token = $("meta[name='_csrf']").attr("content");
			$.ajax({
				type : 'GET',
				url : 'getAFCATGroups',
				headers : {
					'X-CSRF-TOKEN' : token
				},
				// contentType : "application/json; charset=utf-8",
				// dataType : 'json',
				success : function(data, xhr, text) {
					$("#loader").html("").hide();
					console.log(text);
					if (xhr.status == 204) {
						alert("Groups have not been decided yet");
						return;
					}
					console.log(data);
					var ddn = "<option value=0 >--</option>";
					for (var i = 0; i < data.length; i++) {
						ddn += "<option value=" + data[i].afcatGroupId + " >"
								+ data[i].name + "</option>";

					}
					$("#groupId").html(ddn);

				},
				error : function(response) {
					$("#loader").html("").hide();
					alert("Please try again!");
					console.log(response);
				}

			});

		});
$(function() {

	$("#groupId").change(function() {
		$("#groupBtn").removeAttr('disabled');

	});
});

$(function() {

	$("#groupBtn")
			.click(
					function() {
						if ($("#groupId").val() == 0
								|| $("#groupId").val() == null) {
							alert("Please choose appropriate option from the list!");
							return;

						}
						if (confirm("Press OK to delete allocation for"
								+ $('#groupId option:selected').text())) {
							var groupId = {
								"afcatGroupId" : $("#groupId").val()
							}
							$("#loader").html("").show();
							var token = $("meta[name='_csrf']").attr("content");
							$
									.ajax({
										type : 'POST',
										url : 'deleteGroup',
										headers : {
											'X-CSRF-TOKEN' : token
										},
										contentType : "application/json; charset=utf-8",
										dataType : "text",
										data : JSON.stringify(groupId),
										success : function(data, xhr, text) {
											$("#loader").html("").hide();
											if (xhr.status == 204) {
												alert(data
														+ " for "
														+ $(
																'#groupId option:selected')
																.text());
												return;
											} else {
												alert(data
														+ " for "
														+ $(
																'#groupId option:selected')
																.text());

												location.reload();
												return;
											}

										},
										error : function(error) {
											$("#loader").html("").hide();
											alert("Failed,Please Try again!");
											return;
										}
									});

						} else
							document.reload();
						return;

					});
});*/

//-------CANDIDATE DELETION STARTS HERE-----------
$(document).on('click', '#candidateDeletionTab', function(e) {
	/**
	 * Javascript to open selected tab and hide the unselected ones
	 */
	
	$("#infoDiv").attr("hidden", "true");
	

});
// GLOBAL VARIABLES FOR IDENTIFYING THE TYPE OF FILE UPLOADED
var dta;
var status;
$(document).ready(function() {
	// Sheet of appcredIDs getting validated
	$("#candidateSheetForm").validate({

		rules : {
			candidateSheet : {
				required : true,
				extension : "xlsx|csv"
			}
		},
		messages : {
			candidateSheet : {
				required : "Please Choose File to upload!",
				extension : "File Should be of 'XLSX' or 'CSV' format!"
			}
		}

	});

	$("#candidateEmailSheetForm").validate({

		rules : {
			candidateEmailSheet : {
				required : true,
				extension : "xlsx|csv"
			}
		},
		messages : {
			candidateEmailSheet : {
				required : "Please Choose File to upload!",
				extension : "File Should be of 'XLSX' or 'CSV' format!"
			}
		}

	});

	$("#candidateRegSheetForm").validate({

		rules : {
			candidateRegSheet : {
				required : true,
				extension : "xlsx|csv"
			}
		},
		messages : {
			candidateRegSheet : {
				required : "Please Choose File to upload!",
				extension : "File Should be of 'XLSX' or 'CSV' format!"
			}
		}

	});

});
// Function containing AJAX call for deleteion candidates Ids wise
function deleteAlloc() {
	$(document).off('click').on('click', '#sheetUploadBtn', function(e) {
		switch (status) {
		case 'a': {
			if ($("#candidateSheetForm").valid()) {
				sendRequest();
				$('#candidateSheetForm')[0].reset();
			} else
				return;
		}
			break;
		case 'r': {
			if ($("#candidateRegSheetForm").valid()) {
				sendRequest();
				$('#candidateRegSheetForm')[0].reset();
			} else
				return;

		}
			break;
		case 'e': {
			if ($("#candidateEmailSheetForm").valid()) {
				sendRequest();
				$('#candidateEmailSheetForm')[0].reset();
			} else
				return;

		}
			break;
		default: {
			alert("Please try Again!");
		}
			break;
		}

		{

		}

	});
}
// AJAX Call to Backend
function sendRequest() {
	$("#loading").show();
	$("#sheetUploadBtn").attr("disabled", "disabled");
	//var token = $("meta[name='_csrf']").attr("content");
	$.ajax({
		type : 'POST',
		enctype : 'multipart/form-data',
		url : 'deleteCandidatesOnData',
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/
		data : dta,
		dataType : 'text',
		contentType : false,
		processData : false,
		cache : false,
		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#sheetUploadBtn").removeAttr("disabled");
			$("#sheetUploadBtnblock").removeAttr("disabled");
			$("#sheetUploadBtnunblock").removeAttr("disabled");
			alert(data);
			//$("#candidatesDeletionTab").removeClass("active");
			
			
			
			dta = null;
			status = null;
			location.reload(); 
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#sheetUploadBtn").removeAttr("disabled");
			console.log(xhr.responseJSON);
			alert(xhr.responseText);
			$("#candidatesDeletionLink").removeClass("active show");
			$("#candidatesDeletionLink").addClass("active show");
			location.reload(); 
			
		}
	});

}

///-----------------------------
//Function containing AJAX call for deleteion candidates Ids wise
function blockAlloc() {
	$(document).off('click').on('click', '#sheetUploadBtnblock', function(e) {
		switch (status) {
		case 'a': {
			if ($("#candidateSheetForm").valid()) {
				sendRequestblock();
				$('#candidateSheetForm')[0].reset();
			} else
				return;
		}
			break;
		case 'r': {
			if ($("#candidateRegSheetForm").valid()) {
				sendRequestblock();
				$('#candidateRegSheetForm')[0].reset();
			} else
				return;

		}
			break;
		case 'e': {
			if ($("#candidateEmailSheetForm").valid()) {
				sendRequestblock();
				$('#candidateEmailSheetForm')[0].reset();
			} else
				return;

		}
			break;
		default: {
			alert("Please try Again!");
		}
			break;
		}

		{

		}

	});
}

//AJAX Call to Backend
function sendRequestblock() {
	$("#loading").show();
	$("#sheetUploadBtnblock").attr("disabled", "disabled");
	/*var token = $("meta[name='_csrf']").attr("content");*/
	$.ajax({
		type : 'POST',
		enctype : 'multipart/form-data',
		url : 'blockCandidatesOnData',
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/
		data : dta,
		dataType : 'text',
		contentType : false,
		processData : false,
		cache : false,
		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#sheetUploadBtn").removeAttr("disabled");
			$("#sheetUploadBtnblock").removeAttr("disabled");
			$("#sheetUploadBtnunblock").removeAttr("disabled");
			 
			alert(data);
			//$("#candidatesDeletionTab").removeClass("active");
			
			
			
			dta = null;
			status = null;
			location.reload(); 
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#sheetUploadBtn").removeAttr("disabled");
			console.log(xhr.responseJSON);
			alert(xhr.responseText);
			$("#candidatesDeletionLink").removeClass("active show");
			$("#candidatesDeletionLink").addClass("active show");
			location.reload(); 
			
		}
	});

}
///------------------------------


//============================
//Function containing AJAX call for deleteion candidates Ids wise
function unblockAlloc() {
	$(document).off('click').on('click', '#sheetUploadBtnunblock', function(e) {
		switch (status) {
		case 'a': {
			if ($("#candidateSheetForm").valid()) {
				sendRequestunblock();
				$('#candidateSheetForm')[0].reset();
			} else
				return;
		}
			break;
		case 'r': {
			if ($("#candidateRegSheetForm").valid()) {
				sendRequestunblock();
				$('#candidateRegSheetForm')[0].reset();
			} else
				return;

		}
			break;
		case 'e': {
			if ($("#candidateEmailSheetForm").valid()) {
				sendRequestunblock();
				$('#candidateEmailSheetForm')[0].reset();
			} else
				return;

		}
			break;
		default: {
			alert("Please try Again!");
		}
			break;
		}

		{

		}

	});
}

//AJAX Call to Backend
function sendRequestunblock() {
	$("#loading").show();
	$("#sheetUploadBtnblock").attr("disabled", "disabled");
	//var token = $("meta[name='_csrf']").attr("content");
	$.ajax({
		type : 'POST',
		enctype : 'multipart/form-data',
		url : 'unblockCandidatesOnData',
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/
		data : dta,
		dataType : 'text',
		contentType : false,
		processData : false,
		cache : false,
		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#sheetUploadBtn").removeAttr("disabled");
			$("#sheetUploadBtnblock").removeAttr("disabled");
			$("#sheetUploadBtnunblock").removeAttr("disabled");
			alert(data);
			//$("#candidatesDeletionTab").removeClass("active");
			
			
			
			dta = null;
			status = null;
			location.reload(); 
			
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#sheetUploadBtn").removeAttr("disabled");
			console.log(xhr.responseJSON);
			alert(xhr.responseText);
			$("#candidatesDeletionLink").removeClass("active show");
			$("#candidatesDeletionLink").addClass("active show");
			location.reload(); 
			
		}
	});

}


//==============================
// Setting status as AppCredId Sheet to be uploaded (status=a)
$(document).on('change', "#candidateSheet", function() {
	dta = null;
	status = null;

	$("#candidateRegSheet").val("");
	$("#candidateEmailSheet").val("");
	status = "a";
	var form = $('#candidateSheetForm')[0];
	console.log(form);
	var data = new FormData(form);
	// New Formdata will contain customised names for attributes
	dta = new FormData();
	dta.append("candidateSheet", data.get("candidateSheet"));
	dta.append("description", data.get("description"));

	$("#sheetUploadBtn").removeAttr("disabled");
	$("#sheetUploadBtnblock").removeAttr("disabled");
	$("#sheetUploadBtnunblock").removeAttr("disabled");

});
// Setting status as Registration Number sheet is to be uploaded
$(document).on('change', "#candidateRegSheet", function() {
	dta = null;
	status = null;
	$("#candidateSheet").val('');
	$("#candidateEmailSheet").val('');
	status = "r";
	var form = $('#candidateRegSheetForm')[0];

	var data = new FormData(form);
	// New formdata will contain customised values for attribute names
	dta = new FormData();
	dta.append("candidateSheet", data.get("candidateRegSheet"));
	dta.append("description", data.get("description"));

	$("#sheetUploadBtn").removeAttr("disabled");
	$("#sheetUploadBtnblock").removeAttr("disabled");
	$("#sheetUploadBtnunblock").removeAttr("disabled");

	
});
// setting status as Email Sheet to be uplaoded
$(document).on('change', "#candidateEmailSheet", function() {
	// Initialising vars to null
	dta = null;
	status = null;
	// setting values of other file input to look clean
	$("#candidateRegSheet").val("");
	$("#candidateSheet").val("");
	status = "e";
	var form = $('#candidateEmailSheetForm')[0];
	console.log(form);
	var data = new FormData(form);
	// new Formdata element will contain customised values of names of
	// attributes
	dta = new FormData();
	dta.append("candidateSheet", data.get("candidateEmailSheet"));
	dta.append("description", data.get("description"));

	$("#sheetUploadBtn").removeAttr("disabled");
	$("#sheetUploadBtnblock").removeAttr("disabled");
	$("#sheetUploadBtnunblock").removeAttr("disabled");

});
// ------------Multiple Candidates deletion ends here----



//------Single candidate deletion starts here-----
$(document).on("click", "#indiCandidateDeletionTab", function() {
	/**
	 * Javascript to open selected tab and hide the unselected ones
	 */
	
	$("#infoDiv").attr("hidden", "true");
	

});
// Monitoring onchange event of criteria dropdown
// global variable for criteria textBox check
$(document)
		.on(
				"change",
				"#criteria",
				function() {
					 $("#deleteIndiCanBtn").attr("disabled",'disabled');
					 $("#deleteIndiCanBtn").attr("hidden",'hidden');
					 
					 $("#blockIndiCanBtn").attr("disabled",'disabled');
					 $("#blockIndiCanBtn").attr("hidden",'hidden');
					 
					 $("#unblockIndiCanBtn").attr("disabled",'disabled');
					 $("#unblockIndiCanBtn").attr("hidden",'hidden');
					 
					 $('#notAllocateMessg').html("");
					 
					 
					 
					var sel = $("#criteria").val();
					switch (sel) {
					// If email is selected Email text box will be shown and
					// proper validation will take place
					case 'e': {
						$("#criteriaForm")
								.append(
										"<div id='email-group' class='form-group row custom-form-label criteria-style'><label class='labler'>Email:</label><input type='email' class='form-control' name='email' id='email'/></div>");
						$("#reg-group").remove();
						$("#app-group").remove();
						$("#detailsAllocation").remove();
						status = 'e';
					}
						break;
					// if registration number is selected text box will appear
					case 'r': {
						$("#criteriaForm")
								.append(
										"<div class='form-group row custom-form-label criteria-style' id='reg-group'><label class='labler'>Registration number:</label><input type='text' class='form-control' name='regNumber' id='regNumber'/></div>");
						$("#email-group").remove();
						$("#app-group").remove();
						$("#detailsAllocation").remove();
						status = 'r';

					}
						break;
					case 'a': {
						$("#criteriaForm")
								.append(
										"<div class='form-group row custom-form-label criteria-style' id='app-group'><label class='labler'>Applicant Credential Id:</label><input type='number' class='form-control' name='appCredId' id='appCredId'/></div>");
						$("#reg-group").remove();
						$("#detailsAllocation").remove();
						$("#email-group").remove();
						status = 'a';

					}
						break;
					default: {
						alert("Please choose appropriate option!");
						$("#reg-group").remove();
						$("#email-group").remove();
						$("#app-group").remove();
						$("#detailsAllocation").remove();
						return;
					}
						break;
					}

				});






$(document)
		.ready(
				function() {
					
					// Jquery to validate criteria form
					$.validator.addMethod("regex", function(value, element, regexp) {
						var re = new RegExp(regexp);
						return this.optional(element) || re.test(value);
					}, "Please check your input (Input should Contain only letters).");

					$("#criteriaForm")
							.validate(
									{
										rules : {
											email : {
												required : true,
												regex : /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/
											},
											regNumber : {
												required : true,
												regex : /^[0-9]{4}[A-Z]{2}[0-9]{7}$/
											},
											appCredId : {
												required : true,
												regex : /^[0-9]+$/
											}

										},
										messages : {
											email : {
												required : "Email cannot be left blank",
												regex : "Please ensure Email is in proper format!"
											},
											regNumber : {
												required : "Registration Number cannot be left blank",
												regex : "Please ensure Registration Number is in proper format!"
											},
											appCredId : {
												required : "ApplicantCredId cannot be left blank",
												regex : "Only numeric digits are allowed ,Please ensure AppCredId is in proper format!"
											}

										}
									});
				});
$(document).on('change', '#email,#regNumber,#appCredId', function() {
	$("#detailsAllocation").remove();
	 $("#deleteIndiCanBtn").attr("disabled",'disabled');
	 $("#deleteIndiCanBtn").attr("hidden",'hidden');
	 
	 $("#blockIndiCanBtn").attr("disabled",'disabled');
	 $("#blockIndiCanBtn").attr("hidden",true);
	 
	 $("#unblockIndiCanBtn").attr("disabled",'disabled');
	 $("#unblockIndiCanBtn").attr("hidden",true);
	 
		
	
	$("#showIndiCanBtn").removeAttr("disabled");
	switch (status) {
	case 'a': {
		dta = $("#appCredId").val();

		return;
	}
		break;
	case 'e': {
		dta = $("#email").val();

		return;

	}
		break;
	case 'r': {
		dta = $("#regNumber").val();

		return;
	}
		break;
	default: {
		alert("Try Again !");
		return;
	}
		break;

	}
});

$(document).on('click', '#deleteIndiCanBtn', function() {

	sendAjaxSingleDel(dta, status);
});


$(document).on('click', '#blockIndiCanBtn', function() {

	sendAjaxSingleBlock(dta, status);
});

$(document).on('click', '#unblockIndiCanBtn', function() {

	sendAjaxSingleunBlock(dta, status);
});


function sendAjaxSingleDel(id, st) {

	$("#deleteIndiCanBtn").attr("disabled", "disabled");
	$('#notAllocateMessg').html("");
	//var token = $("meta[name='_csrf']").attr("content");
	$("#loading").show();
	$.ajax({
		type : 'POST',
		url : 'deleteIndividualCandidate?id=' + id + "&status=" + st,
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/

		dataType : 'text',
		contentType : false,

		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(data);
			dta = null;
			status = null;
			/* location.reload(); */
			return;
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(xhr.responseText);
			/* location.reload(); */
			return;
		}
	});

}

function sendAjaxSingleBlock(id, st) {

	$("#deleteIndiCanBtn").attr("disabled", "disabled");
	 $("#blockIndiCanBtn").attr("disabled",'disabled');
	 $("#unblockIndiCanBtn").attr("disabled",'disabled');
	
	 $("#blockIndiCanBtn").attr("hidden",'hidden');
	 
	$('#notAllocateMessg').html("");
	 $("#unblockIndiCanBtn").attr("hidden",'hidden');
	
	//var token = $("meta[name='_csrf']").attr("content");
	$("#loading").show();
	$.ajax({
		type : 'POST',
		url : 'blockIndividualCandidate?id=' + id + "&status=" + st,
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/

		dataType : 'text',
		contentType : false,

		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(data);
			dta = null;
			status = null;
			/* location.reload(); */
			return;
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(xhr.responseText);
			
			/* location.reload(); */
			return;
		}
	});

}

function sendAjaxSingleunBlock(id, st) {

	$("#deleteIndiCanBtn").attr("disabled", "disabled");
	 $("#blockIndiCanBtn").attr("disabled",'disabled');
	 $("#unblockIndiCanBtn").attr("disabled",'disabled');
	
	 $("#blockIndiCanBtn").attr("hidden",'hidden');
	 
	 $('#notAllocateMessg').html("");
	 $("#unblockIndiCanBtn").attr("hidden",'hidden');
	
	//var token = $("meta[name='_csrf']").attr("content");
	$("#loading").show();
	$.ajax({
		type : 'POST',
		url : 'unblockIndividualCandidate?id=' + id + "&status=" + st,
		/*headers : {
			'X-CSRF-TOKEN' : token
		},*/

		dataType : 'text',
		contentType : false,

		success : function(data, xhr, text) {
			$("#loading").hide();
			console.log("SUCCESS : ", data);
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(data);
			dta = null;
			status = null;
			/* location.reload(); */
			return;
		},
		error : function(xhr, textStatus, errorThrown) {
			$("#loading").hide();
			$("#criteria").val("0");
			$("#reg-group").remove();
			$("#email-group").remove();
			$("#app-group").remove();
			$("#detailsAllocation").remove();
			alert(xhr.responseText);
			
			/* location.reload(); */
			return;
		}
	});

}

$(document).on(
		"click",
		"#showIndiCanBtn",
		function() {

			//var token = $("meta[name='_csrf']").attr("content");
			if (!($("#criteria").val() == 0)) {
				if ($("#criteriaForm").valid()) {
					$("#loading").show();
					// This ajax isacknowledged at misc
					$.ajax({
						type : 'GET',

						url : 'getApplicantAllocantDetails?id=' + dta
								+ "&status=" + status,
						/*headers : {
							'X-CSRF-TOKEN' : token
						},*/

						dataType : 'JSON',
						contentType : false,

						success : function(data, xhr, text) {
							$("#loading").hide();
							$("#criteria").val("0");
							console.log("SUCCESS : ", data);
							$("#reg-group").remove();
							$("#email-group").remove();
							$("#app-group").remove();

							$('#notAllocateMessg').html("");
							//$("#deleteIndiCanBtn").attr('hidden', 'false');
							$("#deleteIndiCanBtn").removeAttr("hidden");
							
							 $("#blockIndiCanBtn").attr("disabled", "disabled");
							 $("#blockIndiCanBtn").attr("hidden",'hidden');
							 $("#unblockIndiCanBtn").attr("disabled", "disabled");
							 $("#unblockIndiCanBtn").attr("hidden",'hidden');
							
							$("#showDiv").append(
									"<div id='detailsAllocation' class='container table-responsive'>" +
									"<table class='table table-hover'><thead>"+
									"<tr><th scope='col'>Alloted Slot</th><th scope='col'>Centre Name</th><th scope='col'>Data</th></tr></thead>"+
									"<tbody><tr><td>"+data.slotName+"</td><td>"+data.centreName+"</td><td>"+dta+"</td></tr></tbody></table>"
											+ "</div>");
							$("#deleteIndiCanBtn").removeAttr("disabled");
							
							// location.reload();
							return;
						},
						error : function(xhr, textStatus, errorThrown) {
							$("#loading").hide();
							console.log(xhr);
							var mesg=xhr.responseText.split(":");
							$('#notAllocateMessg').html(mesg[1]);
							
							
							if(mesg[mesg.length-1] === 'block')
								{
								$("#deleteIndiCanBtn").attr("disabled", "disabled");
								 $("#deleteIndiCanBtn").attr("hidden",'hidden');
								$("#blockIndiCanBtn").removeAttr("hidden");
								$("#blockIndiCanBtn").removeAttr("disabled");
								
								$("#unblockIndiCanBtn").attr("hidden","hidden");
								
								$("#unblockIndiCanBtn").attr("disabled","disabled");
								}
							else if(mesg[mesg.length-1] === 'unblock')
								{
								$("#deleteIndiCanBtn").attr("disabled", "disabled");
								 $("#deleteIndiCanBtn").attr("hidden",'hidden');
								 
								$("#unblockIndiCanBtn").removeAttr("hidden");
								$("#unblockIndiCanBtn").removeAttr("disabled");
								
								$("#blockIndiCanBtn").attr("hidden","hidden");
								
								$("#blockIndiCanBtn").attr("disabled","disabled");
								}
							else 
								{
								$("#criteria").val("0");
								console.log("error");
								$("#reg-group").remove();
								$("#email-group").remove();
								$("#app-group").remove();
								
								 $("#blockIndiCanBtn").attr("disabled",'disabled');
								 $("#blockIndiCanBtn").attr("hidden",'hidden');
								 
								 $("#unblockIndiCanBtn").attr("disabled",'disabled');
								 $("#unblockIndiCanBtn").attr("hidden",'hidden');
								
								
								}
							
							
							
							// location.reload();
							return;
						}
					});
				}
			} else
				alert("Please choose appropriate option !");
		});
