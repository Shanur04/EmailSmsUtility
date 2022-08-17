var sentEmailType = ""
	
$(document)
		.ready(function(e) {
					
					$("#scheduleBlock").hide();
					$("#quickScheduleBtn").hide();
					
					$("#loading").hide();
					
					$("#mailReason").on("change", function(){
						var name = ($(this).val()).replace(/[a-zA-Z0-9 ]/g, "");
						if(name != ""){
							alert("Mail Reason can not contain any special character!")
						}
					});

					$("#fileuploader").uploadFile({
						url: "../"+$("#pageType").val()+"/upload",
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
								    url: "../"+$("#pageType").val()+"/load",
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
					function setTheme() {
						theme = $.trim($("#themes").val());
						font_size = $.trim($("#font_size").val());

						editorAce1.setTheme("ace/theme/" + theme);
						$("#code1").css({
							"font-size" : (font_size + "px")
						});
						editorAce1.setOptions({
							enableBasicAutocompletion : true,
							enableSnippets : true,
							enableLiveAutocompletion : true
						});
					}

					function show_msg(msg, type) {
						if (type == "info") {
							$("#msg").html(
									'<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>&nbsp;'
											+ msg).removeClass("text-danger")
									.addClass("text-info");
							$("#msg_modal").modal({
								backdrop : false
							});
						} else if (type == "error") {
							$("#msg")
									.html(
											'<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>&nbsp;'
													+ msg).removeClass(
											"text-info")
									.addClass("text-danger");
							$("#msg_modal").modal({
								backdrop : false
							});
						}
					}

					ace.require("ace/ext/language_tools");
					var editorAce1 = ace.edit("code1");
					editorAce1.focus();
					editorAce1.setOptions({
						enableBasicAutocompletion : true,
						enableSnippets : true,
						enableLiveAutocompletion : true
					});
					editorAce1.setTheme("ace/theme/chrome");
					editorAce1.getSession().setMode("ace/mode/json");

					$("#clear").click(function(e) {
						e.preventDefault();
						editorAce1.setValue("");
					});

					$("#code1").resizable({
						handles : 's',
						resize : function(event, ui) {
							editorAce1.resize();
						}
					});

					$(window).resize(function(e) {
						$("#code1").css({
							"width" : "100%"
						});
					});

					$("#browse").click(function(e) {
						e.preventDefault();
						$("#file").click();
					});

					$("#download").click(function(e) {
						e.preventDefault();
						data = $.trim(editorAce1.getValue());
						if (data == "") {
							show_msg("The editor is empty!", "error");
							return false;
						}
						blob = new Blob([ "" + data + "" ], {
							type : "text/plain;charset=utf-8"
						});
						saveAs(blob, "data.txt");
					});

					$("#beautify_json").click(function(e) {
						e.preventDefault();
						data = $.trim(editorAce1.getValue());
						if (data != "") {
							try {
								editorAce1.setValue(vkbeautify.json(data, 4));
							} catch (e) {
							}
						}
					});

					themelist = ace.require("ace/ext/themelist");
					theme = "";
					$(themelist.themes).each(
							function(key, value) {
								theme += '<option value="' + value.name + '">'
										+ value.caption + '</option>';
							});
					$("#themes").append(theme);

					$("#themes").val("chrome");
					$("#font_size").val("14");

					$("#themes,#font_size").change(function(e) {
						setTheme();
						editorAce1.focus();
					});

					function to_json(workbook) {
						var result = {};
						workbook.SheetNames
								.forEach(function(sheetName) {
									// alert(sheetName);
									/*
									 * if(sheetName != 'Sheet1') { alert("Sheet
									 * Name should be Sheet1");
									 * location.reload(); }
									 */
									var roa = XLSX.utils
											.sheet_to_row_object_array(workbook.Sheets[sheetName]);
									if (roa.length > 0) {
										result[sheetName] = roa;
									}
								});
						return result;
					}

					function handleFile(e) {
						var files = e.target.files;
						var i, f;
						for (i = 0, f = files[i]; i != files.length; ++i) {
							var reader = new FileReader();
							var name = f.name;
							reader.onload = function(e) {
								try {
									var data = $.trim(e.target.result);
									var workbook = XLSX.read(data, {
										type : 'binary'
									});
									editorAce1.getSession().setUseWorker(true);
									editorAce1.setValue(vkbeautify.json(
											to_json(workbook), 4));
									var final_data = vkbeautify.json(
											to_json(workbook), 4);
									builddata(final_data);
								} catch (e) {
									if (name.split(".").pop() == "csv") {
										editorAce1.setValue(vkbeautify.json(
												CSV2JSON(data), 4));
									} else {
										editorAce1.getSession().setUseWorker(
												false);
										editorAce1
												.setValue("Please select an excel file.");
									}
								}
							};
							reader.readAsBinaryString(f);
						}
					}

					$("#file")
							.change(
									function(e) {

										// alert("Haiiai");
										e.preventDefault();
										editorAce1
												.setValue("Please wait while loading your file.");
										var ext = $
												.trim($("#file").val().split(
														'.').pop()
														.toLowerCase());
										if ($.trim($("#file").val()) == "") {
											editorAce1.getSession()
													.setUseWorker(false);
											alert("Please upload valid excel file first");
											location.reload();
										} else if ($.inArray(ext, [ 'xlsx' ]) == -1) {
											editorAce1.getSession()
													.setUseWorker(false);
											alert("Please upload valid excel (xlsx) file");
											location.reload();
											// $("#file").change();
										}
										handleFile(e);
									});

					$("#convert")
							.click(
									function(e) {
										e.preventDefault();
										var ext = $('#my_file_field').val()
												.split('.').pop().toLowerCase();
										if ($.trim($("#file").val()) == "") {
											editorAce1.getSession()
													.setUseWorker(false);
											alert("Please upload valid excel file first");
										} else if ($.inArray(ext, [ 'xlsx' ]) == -1)
											editorAce1.getSession().setUseWorker(false);
										alert("Please upload valid excel (xlsx) file");
										{
											$("#file").change();
										}
									});

					$(document).on('click', '.btnSendBulkEmailWithExcel', function(e) {
										$("#loading").show();
										// Your Code
										e.preventDefault();
										data = $.trim(editorAce1.getValue());
										if (data == "Please select an excel file.") {
											// show_msg("The editor is empty
											// resubmit!", "error");
											$("#errorModalMessage").html(
													'Excel File Is Empty');
											$('#errorModal').modal("show");
											return false;
										}


										var obj = JSON.parse(data);
										obj = trimArrAndObj(obj);
										for ( var key in obj) {
											if (!Object.prototype.hasOwnProperty.call(obj, key))
												continue;
											obj = obj[key];
											break;
										}

										var error_msg = "";


										for (var i = 0; i < obj.length; i++) {
											$.each(obj[i],function(key, value) {
												value = escapeHtml(value);
											});
										}
										// alert(error_msg);
										if (error_msg) {
											alert(error_msg);
											location.reload();
											return false;
										}

										var emailContent = $('#emailContent')
												.val();
										var emailSubject = $('#mailSubject')
												.val();

										var mailReason = $('#mailReason').val();
										var emailServertype = $(
												"input[name='emailServerTypeRadio']:checked")
												.val();
										if (emailServertype == "smtp") {
											var mailServerHost = "smtp.cdac.in";
											var mailServerPort = "587";
											var starttls = true;
											var socketFactoryPort = "587";
										} else if (emailServertype == "smtpb") {
											var mailServerHost = "smptb.pune.cdac.in";
											var mailServerPort = "25";
											var starttls = false;
											var socketFactoryPort = "25";
										} else if (emailServertype == "smtpdr") {
											var mailServerHost = "smtp-dr.cdac.in";
											var mailServerPort = "25";
											var starttls = false;
											var socketFactoryPort = "25";
										} else {
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
										
										var isAppCredIdList = $('input[type="radio"][name="isAppCredIdList"]:checked').val();

										var jsonObj = {
											obj : obj,
											emailContent : emailContent,
											emailSubject : emailSubject,
											mailServerHost : mailServerHost,
											mailServerPort : mailServerPort,
											starttls : starttls,
											socketFactoryPort : socketFactoryPort,
											mailReason : mailReason,
											sentType : sentEmailType,
											scheduleList : str,
											isAppCredIdList : isAppCredIdList
										};

										$.ajax({
											type : 'POST',
											processData : false,
											contentType : "application/json",
											async : false,
											url : '../'+$("#pageType").val()+'/sendBulkTestEmails',
											data : JSON.stringify(jsonObj)
										})
										.done(function(data) {
											if (data) {
												$('.table-responsive,#file,span.error').hide();
												$('.info').html('<div class="alert alert-success"><p> <span class="glyphicon glyphicon-ok"></span> <strong>Email Sent successfully to above email Ids</strong><br /></p></div><p><a class="btn btn-primary"href="bulkUploadCI">Click here to upload again</a>');
											}
											$("#loading").fadeOut("slow");
											$("#successModalMessage").html("Email Sending Completed!");
											// $("#successModalOk").attr("href",
											// "showDashBoard");
											$('#successModal').modal("show");
											setTimeout(function() {
												location.reload();
											}, 6000);

										})
										.fail(
											function(xhr, err) {

												var errData = "<br />";
												var data = xhr.responseText;
												if (data) {
													var jsonResponse = JSON.parse(data);
													var count = Object.keys(jsonResponse).length;

													if (count) {
														$.each(jsonResponse,function(key,value) {
															errData += value+ '<br />';
														});
													} else {
														errData += jsonResponse + '<br />';
													}
												}
												$('.table-responsive,#file,span.error').hide();
												$('.error').html('<div class="alert alert-danger"><p> <span class="glyphicon glyphicon-exclamation-sign"></span> <strong>Error in the uploaded File </strong>'
																					+ errData
																					+ '<br /></p></div><p><a class="btn btn-primary" href="bulkUploadCI">Click here to upload again</a>');

												$('#loading').html("").hide();
												
										});

									});

					$(document).on('click',	"#btnSendBulkEmailWithExcelForRejected",
						function(e) {
						$("#loading").show();
										// $(this).hide();
						e.preventDefault();
										
						var emailContent = $('#emailContent').val();
						var emailSubject = $('#mailSubject').val();

						var mailReason = $('#mailReason').val();
						var emailServertype = $("input[name='emailServerTypeRadio']:checked").val();
												
						var postRadio = $("input[name='postRadio']:checked").val();
						if (emailSubject == "") {
							$("#errorModalMessage").html("No Email Subject Found!");
							$('#errorModal').modal("show");
							$("#loading").fadeOut("slow");
							return;
						}
						if (emailContent == "") {
							$("#errorModalMessage").html("No Email Content Found!");
							$('#errorModal').modal("show");
							$("#loading").fadeOut("slow");
							return;
						}
										
						if(postRadio== ""){
							$("#errorModalMessage").html("Select Radio Button!");
							$('#errorModal').modal("show");
							$("#loading").fadeOut("slow");
							return;
						}

						if (emailServertype == "smtp") {
							var mailServerHost = "smtp.cdac.in";
							var mailServerPort = "587";
							var starttls = true;
							var socketFactoryPort = "587";
						} else if (emailServertype == "smtpb") {
											var mailServerHost = "smtpb.pune.cdac.in";
											var mailServerPort = "25";
											var starttls = false;
											var socketFactoryPort = "25";
										} else if (emailServertype == "smtpdr") {
											var mailServerHost = "smtp-dr.cdac.in";
											var mailServerPort = "25";
											var starttls = false;
											var socketFactoryPort = "25";
										} else {
											var mailServerHost = null;
											var mailServerPort = null;
											var starttls = false;
											var socketFactoryPort = null;
										}

										var jsonObj = {
											// obj : obj,
											emailContent : emailContent,
											emailSubject : emailSubject,
											mailServerHost : mailServerHost,
											mailServerPort : mailServerPort,
											starttls : starttls,
											socketFactoryPort : socketFactoryPort,
											mailReason : mailReason,
											postRadio:postRadio
										};

										console.log(jsonObj)
										$
												.ajax(
														{
															type : 'POST',
															processData : false,
															contentType : "application/json",
															async : false,
															url : 'sendBulkEmailWithExcelForRejected',
															data : JSON
																	.stringify(jsonObj)
														})
												.done(
														function(data) {
															if (data) {
																$(
																		'.table-responsive,#file,span.error')
																		.hide();
															}
															$("#loading")
																	.fadeOut(
																			"slow");
															$(
																	"#successModalMessage")
																	.html(
																			"Email Sending Completed!");
															// $("#successModalOk").attr("href",
															// "showDashBoard");
															$('#successModal')
																	.modal(
																			"show");
															// setTimeout(
															// function() {
															// location
															// .reload();
															// }, 10000);

														})
												.fail(
														function(xhr, err) {
															var errData = "<br/>";
															var data = xhr.responseText;
															console.log(data)
															$(
																	"#errorModalMessage")
																	.html(data);
															$('#errorModal')
																	.modal(
																			"show");

															$('#loading').html(
																	"").hide();
														});

										console.log(jsonObj)
										$('#loading').html("").hide();

									})

					function setcityDetail(id) {
						for (var i = 0; i < jsonObj.length; i++) {
							if (jsonObj[i].Id === id) {
								jsonObj[i].cityDetail = "Hello";
								return;
							}
						}
					}

					function getCity(cityId) {
						alert(cityId);
					}
					function getState(stateId) {
						alert(stateId);
					}

					function Unix_timestamp(timestamp) {
						console.log("TSatamp " + timestamp);
						var d = new Date(timestamp * 1000), // Convert the
						// passed timestamp
						// to milliseconds
						yyyy = d.getFullYear(), mm = ('0' + (d.getMonth() + 1))
								.slice(-2), // Months are zero based. Add
						// leading 0.
						dd = ('0' + d.getDate()).slice(-2), // Add leading 0.
						hh = d.getHours(), h = hh, min = ('0' + d.getMinutes())
								.slice(-2), // Add leading 0.
						ampm = 'AM', time;

						if (hh > 12) {
							h = hh - 12;
							ampm = 'PM';
						} else if (hh === 12) {
							h = 12;
							ampm = 'PM';
						} else if (hh == 0) {
							h = 12;
						}

						// ie: 2013-02-18, 8:35 AM
						// time = yyyy + '-' + mm + '-' + dd + ', ' + h + ':' +
						// min + ' ' + ampm;

						time = dd + '-' + mm + '-' + yyyy;

						return time;
					}

					function escapeHtml(unsafe) {
						return unsafe.replace(/&/g, "&amp;").replace(/</g,
								"&lt;").replace(/>/g, "&gt;").replace(/"/g,
								"&quot;").replace(/'/g, "&#039;");
					}

					function trimArrAndObj(obj) {
						if (!Array.isArray(obj) && typeof obj != 'object')
							return obj;
						return Object
								.keys(obj)
								.reduce(
										function(acc, key) {
											acc[key.trim()] = typeof obj[key] == 'string' ? obj[key]
													.trim()
													: trimArrAndObj(obj[key]);
											return acc;
										}, Array.isArray(obj) ? [] : {});
					}
					function builddata(JSON_DATA) {
						var obj = $.parseJSON(JSON_DATA);
						console.log(obj.Sheet1.length);
						console.log(obj.Sheet1);
						$('#example').DataTable({
							"aaData" : obj.Sheet1,
							"aoColumns" : [
							// { "sTitle": "Name", "mData": "name" },
							// { "sTitle": "Contact Number", "mData":
							// "contact_number" },
							{
								"sTitle" : "Email ID",
								"mData" : "email_id"
							}
							// { "sTitle": "User Description", "mData":
							// "userDesc" },
							// { "sTitle": "Address", "mData": "address" },
							// { "sTitle": "Designation", "mData": "designation"
							// },
							// { "sTitle": "Centre Code", "mData": "centre_code"
							// }
							]
						});

						if (obj.Sheet1.length) {
							// $('#submit_data').html('<br /><input
							// type="button" class="btn btn-primary"
							// value="Submit" id="SubmitData"></input>');
							// $('#loading').html("").hide();
						}

					}
					// centerName centerAddress centerAlias centerCapacity
					// centerCode
					// centerContactEmailid centerContactNumber
					// centerContactPerson
					// centerGoogleMapurl centerLandmark centerPincode
					// centerStatus cityDetail
					// stateDetail

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
									+ "</td><td class='col-sm-1'><input type='date' value='"
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