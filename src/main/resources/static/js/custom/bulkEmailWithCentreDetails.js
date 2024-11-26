var sentEmailType = ""

$(document).ready(function () {


	$("#loading").hide();
	$("#scheduleBlock").hide();
	$("#quickScheduleBtn").hide();

	$("#mailReason").on("change", function(){
		var name = ($(this).val()).replace(/[a-zA-Z0-9 ]/g, "");
		if(name != ""){
			alert("Mail Reason can not contain any special character!")
		}
	});

	$('#dropdownSlots').multiselect({
		includeSelectAllOption: true
	});
	
	$('#dropdownCenter').multiselect({
		includeSelectAllOption: true,
		enableFiltering: true,
		maxHeight: 300,
		disableIfEmpty:true,
		disabledText: '---------------Select Centers From List-----------------------',
		inheritClass: true,
		filterBehavior:'both',
		enableCaseInsensitiveFiltering: true
	});
	
	$('#dropdownExam').multiselect({
		
	});

	populateCenters();

	 $('#defaultConfigCheckBox').change(function() {
	     if(this.checked) {
	         var returnVal = "checkbox is checked";
	         $(this).prop("checked", returnVal);
	         $('#defaultConfigCheckBox').val(this.checked);
	         
	         $('#mailServerHost').val('smtp.cdac.in');
	         $('#mailServerPort').val('587');
	         $('#socketFactoryPort').val('587');
	         $("#yesstarttlsRadio").prop("checked", true);
	     }
	     else
	    	 {
		    	 $('#mailServerHost').val('');
		         $('#mailServerPort').val('');
		         $('#socketFactoryPort').val('');
		         $("#yesstarttlsRadio").prop("checked", false);
	    	 }
	    
	     
	 });

	$("#discard").click(function (event) {

		$('#mailSubject').val('');
		document.getElementById("yesRadio").checked = false;
		document.getElementById("noRadio").checked = false;
		$('#emailContent').val('');

	});

	$("#btnSendBulkEmailWithExcel1").click(function (event) {

		event.preventDefault();

		console.log("inside")
		$("#loading").show();
		
		

		var emailContent = $('#emailContent').val();

		var emailSubject = $('#mailSubject').val();

		var sendAdmitCardsRadio = $("input[name=optradio]:checked").val();



		var slots = $('#dropdownSlots').val();
		var centers = $('#dropdownCenter').val();
		var exam = $('#dropdownExam').val();

		var emailServertype= $("input[name='emailServerTypeRadio']:checked").val();
		if(emailServertype=="smtp"){
				var mailServerHost = "smtp.cdac.in";
			    var mailServerPort = "587";
				var starttls = true;
				var socketFactoryPort = "587";
			}
		else if(emailServertype=="smtpb"){
				var mailServerHost = "smtpb.pune.cdac.in";
			    var mailServerPort = "25";
				var starttls = false;
				var socketFactoryPort = "25";
			}
		else if(emailServertype=="smtpdr"){
			var mailServerHost = "smtp-dr.cdac.in";
		    var mailServerPort = "25";
			var starttls = false;
			var socketFactoryPort = "25";
		}
		else{
				var mailServerHost = null;
			    var mailServerPort = null;
				var starttls = false;
				var socketFactoryPort = null;
			}	
		
		var str = "";
		if (sentEmailType == '2' || sentEmailType == '3') {
			$("#scheduleTableBody tr").each(function(index, tr) {
				console.log("index : "+ index+ " tr : "+ tr)
				console.log("inputs : "+ $($($($(this).get()).children().get(1)).find('input').get(0)).val())
				for (var i = 0; i < ($($($(this).get()).children().get(2)).find('input').get().length); i++) {
					var refTimes = $($($($(this).get()).children().get(2)).find('input').get(i)).val();							
					str += $($($($(this).get()).children().get(1)).find('input').get(0)).val()+ "T"+ refTimes+ ",";
				}

			});
		}
						      
		var mailReason = $('#mailReason').val();
		var emailBreathingTime = $('#breathingTime').val();
		var emailsPerBatch = $('#emailsPerBatch').val();		

		var formData = new FormData();

		formData.append("emailContent", emailContent);
		formData.append("emailSubject", emailSubject);
		formData.append("sendAdmitCardsRadio", sendAdmitCardsRadio);
		formData.append("slots", slots);
		formData.append("centers", centers);
		formData.append("mailServerHost", mailServerHost);
		formData.append("mailServerPort", mailServerPort);
		formData.append("socketFactoryPort", socketFactoryPort);
		formData.append("starttls", starttls);
		formData.append('exam',exam);
		formData.append('mailReason', mailReason);
		formData.append('emailBreathingTime',emailBreathingTime);
		formData.append('emailsPerBatch',emailsPerBatch);
		formData.append("sentType", sentEmailType)
		formData.append("scheduleList", str);
		
		$.ajax({
			type: 'POST',
			contentType: false,
			processData: false,
			async: false,
			url: '../'+$("#pageType").val()+'/sendCenterwiseTestEmails',
			data: formData
		}).done(function (response) {
			if (response === 'success') {
				$("#loading").fadeOut("slow");

				$("#successModalMessage").html("Email Sending Completed!");

				$('#successModal').modal("show");
				setTimeout(function () { location.reload(); }, 4000);

			}
			else if (response != 'success') {
				$("#errorModalMessage").html(response);
				$('#errorModal').modal("show");
			}

		}).fail(function (status, error, desc) {

			$("#errorModalMessage").html("Some error occurred.");
			$('#errorModal').modal("show");
		});

	});

	
	$("#candidateCount").click(function (event) {

		var slots = $('#dropdownSlots').val();
		var centers = $('#dropdownCenter').val();
		
		console.log(slots)
		console.log(centers)
		let jsonObj={
			"centerCode":centers,
			"slotCode":slots
		}
		$.ajax({
			type : 'POST',
			processData : false,
			contentType : "application/json",
			async : false,
			url : 'countCandidate',
		    data : JSON.stringify(jsonObj)
		}).done(function(data) {

			if (data.status == "success") {
			if (data.countCandidate != null) {
				$("#candidateCount").html("Candidate Alloted Count Is = "+data.countCandidate+ "<br>Make Sure That Center Code "+data.centerList+" And Slot "+data.slotList+" is_email_sent Flag Is False In applicant_hallticket.");
			} else {
				$("#candidateCount").text("0");
			}	
		}else if(data.status=="error"){
			$("#errorModalMessage").html(data.data);
			$('#errorModal').modal("show");
		}
		}).fail(function(xhr, err) {
			$("#errorModalMessage").html("Select Center Code And Slot");
			$('#errorModal').modal("show");
		});
	})


});

function populateCenters() {

	var optionsCenters = [];
	optionsCenters.length = 0;

	$.ajax({
		type: "POST",
		url: '../'+$("#pageType").val()+"/populateCenters",
		async: false,
		dataType: "json",
		headers : {
			'X-CSRF-TOKEN' : token
		},
		success: function (response, textStatus, request) {
			if ($('#dropdownCenter > option').length == 0)//check if slots is not present in drop down
			{
				$.each(response, function (key, value) {
					optionsCenters.push({ label: this.centreCode+"-"+this.centreName, title: this.centreCode, value: this.centreCode });
				});
				$('#dropdownCenter').multiselect('dataprovider', optionsCenters);
			}
		},
		error: function (data) {
			console.log("error in getting data : " + data);
		}
	});
}

function populateSlots() {

	var optionsSlots = [];
	optionsSlots.length = 0;

	$.ajax({
		type: "POST",
		url: "../populateIcgSlots",
		async: false,
		dataType: "json",
		headers : {
			'X-CSRF-TOKEN' : token
		},
		success: function (response, textStatus, request) {
			if ($('#dropdownSlots > option').length == 0)//check if slots is not present in drop down
			{
				$.each(response, function (key, value) {
					optionsSlots.push({ label: this.examSlotCode, title: this.examSlotCode, value: this.examSlotCode });
				});
				$('#dropdownSlots').multiselect('dataprovider', optionsSlots);
			}
		},
		error: function (data) {
			console.log("error in getting data : " + data);
		}
	});
}


function makeDivEnabledOrDisabled(id1, status) {
	console.log(status, " : ", id1)
	if (status === true) {
		$("#" + id1).show();
		$("#quickScheduleBtn").show();
		$("#quickSMSBtn1").hide();
		$("#sect1").hide();
		$("#sect2").hide();
		$("#scheduledDataTable").hide();
	} else {
		$("#" + id1).hide();
		$("#quickSMSBtn1").show();
		$("#quickScheduleBtn").hide();

	}
}

function toDateChange(btn) {
	if ($("#fromDate1").val().length == 0) {
		$("#fromDate1").css('border', '1px solid red')
	} else {
		$("#fromDate1").css('border', '1px solid black')
	}

	if ($("#toDate1").val().length == 0) {
		$("#toDate1").css('border', '1px solid red')
	} else {
		$("#toDate1").css('border', '1px solid black')
	}
	if ($("#fromDate1").val().length != 0 && $("#toDate1").val().length != 0) {
		if (btn == 'btn1') {
			console.log(btn)
			$("#sect1").show();
			$("#sect2").hide();
		} else {
			$("#sect2").show();
			$("#sect1").hide();
		}
	}
}

function addDataToTable(btn) {
	var markup = "";

	if (btn == 'sec1') {
		var edate = new Date($("#fromDate1").val());
		var sdate = new Date($("#toDate1").val());

		var days = (sdate - edate) / (1000 * 60 * 60 * 24);
		var addDate = new Date(edate.setDate(edate.getDate()))
				.toLocaleDateString('en-CA');
		for (var i = 0; i <= days; i++) {
			$("#scheduleTableBody")
					.append(
							"<tr id=scheduleTable"
									+ (parseInt($("#scheduleTableBody > tr").length) + 1)
									+ "><td class='col-sm-1'>"
									+ ($("#scheduleTableBody > tr").length + 1)
									+ "</td><td class='col-sm-2'><input type='date' value='"
									+ addDate
									+ "'></td>"
									+ "<td class='col-sm-1'><div class='d-inline p-2 col-sm m-2' id=scheduleTable"
									+ (rowLen + 1)
									+ ""
									+ j
									+ "><input type='time' value='"
									+ $("#addTime1").val()
									+ "'><i type='button' onclick='deleteTime("
									+ (rowLen + 1)
									+ ","
									+ j
									+ ")' class='deleteIcon fa fa-trash' style='color:red;'></i></div></td>"
									+ "<td class='col-sm-1'><button type='button' class=' btn btn-danger' onclick='deleteRow("
									+ (parseInt($("#scheduleTableBody > tr").length) + 1)
									+ ")'>Delete</button></td></tr>");
			addDate = new Date(edate.setDate(edate.getDate() + 1))
					.toLocaleDateString('en-CA');
		}
	} else {
		var edate = new Date($("#fromDate1").val());
		var sdate = new Date($("#toDate1").val());
		var days = (sdate - edate) / (1000 * 60 * 60 * 24);

		var addDate = new Date(edate.setDate(edate.getDate()))
				.toLocaleDateString('en-CA');

		for (var i = 0; i <= days; i++) {
			var addDelay = new Date();
			var addDelay2 = $("#addTime2").val().split(":")
			addDelay.setHours(addDelay2[0])
			addDelay.setMinutes(addDelay2[1])

			var rowLen = $("#scheduleTableBody > tr").length;
			var addString = "<tr id=scheduleTable" + (rowLen + 1) + "><td>"
					+ (rowLen + 1) + "</td><td><input type='date' value='"
					+ addDate + "'></td><td>";
			for (var j = 0; j <= $("#repeat").val(); j++) {

				addString += "<div class='d-inline p-2 col-sm .bg-success m-2' id=scheduleTable"
						+ (rowLen + 1)
						+ ""
						+ j
						+ "><input type='time' class='px-2' value='"
						+ addDelay.toLocaleTimeString('en-US', {
							hour : '2-digit',
							minute : '2-digit',
							hourCycle : 'h23'
						})
						+ "'><i type='button' onclick='deleteTime("
						+ (rowLen + 1)
						+ ","
						+ j
						+ ")' class='deleteIcon fa fa-trash' style='color:red;'></i></div>";
				addDelay.setHours(parseInt(addDelay.getHours())
						+ parseInt($("#delay").val()))
			}

			addString += "</td><td><button type='button' class='btn btn-danger' onclick='deleteRow("
					+ (rowLen + 1) + ")'>Delete</button></td></tr>";
			$("#scheduleTableBody").append(addString)
			addDate = new Date(edate.setDate(edate.getDate() + 1))
					.toLocaleDateString('en-CA');

		}
	}
	$("#scheduledDataTable").show();
	$("#scheduledTable").DataTable();
}

function deleteRow(id) {
	$("#scheduleTable" + id).remove();
}
function deleteTime(id1, id2) {
	$("#scheduleTable" + id1 + id2).remove();
}

function changeurl(sentType) {
	sentEmailType = sentType;
}