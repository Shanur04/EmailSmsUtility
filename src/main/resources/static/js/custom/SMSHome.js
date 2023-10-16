var sentSMSType = "";
var pageType = $("#pageTypeId").val();

$(document)
		.ready(
				function() {
					var token = $("meta[name='_csrf']").attr("content");
					$("#loading").hide();

					$("#scheduleBlock").hide();
					$("#quickScheduleBtn").hide();
					$("#scheduleListId").hide();

					$('#sendSMSForm')
							.submit(
									function(event) {
										event.preventDefault();
										var errMsgFE = '';

										if ($(
												'#smsReasonMasterId option:selected')
												.val() == "-1")
											errMsgFE = 'Please select reason!';

										if ($(
												'#templateMasterId option:selected')
												.val() == "-1")
											errMsgFE = 'Please select template!';

										if (errMsgFE != '') {
											$('#errorModalMessage').html(
													errMsgFE);
											$('#errorModal').modal('show');
											return;
										}
										var str = "";
										if (sentSMSType == '2'
												|| sentSMSType == '3') {
											$("#scheduleTableBody tr")
													.each(
															function(index, tr) {
																console
																		.log("index : "
																				+ index
																				+ " tr : "
																				+ tr)
																console
																		.log("inputs : "
																				+ $(
																						$(
																								$(
																										$(
																												this)
																												.get())
																										.children()
																										.get(
																												1))
																								.find(
																										'input')
																								.get(
																										0))
																						.val())
																for (var i = 0; i < ($(
																		$(
																				$(
																						this)
																						.get())
																				.children()
																				.get(
																						2))
																		.find(
																				'input')
																		.get().length); i++) {
																	var refTimes = $(
																			$(
																					$(
																							$(
																									this)
																									.get())
																							.children()
																							.get(
																									2))
																					.find(
																							'input')
																					.get(
																							i))
																			.val();
																	// var
																	// refDate =
																	// new
																	// Date($($($($(this).get()).children().get(1)).find('input').get(0)).val());
																	// refDate.setTime(refTimes)
																	// console.log(refDate)
																	str += $(
																			$(
																					$(
																							$(
																									this)
																									.get())
																							.children()
																							.get(
																									1))
																					.find(
																							'input')
																					.get(
																							0))
																			.val()
																			+ "T"
																			+ refTimes
																			+ ",";
																	// str+=refDate.toISOString()+","
																}

															});
										}
										console.log(str)

										$("#scheduleListId").val(str)
										var action = $(this).attr('action')
												+ sentSMSType;

										$(this).get(0).setAttribute("action",
												action);
										$(this).get(0).setAttribute("method",
												"POST");
										console.log("action : ", $(this).attr(
												'action'));

										$(this).unbind('submit').submit();

									});

					$('#checkCount')
							.click(
									function() {
										let centreCode = $('#centreId').val();
										let slotIds = $('#slotIds').val();

										if (centreCode == "-1"
												|| slotIds.length == 0) {
											$('#errorModalMessage')
													.html(
															'Please select centre and exam slot!');
											$('#errorModal').modal();
										} else {
											$
													.ajax({
														type : 'POST',
														url : pageType
																+ '/checkCount/'
																+ centreCode,
														traditional : true,
														headers : {
															'X-CSRF-TOKEN' : token
														},
														data : {
															'slotIds' : slotIds
														},
														datatype : 'json',
														beforeSend : function(
																xhr, s) {
															$("#loading")
																	.show();
														},
														complete : function(
																xhr, textStatus) {
															$("#loading")
																	.fadeOut(
																			"slow");
															if (xhr.status != 200) {
																$(
																		'#errorModalMessage')
																		.html(
																				'Something went wrong!');
																$('#errorModal')
																		.modal();
																return;
															} else {
																$(
																		'#successModalMessage')
																		.html(
																				xhr.responseText);
																$(
																		'#successModal')
																		.modal();
															}
														}
													});
										}
									});

					$('#table_previousSMS').DataTable();
					$('#table_smsTemplates').DataTable();
					$("#scheduledDataTableId").DataTable();
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

	$("#scheduleTableBody").empty();
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
									+ ($("#scheduleTableBody > tr").length + 1)
									+ "><td style='width: 5px;'>"
									+ ($("#scheduleTableBody > tr").length + 1)
									+ "</td><td><input type='date' value='"
									+ addDate
									+ "'></td>"
									+ "<td><div class='d-inline p-2 m-2' id=scheduleTable"
									+ (rowLen + 1)
									+ ""
									+ j
									+ "><input type='time' value='"
									+ $("#addTime1").val()
									+ "'><i type='button' onclick='deleteTime("
									+ (rowLen + 1)
									+ ","
									+ j
									+ ")' class='deleteIcon fa fa-trash'></i></div></td>"
									+ "<td style='width: 5px;'><button type='button' class=' btn btn-danger' onclick='deleteRow("
									+ ($("#scheduleTableBody > tr").length + 1)
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
			var addString = "<tr id=scheduleTable" + (rowLen + 1) + "><td style='width: 5px;'>"
					+ (rowLen + 1) + "</td><td><input type='date' value='"
					+ addDate + "'></td><td class='row'>";
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
						+ ")' class='deleteIcon fa fa-trash'></i></div>";
				addDelay.setHours(parseInt(addDelay.getHours())
						+ parseInt($("#delay").val()))
			}

			addString += "</td><td style='width: 5px;'><button type='button' class='btn btn-danger' onclick='deleteRow("
					+ (rowLen + 1) + ")'>Delete</button></td></tr>";
			$("#scheduleTableBody").append(addString)
			addDate = new Date(edate.setDate(edate.getDate() + 1))
					.toLocaleDateString('en-CA');

		}
	}
	
	$("#scheduledDataTable").show();
}
function deleteRow(id) {
	$("#scheduleTable" + id).remove();
}
function deleteTime(id1, id2) {
	$("#scheduleTable" + id1 + id2).remove();
}

function scheduleTableForChanges() {
	$("#scheduledData").show();

	var markup = "";
	var date = new Date();
	console.log("Date : ", date, "\n Hours : ", date.getHours())
	$("#scheduleTableBody").empty();
	for (var i = 0; i < ($("#repeat").val()); i++) {
		markup = markup
				+ "<tr><td><input type='datetime-local' class='w-25 form-control'></td></tr>";
	}
	$("#scheduleTableBody").append(markup);
}

function scheduleTableForChanges2() {
	$("#scheduledData2").show();

	var markup = "";
	var date = new Date();
	$("#scheduleTableBody2").empty();
	for (var i = 0; i < ($("#repeat2").val()); i++) {
		markup = markup
				+ "<tr><td><input type='datetime-local' class='w-25 form-control' th:name='scheduleDateTime'></td></tr>";
	}
	$("#scheduleTableBody2").append(markup);
}

function changeurl(sentType) {
	console.log(sentType)
	sentSMSType = sentType;
}


function downloadExcel(smsScheduleId, pageType) {
  var token = $("meta[name='_csrf']").attr("content");

	window.location.href = '../../smsSent/'+pageType + '/downloadExcel/' + smsScheduleId;
//	$.ajax({
//		type : 'GET',
//		url : '../../smsSent/'+pageType + '/downloadExcel/' + smsScheduleId,
//		traditional : true,
//		headers : {
//			'X-CSRF-TOKEN' : token
//		},
//		datatype : 'json',
//		beforeSend : function(xhr, s) {
//			$("#loading").show();
//		},
//		success : function(xhr) {
//			$("#loading").fadeOut("slow");
//			if (xhr.status != 200) {
//				$('#errorModalMessage').html('Something went wrong!');
//				$('#errorModal').modal();
//				return;
//			} else {
//				$('#successModalMessage').html(xhr.responseText);
//				$('#successModal').modal();
//			}
//		},
//		error : function() {
//			alert("ERRROR")
//		}
//	});
}

$("#showSentHistoryBtn").on('click', function(){
	$("#showHistoryOfSMSSentModal").modal("show");
});

$("#showTemplatesBtn").on('click', function(){
	$("#showHistoryOfSMSTemplateModal").modal("show");
});