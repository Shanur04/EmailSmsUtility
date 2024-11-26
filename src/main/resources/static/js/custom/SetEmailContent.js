$(document)
		.ready(
				function() {
					var token = $("meta[name='_csrf']").attr("content");

					$("#loading").hide();

					$('#reportBody').empty();
					$("#reportDiv").hide();
					$(".closeSearchByEmailId").hide();
										
					$('#defaultConfigCheckBox').change(function() {
						if (this.checked) {
							var returnVal = "checkbox is checked";
							console.log("check box is checked");
							$(this).prop("checked", returnVal);
							$('#defaultConfigCheckBox').val(this.checked);

							$('#mailServerHost').val('smtp.cdac.in');
							$('#mailServerPort').val('587');
							$('#socketFactoryPort').val('587');
							$("#yesstarttlsRadio").prop("checked", true);
						} else {
							$('#mailServerHost').val('');
							$('#mailServerPort').val('');
							$('#socketFactoryPort').val('');
							$("#yesstarttlsRadio").prop("checked", false);
						}

					});

					if ($('#whichTabDataToShow').val().localeCompare(
							'singleEmail') == 0) {
						$('#totalCandidatesForSingleTestEmail').html(
								$('#totalCandidates').val());
						$('#attachmentsInSingleEmail').html(
								$('#totalAttachments').val());
						$('#hostInSingleEmail').html($('#host').val());

						if ($('#totalCandidates').val() > 0) {
							populateDataTableForTotalEmailNotSent();
							showTotalEmailSent();
							var intervalId = window.setInterval(
									function() {
										$('#dataTableForPhotoNotFound')
												.DataTable().ajax.reload();
									}, 60000);
							var intervalId1 = window.setInterval(function() {
								showTotalEmailSent();
							}, 60000);
						}

						// clearInterval(intervalId)

						function showTotalEmailSent() {
							$.ajax({
								type : "POST",
								url : "populateTotalEmailSent",
								async : false,
								dataType : "json",
								headers : {
								    'X-CSRF-TOKEN' : token
								},
								success : function(response, textStatus,
										request) {
									$('#totalEmailSentForSingleTestEmail')
											.html(response);
								},
								error : function(data) {
									console.log("error in getting data : "
											+ data);
								}
							});
						}

						function populateDataTableForTotalEmailNotSent() {
							$('#dataTableForPhotoNotFound').dataTable({
								"ajax" : {
									"type" : "POST",
									"url" : "populateDataTable",
									headers : {
										'X-CSRF-TOKEN' : token
									},
									"dataSrc" : function(response) {

										var jsonObj = [];
										for ( var a in response) {
											var variable = response[a]
											var id = "title";
											var email = variable;
											var item = {}
											item["title"] = id;
											item["email"] = email;
											jsonObj.push(item);
										}
										return jsonObj;
									}
								},
								"columns" : [ {
									data : "email"
								} ]
							});
						}
					}

					if ($('#whichTabDataToShow').val().localeCompare(
							'bulkExcelEmail') == 0) {
						$('#totalCandidatesForEmailIdsFromExcel').html(
								$('#totalCandidates').val());
						$('#attachmentsInBulkExcelEmail').html(
								$('#totalAttachments').val());
						$('#hostInBulkExcelEmail').html($('#host').val());

						if ($('#totalCandidates').val() > 0) {
							populateDataTableForTotalEmailNotSent();
							showTotalEmailSent();
							var intervalId = window.setInterval(
									function() {
										$('#dataTableForPhotoNotFound')
												.DataTable().ajax.reload();
									}, 60000);
							var intervalId1 = window.setInterval(function() {
								showTotalEmailSent();
							}, 60000);
						}
						// clearInterval(intervalId)

						function showTotalEmailSent() {
							$.ajax({
								type : "POST",
								url : "populateTotalEmailSent",
								async : false,
								dataType : "json",
								headers : {
									'X-CSRF-TOKEN' : token
								},
								success : function(response, textStatus,
										request) {
									$('#totalEmailSentForEmailIdsFromExcel')
											.html(response);
								},
								error : function(data) {
									console.log("error in getting data : "
											+ data);
								}
							});
						}

						function populateDataTableForTotalEmailNotSent() {
							$('#dataTableForPhotoNotFound').dataTable({
								"ajax" : {
									"type" : "POST",
									"headers" : {
										'X-CSRF-TOKEN' : token
									},
									"url" : "populateDataTable",
									"dataSrc" : function(response) {
										var jsonObj = [];
										for ( var a in response) {
											var variable = response[a]
											var id = "title";
											var email = variable;
											var item = {}
											item["title"] = id;
											item["email"] = email;
											jsonObj.push(item);
										}
										return jsonObj;
									}
								},
								"columns" : [ {
									data : "email"
								} ]
							});
						}
					}

					if ($('#whichTabDataToShow').val().localeCompare(
							'bulkEmail') == 0) {
						$(
								'#totalCandidatesForBulkEmailWithAdmitCardsAndOtherAttachments')
								.html($('#totalCandidates').val());
						$('#attachmentsInBulkEmail').html(
								$('#totalAttachments').val());
						$('#hostInBulkEmail').html($('#host').val());

						var totalcandidates = $('#totalCandidates').val();

						if (totalcandidates > 0) {

							populateDataTableForTotalEmailNotSent();
							showTotalEmailSent();
							var intervalId = window.setInterval(
									function() {
										$('#dataTableForPhotoNotFound')
												.DataTable().ajax.reload();
									}, 60000);
							var intervalId1 = window.setInterval(function() {
								showTotalEmailSent();
							}, 60000);
						}

						// clearInterval(intervalId)

						function showTotalEmailSent() {
							$
									.ajax({
										type : "POST",
										url : "populateTotalEmailSent",
										async : false,
										dataType : "json",
										headers : {
											'X-CSRF-TOKEN' : token
										},
										success : function(response,
												textStatus, request) {
											$(
													'#totalEmailSentForBulkEmailWithAdmitCardsAndOtherAttachments')
													.html(response);
										},
										error : function(data) {
											console
													.log("error in getting data : "
															+ data);
										}
									});
						}

						function populateDataTableForTotalEmailNotSent() {
							$('#dataTableForPhotoNotFound').dataTable({
								"ajax" : {
									"type" : "POST",
									"url" : "populateDataTable",
									headers : {
										'X-CSRF-TOKEN' : token
									},
									"dataSrc" : function(response) {
										var jsonObj = [];
										for ( var a in response) {
											var variable = response[a]
											var id = "title";
											var email = variable;
											var item = {}
											item["title"] = id;
											item["email"] = email;
											jsonObj.push(item);
										}
										return jsonObj;
									}
								},
								"columns" : [ {
									data : "email"
								} ]
							});
						}
					}

					if ($('#disableEnableButtons').val() == "disable") {
						$('#bulkEmailWithAdmitCardAndOtherAttachments').prop(
								'disabled', true);
						$('#bulkEmailWithExcelSheet').prop('disabled', true);
						$('#singleTestEmail').prop('disabled', true);
					} else {
						$('#bulkEmailWithAdmitCardAndOtherAttachments').prop(
								'disabled', false);
						$('#bulkEmailWithExcelSheet').prop('disabled', false);
						$('#singleTestEmail').prop('disabled', false);
					}

					$("#bulkEmailWithAdmitCardAndOtherAttachments")
							.click(
									function(event) {
										window.location.href = '../'
												+ $("#pageType").val()
												+ '/bulkEmailWithAdmitCardsAndOtherAttachments';

									});

					$("#bulkEmailWithExcelSheet").click(
							function(event) {

								window.location.href = '../'
										+ $("#pageType").val()
										+ '/bulkEmailWithExcelSheet';

							});

					$("#singleTestEmail").click(
							function(event) {

								window.location.href = '../'
										+ $("#pageType").val()
										+ '/singleTestEmail';

							});
					$("#singleRejectedEmail")
							.click(
									function(event) {

										window.location.href = '../smsSent/'
												+ $("#pageType").val()
												+ '/bulkEmailWithExcelSheetForRejected';

									});
					$("#bulkEmailMultiDataWithExcelSheet")
					.click(
							function(event) {

								window.location.href = '../'
										+ $("#pageType").val()
										+ '/bulkEmailMultiDataWithExcelSheet';

							});

					
					
					$("#reportModalClose").click(function() {
						$("#uploadDocTable tbody").empty();
						$("#reportTable tbody").empty();
					});
					
					$('#table_previousEmail').DataTable();
					$("#reportTable").DataTable();
				});

function downloadExcel(scheduleId) {
	console.log(scheduleId);
	var token = $("meta[name='_csrf']").attr("content");

	window.location.href = '../' + pageType + '/downloadExcel/' + scheduleId;
//	$.ajax({
//		type : 'GET',
//		url : '../' + pageType + '/downloadExcel/' + scheduleId,
//		traditional : true,
//		headers : {
//			'X-CSRF-TOKEN' : token
//		},
//		datatype : 'json',
//		beforeSend : function(xhr, s) {
//			$("#loading").show();
//		},
//		complete : function(xhr) {
//			$("#loading").fadeOut("slow");
//			console.log(xhr)
//			if (xhr.status != 200) {
//				$('#errorModalMessage').html('Something went wrong!');
//				$('#errorModal').modal();
//				return;
//			} else {
//				// $('#successModalMessage').html(xhr.responseText);
//				$('#successModal').modal();
//			}
//		}
//	});
}

function searchByEmailId() {
	$(".closeSearchByEmailId").show();
	var token = $("meta[name='_csrf']").attr("content");
	$("#reportTable tbody").empty();
	$
			.ajax({
				type : 'GET',
				url : '../' + pageType + '/searchByEmailId/'
						+ $("#ipEmailId").val(),
				traditional : true,
				headers : {
					'X-CSRF-TOKEN' : token
				},
				datatype : 'json',
				beforeSend : function(xhr, s) {
					$("#loading").show();
				},
				success : function(data, xhr) {
					console.log(data)
					$("#loading").fadeOut("slow");
					if (data.length === 0) {
						console.log("Empty")
						$('#reportTable').hide();
						$('#errorModalMessage').html('Something went wrong!');
						$('#errorModal').modal();
						return;
					} else {
						$("#hiddenEmailId").val($("#ipEmailId").val());
							
						for (var i = 0; i < data.length; i++) {
							console.log(data[i].reportName)
							var d = data[i].reportName.substring((data[i].reportName.indexOf('_')+1), data[i].reportName.indexOf('-'));
							
							$("#reportTable tbody")
									.append(
											"<tr><td>"
													+ (i + 1)
													+ "</td>"
													+"<td>"+ d + "</td>"
													+ "<td id = '"
													+ data[i].reportPath
													+ "'>"
													+ data[i].reportName
													+ "</td>"
													+ "<td><button class='btn btn-info btn-sm mb-3' onClick='modalCall("
													+ i
													+ ")'><i class='fa fa-list'></i></button></td></tr>");
						}
					}
				},
				error : function(data){
					location.reload();
				}
			});
	$("#reportDiv").show();

}
function modalCall(row) {
	$('#reportModal').modal();
	$("#uploadDocTable tbody").empty();

	var name = document.getElementById('reportBody').rows[row].cells[2].innerHTML;
	var path = document.getElementById('reportBody').rows[row].cells[2].id;
	console.log("path : " + path)

	$("#reportPathId").val(path);

	var token = $("meta[name='_csrf']").attr("content");

	$
			.ajax({
				type : 'GET',
				url : '../' + pageType + '/getUploadData/',
				traditional : true,
				headers : {
					'X-CSRF-TOKEN' : token
				},
				datatype : 'json',
				data : {
					"path" : path
				},
				beforeSend : function(xhr, s) {
					$("#loading").show();
				},
				success : function(data, xhr) {
					console.log("\n\n :: ", data)
					$("#loading").fadeOut("slow");
					if (data === null) {
						$('#errorModalMessage').html('Something went wrong!');
						$('#errorModal').modal();
						return;
					} else {
						for (var i = 0; i < data.length; i++) {
							console.log(data[i].reportName)
							$("#uploadDocTable tbody")
									.append(
											"<tr><td>"
													+ (i + 1)
													+ "</td>"
													+ "<td id = '"
													+ data[i].reportPath
													+ "'>"
													+ data[i].reportName
													+ "</td>"
													+ "<td><button class='btn btn-success btn-sm mb-3' onclick='downloadReport("
													+ (i + 1)
													+ ")'>Download Document</button></td></tr>");
						}
					}
				}
			});

}
function downloadReport(row) {
	var path = null
	if (row === 0)
		path = $("#reportPathId").val();
	else
		path = document.getElementById('uploadDocBody').rows[row - 1].cells[1].id;

	var token = $("meta[name='_csrf']").attr("content");

	$.ajax({
		type : 'GET',
		url : '../' + pageType + '/downloadTextFile/',
		traditional : true,
		headers : {
			'X-CSRF-TOKEN' : token
		},
		datatype : 'json',
		data : {
			"path" : path
		},
		beforeSend : function(xhr, s) {
			$("#loading").show();
		},
		success : function(data, xhr) {
			$("#loading").fadeOut("slow");
			console.log("status : " + xhr)
			if (xhr != "success") {
				$('#errorModalMessage').html('Something went wrong!');
				$('#errorModal').modal();
				return;
			} else {
				if (row === 0) {
					let blob = new Blob([ data ], {
						type : "application/octetstream"
					});

					let a = document.createElement('a');
					a.href = window.URL.createObjectURL(blob);
					//a.href = '../'+pageType+"/downloadData"+
					a.download = "test.txt";
					document.body.appendChild(a);
					a.click();
					a.remove();
					//document.body.removeChild(a);
					//window.URL.revokeObjectURL(a.href);
				}else{
					$('#successModalMessage').html(data);
					$('#successModal').modal();
				}
			}
		}
	});

}

function downloadUploadDoc(row) {
	var name = document.getElementById('reportBody').rows[row].cells[1].innerHTML;
	var path = document.getElementById('reportBody').rows[row].cells[1].id;

	var token = $("meta[name='_csrf']").attr("content");

	$.ajax({
		type : 'GET',
		url : '../' + pageType + '/downloadTextFile/',
		traditional : true,
		headers : {
			'X-CSRF-TOKEN' : token
		},
		datatype : 'json',
		data : {
			"path" : path
		},
		beforeSend : function(xhr, s) {
			$("#loading").show();
		},
		success : function(data, xhr) {
			$("#loading").fadeOut("slow");
			console.log("status : " + xhr)
			if (xhr != "success") {
				$('#errorModalMessage').html('Something went wrong!');
				$('#errorModal').modal();
				return;
			} else {
				var a = document.createElement('a');
				var url = window.URL.createObjectURL(data);
				a.href = url;
				a.download = 'myfile.txt';
				document.body.append(a);
				a.click();
				a.remove();
				window.URL.revokeObjectURL(url);
				// $('#successModalMessage').html(data);
				// $('#successModal').modal();
			}
		}
	});
}

//var emailId = $("#ipEmailId").val();
//var a = document.createElement('a');
//var path = null;
//var url = null;
//if (row === 0){
//	path = $("#reportPathId").val();
//	console.log("path.lastIndexOf('\') : "+path.lastIndexOf('\\'))
//	path = path.substring(path.lastIndexOf('\\')+1);
//	alert(path)
//	url = "../"+pageType+"/downloadTextFile/"+emailId+"/"+path;
//}
//else{
//	path = document.getElementById('uploadDocBody').rows[row - 1].cells[1].id;
//	console.log("2 : path.lastIndexOf('\') : "+path.lastIndexOf('\\'))
//
//	path = path.substring(path.lastIndexOf('\\', path.lastIndexOf('\\')-1)+1);
//	
//	alert(path)
//	url = "../"+pageType+"/downloadTextFile/"+null+"/"+path.substring(0, path.lastIndexOf('\\'))+"-"+path.substring(path.lastIndexOf('\\')+1);
//}
//
//a.href = url;
//// a.download = 'myfile.pdf';
//document.body.append(a);
//a.click();
//a.remove();

$(".closeSearchByEmailId").on('click', function() {
	$("#ipEmailId").val('')
	$("#reportDiv").hide();
	$(".closeSearchByEmailId").hide();
});

$("#showOldEmailSentBtn").on('click', function(){
	$("#showHistoryOfEmailSentModal").modal("show")
})
