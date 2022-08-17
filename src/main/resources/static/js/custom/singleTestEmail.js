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
	
	$("#fileuploader").uploadFile({
		url: '../'+$("#pageType").val()+"/upload",
		fileName: "myfile",
		showDelete: true,
		showPreview:true,
		previewHeight: "100px",
		previewWidth: "100px",
		returnType: "json",
		onLoad:function(obj)
		   {
		   	$.ajax({
			    	cache: false,
				    url: '../'+$("#pageType").val()+"/load",
				    type:"POST",
			    	dataType: "json",
				    success: function(data) 
				    {
					    for(var i=0;i<data.length;i++)
		   	    	{ 
		       			obj.createProgress(data[i]["name"],data[i]["file"],data[i]["size"]);
		       		}
			        }
				});
		  },
		deleteCallback: function (data, pd) {
			 for (var i = 0; i < data.length; i++) {
			        $.post("delete", {op: "delete",name: data[i]},
			            function (resp,textStatus, jqXHR) {
			                if(resp=="success"){			                	
			                	$("#successModalMessage").html("File deleted!");
			    				$('#successModal').modal("show");			              
			                }
			                else
			                	{
				                	$("#errorModalMessage").html("Unable to delete file!");
				    				$('#errorModal').modal("show");
			                	}
			            });
			    }
		}
	});

	
	$("#plus").on("click", function () {
		$("#testEmailDiv").append('<input type="text" name="testEmailIds[]" class="form-control" placeholder="Add Email Id"/>');
	});

	$("#minus").on("click", function () {

		if ($("input[name='testEmailIds[]']").length > 1) {
			$("input[name='testEmailIds[]']:eq(" + (length - 1) + ")").remove();
		}

	});

	$("#discard").click(function (event) {
		$('#mailSubject').val('');
		$('#emailContent').val('');
	});


	$(".testEmailButton").on("click", function () {
		
		console.log("1")
		
		$("#loading").show();
		
		var testEmailIds = [];
		$("input[name='testEmailIds[]']").each(function () {

			testEmailIds.push($(this).prop("value"));

		});


		var emailContent = $('#emailContent').val();
		var emailSubject = $('#mailSubject').val();
		
		var mailReason = $('#mailReason').val();
		
		var emailServertype= $("input[name='emailServerTypeRadio']:checked").val();
		if(emailServertype=="smtp")
			{
				var mailServerHost = "smtp.cdac.in";
			    var mailServerPort = "587";
				var starttls = true;
				var socketFactoryPort = "587";
			}
		else if(emailServertype=="smtpb")
			{
				var mailServerHost = "smtpb.pune.cdac.in";
			    var mailServerPort = "25";
				var starttls = false;
				var socketFactoryPort = "25";
			}
		else if(emailServertype=="smtpdr")
		{
			var mailServerHost = "smtp-dr.cdac.in";
		    var mailServerPort = "25";
			var starttls = false;
			var socketFactoryPort = "25";
		}
		else
			{
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
		
		console.log("2")
		var formData = new FormData();

		formData.append("emailContent", emailContent);
		formData.append("emailSubject", emailSubject);
		formData.append("testEmailIds", testEmailIds);
		formData.append("mailServerHost", mailServerHost);
		formData.append("mailServerPort", mailServerPort);
		formData.append("socketFactoryPort", socketFactoryPort);
		formData.append("starttls", starttls);
		formData.append("mailReason",mailReason);
		formData.append("sentType", sentEmailType)
		formData.append("scheduleList", str);
		
		$.ajax({
			type: 'POST',
			contentType: false,
			processData: false,
			async: false,
			url: '../'+$("#pageType").val()+'/sendTestEmails',
			data: formData
		}).done(function (response) {
			if (response === 'success') {
				$("#loading").fadeOut("slow");
				$("#successModalMessage").html("Email Sending Completed!");
				//$("#successModalOk").attr("href", "showDashBoard");
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
});

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