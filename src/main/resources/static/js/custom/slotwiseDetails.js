
$(document).ready(function() {
	$('#slotwiseDetailsTable').hide();
	$('#noSlotsMappedDiv').hide();
	$('#addSlotButton').hide();
	$('#editSlotButton').hide();
	$('#slotwiseDetailsCardDiv').hide();
});



function examAppenderFunc() {
	var batchSelected = $("#batchSelect").val()
	if (batchSelected == 0) {
		// remove all the contents of dropdown barring the select
		$('#examSelect').empty()
		var a = ''
		a += "<option value=0>--Select--</option>"
		$('#stateSelect').append(a);
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getExamListByBatchId/',
			data : {
				'batchId' : batchSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#examSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].examId + "'>"
									+ result[i].examName + "</option>"
						}
						$('#examSelect').append(a);
					} else {
						$('#examSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#examSelect').append(a);
					}
				}).fail(function(err) {
					alert("Error in Exam Appender")
		})
	}
}


function regionAppenderFunc() {
	var examSelected = $("#examSelect").val()
	if (examSelected == 0) {
		// remove all the contents of dropdown barring the select
		$('#regionSelect').empty()
		var a = ''
		a += "<option value=0>--Select--</option>"
		$('#regionSelect').append(a);
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getRegionListByExamId/',
			data : {
				'examId' : examSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#regionSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].regionId + "'>"
									+ result[i].regionName + "</option>"
						}
						$('#regionSelect').append(a);
					} else {
						$('#regionSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#regionSelect').append(a);
					}
				}).fail(function(err) {
					alert("Error in Region Appender")
		})
	}
}


function stateAppenderFunc() {
	var regionSelected = $("#regionSelect").val()
	if (regionSelected == 0) {
		// remove all the contents of dropdown barring the select
		$('#stateSelect').empty()
		var a = ''
		a += "<option value=0>--Select--</option>"
		$('#stateSelect').append(a);
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getStateListByRegionId/',
			data : {
				'regionId' : regionSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#stateSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].examStateId + "'>"
									+ result[i].examStateName + "</option>"
						}
						$('#stateSelect').append(a);
					} else {
						$('#stateSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#stateSelect').append(a);
					}
				}).fail(function(err) {
					alert("Error in State Appender")
		})
	}
}


function cityAppenderFunc() {
	var stateSelected = $("#stateSelect").val()
	if (stateSelected == 0) {
		// remove all the contents of dropdown barring the select
		$('#citySelect').empty()
		var a = ''
		a += "<option value=0>--Select--</option>"
		$('#citySelect').append(a);
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getCityListByStateId/',
			data : {
				'stateId' : stateSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#citySelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].examCityId + "'>"
									+ result[i].examCityName + "</option>"
						}
						$('#citySelect').append(a);
					} else {
						$('#citySelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#citySelect').append(a);
					}
				}).fail(function(err) {
					alert("Error in City Appender")
		})
	}
}


function centreAppenderFunc() {
	var citySelected = $("#citySelect").val()
	if (citySelected == 0) {
		// remove all the contents of dropdown barring the select
		$('#centreSelect').empty()
		var a = ''
		a += "<option value=0>--Select--</option>"
		$('#centreSelect').append(a);
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getCentreListByCityId/',
			data : {
				'cityId' : citySelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#centreSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].centreId + "'>"
									+ result[i].centreCode + " - " + result[i].centreName +"</option>"
						}
						$('#centreSelect').append(a);
					} else {
						$('#centreSelect').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#centreSelect').append(a);
					}
				}).fail(function(err) {
					alert("Error in Centre Appender")
		})
	}
}


function centreSelectedFunc(){
	var centreId = $("#centreSelect").val()
	var examSelected = $("#examSelect").val()
	if (centreId == 0 || examSelected == 0) {
		// remove all the contents of dropdown barring the select
		alert("Enter valid values")
		//Write call for hiding the table
		
		return;
	} else {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getAllSlotwiseDetailsByCentre/',
			data : {
				'centreId' : centreId,
				'examId' : examSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						
						 $(function() {
							    $('#slotStatus').bootstrapToggle();
							  })
						$('#tbodyOfTable').empty();
						$('#slotwiseDetailsTable').hide();	
						var a = '';						
						if(result.examCentreMappingList != null){
							for (i = 0; i < result.examSlotList.length; i++) {
								var mismatchCount = 0;
								a+='<tr>';
									a+='<td>';
										a+='<input type="radio" name="slotRadio" id="slotRadio'+result.examSlotList[i].examSlotId+'" value="'+result.examSlotList[i].examSlotId+'"></td>';
									a+='</td>';
									a+='<td>';
										a+='<label id="slotSelectedId'+result.examSlotList[i].examSlotId+'">'+result.examSlotList[i].examSlotCode+'</label> ';
									a+='</td>';
									for (j = 0; j < postCombination.length; j++) {	
										var checkIfPostHasValue = 0;
										for (b = 0; b < result.examCentreMappingList.length; b++) {										
											if(result.examCentreMappingList[b].icgPostCombination.icgPostCombinationId == postCombination[j].icgPostCombinationId 
												&& result.examSlotList[i].examSlotId == result.examCentreMappingList[b].centreExamslotMapping.examSlot.examSlotId){
												checkIfPostHasValue=1;
											a+='<td>';
												a+='<label>'+result.examCentreMappingList[b].totalSeatAllocated+'</label> ';		
												mismatchCount+=result.examCentreMappingList[b].totalSeatAllocated;
											a+='</td>';
											}
										}
										if(checkIfPostHasValue != 1){
											a+='<td>';
												a+='<label>N.A.</label> ';		
												mismatchCount+=0;
											a+='</td>';
										}
									}				
									var maxCapFlag = 0;
									for (k = 0; k < result.examCentreMappingList.length; k++) {
										if(result.examCentreMappingList[k].centreExamslotMapping.examSlot.examSlotId == result.examSlotList[i].examSlotId){
											a+='<td>';
												a+='<label>'+result.examCentreMappingList[k].centreExamslotMapping.centreExamslotTotalCapacity+'</label> ';
											a+='</td>';
											maxCapFlag =1;
											break;
										}
									}	
									if(maxCapFlag == 0){
										a+='<td>';
											a+='<label>N.A.</label> ';
										a+='</td>';
									}
									a+='<td>';
										a+='<label>'+parseInt(mismatchCount)+'</label>';
									a+='</td>';
									a+='<td>';
									var statusCapFlag = 0;
									for (k = 0; k < result.examCentreMappingList.length; k++) {
										if(result.examCentreMappingList[k].centreExamslotMapping.examSlot.examSlotId == result.examSlotList[i].examSlotId){
											if(result.examCentreMappingList[k].centreExamslotMapping.centreExamslotStatus == true){
												a+='<label id="statusOfCentre'+result.examCentreMappingList[k].centreExamslotMapping.examSlot.examSlotId+'">Active</label> ';
												statusCapFlag =1;
											}
											else{
												statusCapFlag =1;
												a+='<label id="statusOfCentre'+result.examCentreMappingList[k].centreExamslotMapping.examSlot.examSlotId+'">InActive</label> ';
											}
											break;
										}
									}
									if(statusCapFlag == 0){
										a+='<label>N.A.</label> ';
									}
									a+='</td>';
								a+='</tr>';
							}
							$("#tbodyOfTable").append(a);
							$('#editSlotButton').show();
							$('#addSlotButton').hide();
							$('#noSlotsMappedDiv').hide();
							$('#slotwiseDetailsTable').show();
							$('#slotwiseDetailsCardDiv').show();
						}
						else{
							$('#noSlotsMappedDiv').show();							
							$('#addSlotButton').show();
							$('#editSlotButton').hide();
							$('#slotwiseDetailsCardDiv').show();
						}					
					} else {
						$('#slotwiseDetailsTable').hide();
						$('#noSlotsMappedDiv').hide();	
						$('#addSlotButton').hide();
						$('#editSlotButton').hide();
						$('#slotwiseDetailsCardDiv').hide();
						alert("Null Slots found")
						
					}
				}).fail(function(err) {
					$('#slotwiseDetailsTable').hide();
					$('#noSlotsMappedDiv').hide();	
					$('#addSlotButton').hide();
					$('#editSlotButton').hide();
					$('#slotwiseDetailsCardDiv').hide();
					alert("Error in centreSelectedFunc")
		})
	}
}

/**
 * This function is to open the modal window of the add new slot, the ajax
 * is to add number of slots for the exam cycle present
 * @returns
 */
function addNewSlotFunc(){
	var examSelected = $("#examSelect").val()
	$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getSlotsByExamId/',
			data : {
				'examId' : examSelected
			},
		}).done(
				function(result) {
					// write code here for appending
					if (result != null) {
						$('#slotSelectorForAddNewSlot').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						for (i = 0; i < result.length; i++) {
							a += "<option value='" + result[i].examSlotId + "'>"
									+ result[i].examSlotCode +"</option>"
						}
						$('#slotSelectorForAddNewSlot').append(a);
					} else {
						$('#slotSelectorForAddNewSlot').empty()
						var a = ''
						a += "<option value=0>--Select--</option>"
						$('#slotSelectorForAddNewSlot').append(a);
					}
				}).fail(function(err) {
					alert("Error in addNewSlotFunc")
		})
	
	
	$('#addSlotModalPopover').modal()
}

/**
 * This function is to save the new slots in the db corresponding to the centre
 * If the centre does not have any slot mappings , we can add them through this function
 * @returns
 */
function saveNewSlotInDbFunc(){
	event.preventDefault();
	var examId = $("#examSelect").val()
	var centreId = $("#centreSelect").val()
	var slotId = $("#slotSelectorForAddNewSlot").val()
	var maxCapacity = $("#maxCapacityAddSlot").val()
	var statusForCentreSlotAdd = false;
	if($('input[name=slotStatusAdd]:checked').length > 0)
		statusForCentreSlotAdd = true;
	else
		statusForCentreSlotAdd = false;
	var token = $("meta[name='_csrf']").attr("content");
	if(slotId == 0 || centreId == 0 || maxCapacity == ""){
		alert("Enter all values")
		return;
	}
	var postCombinationArray = [];
	$("input:checkbox[name=cbBox]:checked").each(function() {
		postCombinationArray.push($(this).val());
	});
	if (postCombinationArray.length == 0) {
		alert("Select atleast one Post Combination")
		return;
	} 
	//write ajax here
	$.ajax({
		type : 'POST',
		/*contentType : 'application/json',*/
		contentType : "application/json;charset=utf-8",
		url : 'saveNewSlotInDb',
		async : false,
		headers : {
			'X-CSRF-TOKEN' : token
		},
		data : JSON.stringify({
			examId : examId,
			centreId : centreId,
			slotId : slotId,
			maximumCapacity : maxCapacity,
			statusForCentreSlot : statusForCentreSlotAdd,
			listOfIcgPostCombinations : postCombinationArray.toString()
		}),
	}).done(function(result) {
		if (result == "Success") {
			alert("New Slot Successfully Added")
			$("input:checkbox[name=cbBox]:checked").each(function() {
				$(this).prop('checked', false);
			});
			$('#addSlotModalPopover').modal('toggle');
			$("#centreSelect").val(centreId).change();
			//location.reload(true)
		} else {
			alert("Please try again!")
			//location.reload(true);
		}
	}).fail(function(err) {
		alert(err.responseText)
		//alert("Please try again after some time!")
	})
	
}

function editSlotFunc(){
	event.preventDefault();
	var id = $("input[name='slotRadio']:checked").val();
	if(id){
		var centreId = $("#centreSelect").val()
		var examSelected = $("#examSelect").val()
		if (centreId == 0 || examSelected == 0) {
			// remove all the contents of dropdown barring the select
			alert("Enter valid values")
			//Write call for hiding the table
			
			return;
		}else{
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : 'getEditDetailsByCentreExamSlot/',
				data : {
					'centreId' : centreId,
					'examId' : examSelected,
					'slotId' : parseInt(id),
				},
			}).done(
					function(result) {
						$("input:checkbox[name=cbEditBox]:checked").each(function() {
							$(this).prop('checked', false);
						});
						if (result != null) {
							//$("#tbodyOfEditToAppend").empty();
							
							if(result.centreExamSlotMappingList == ""){
								$("#slotForCentreEdit").val($("#slotSelectedId"+id).text())
								$("#maxCapacityForCentreEdit").val(0)
								$('#slotStatus').bootstrapToggle('off');
							}
							else{
								$("#slotForCentreEdit").val(result.centreExamSlotMappingList[0].examSlot.examSlotCode)
								$("#maxCapacityForCentreEdit").val(result.centreExamSlotMappingList[0].centreExamslotTotalCapacity)	
								if(result.centreExamSlotMappingList[0].centreExamslotStatus == null || result.centreExamSlotMappingList[0].centreExamslotStatus == false)
									$('#slotStatus').bootstrapToggle('off');
								else
									$('#slotStatus').bootstrapToggle('on');
							}	
							var totalAllocatedToFill = 0;
							for (i = 0; i < postCombination.length; i++) {
								var flagToCheckSeatsAllocated = 0;
								if(result.examCentreMappingList != null){
									for (j = 0; j < result.examCentreMappingList.length; j++) {	
										if(result.examCentreMappingList[j].icgPostCombination.icgPostCombinationId == postCombination[i].icgPostCombinationId){	
											$("input[type=checkbox][value="+postCombination[i].icgPostCombinationId+"]").prop("checked",true);	
											$("#tdToFill"+postCombination[i].icgPostCombinationId).html(result.examCentreMappingList[j].totalSeatAllocated)
												totalAllocatedToFill+=result.examCentreMappingList[j].totalSeatAllocated;
												flagToCheckSeatsAllocated = 1;
										}
									}
									if(flagToCheckSeatsAllocated == 0){
										$("#tdToFill"+postCombination[i].icgPostCombinationId).html('-')
									}
									$("#allocatedForCentreEdit").val(totalAllocatedToFill)
								}	
								else{
									$("#tdToFill"+postCombination[i].icgPostCombinationId).html('-')
									$("#allocatedForCentreEdit").val(0)
								}
							}	
							
							//Code to hide the post combinations that are not available in allocation criteria for this slot
							 if(allocationCriteriaList != null){
								  for(var j=0; j< postCombination.length; j++){
									  $("#trToHideEdit"+postCombination[j].icgPostCombinationId).css({"visibility": "collapse"});
								  }
								  for (var i = 0; i < allocationCriteriaList.length; i++) {
									  if(allocationCriteriaList[i].examSlot.examMaster.examId == examSelected){
										  if(allocationCriteriaList[i].examSlot.examSlotId == id){
											  $("#trToHideEdit"+allocationCriteriaList[i].icgPostCombination.icgPostCombinationId).css({"visibility": "visible"});
										  }
									  }
								  }
							  }
							 else{
								 alert("allocationCriteriaList is Empty")
							 }
							
							
						} else {
							alert("null list returned")
							return;
						}
					}).fail(function(err) {
						alert("Error in editSlotFunc")	
						return;
		})		
		
	}
	}else{
		alert("Select a slot to edit");
		return;
	}
	
	$('#editSlotModalPopover').modal()
}

function editInDbFunc(){
	event.preventDefault();
	var slotId = $("input[name='slotRadio']:checked").val().trim();
	var maxCapacityToSend = $("#maxCapacityForCentreEdit").val();
	
	var statusForCentreSlot = false;
	if($('input[name=slotStatus]:checked').length > 0)
		statusForCentreSlot = true;
	else
		statusForCentreSlot = false;
	 
	var postCombinationsSelected = [];
	$("input:checkbox[name=cbEditBox]:checked").each(function() {
		postCombinationsSelected.push($(this).val());
	});
	var centreId = $("#centreSelect").val()
	
	if(slotId == 0){
		alert("No Slot selected");
		return;
	}
	if(maxCapacityToSend == 0){
		alert("Maximum Capacity cannot be empty")
		return;
	}
	var examId = $("#examSelect").val()
	var token = $("meta[name='_csrf']").attr("content");
	$.ajax({
		type : 'POST',
		contentType : 'application/json',		
		url : 'editSlotInDb',
		async : false,
		headers : {
			'X-CSRF-TOKEN' : token
		},
		data : JSON.stringify({
			examId : examId,
			centreId : centreId,
			slotId : slotId,
			maximumCapacity : maxCapacityToSend,
			statusForCentreSlot : statusForCentreSlot,
			listOfIcgPostCombinations : postCombinationsSelected.toString()
		}),
	}).done(function(result) {
		if (result == "Success") {
			alert("Details updated succesfully")
			$("input:checkbox[name=cbEditBox]:checked").each(function() {
				$(this).prop('checked', false);
			});
			$('#editSlotModalPopover').modal('toggle');
			$("#centreSelect").val(centreId).change();
			//location.reload(true)
		} else {
			alert("Please try again!")
			return;
			//location.reload(true);
		}
	}).fail(function(err) {
		alert(err.responseText)
		return;
		//alert("Please try again after some time!")
	})	
}

//To Hide the TR of the selected slot in Model window for Add Slot
$('#slotSelectorForAddNewSlot').on('change', function() {
	  var examSelected = $("#examSelect").val()
	  if(this.value !=0 ){
		  if(allocationCriteriaList != null){
			  for(var j=0; j< postCombination.length; j++){
				  $("#trToHideAdd"+postCombination[j].icgPostCombinationId).css({"visibility": "collapse", "padding": "15px"});
			  }
			  $("input:checkbox[name=cbBox]:checked").each(function() {
					$(this).prop('checked', false);
				});
			  for (var i = 0; i < allocationCriteriaList.length; i++) {
				  if(allocationCriteriaList[i].examSlot.examMaster.examId == examSelected){
					  if(allocationCriteriaList[i].examSlot.examSlotId == this.value){
						  $("#trToHideAdd"+allocationCriteriaList[i].icgPostCombination.icgPostCombinationId).css({"visibility": "visible", "padding": "15px"});
					  }
				  }
			  }
		  }
		  else
			  alert("allocationCriteriaList is Empty")
	  }
	});