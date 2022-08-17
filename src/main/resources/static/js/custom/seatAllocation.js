$(document).ready(function () {
$("#loading").hide();
$("#citywiseAllocation").removeAttr("hidden");
$("#interCitywiseAllocation").attr('hidden', 'true');
$("#candidateWiseAllocation").attr('hidden', 'true');	
$("#singleCandidateAllocation").attr('hidden', 'true');
$("#candidateWiseFirstDayAllocation").attr('hidden', 'true');

/*Multi Select list of exam cities for sameCityAllocation */
 $(".multilist").multiselect({
			  templates: {
			        	        filter: '<li class="multiselect-item multiselect-filter"><div class="input-group mb-3"><div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search"></i></span></div><input class="form-control multiselect-search" type="text" /></div></li>',
			        	        filterClearBtn: '<button type="reset" class="btn btn-default"><i class="fa fa-times-circle"></i></button>'
			        	  },
			        	  includeSelectAllOption: true,			             		            
			              enableFiltering: true,
			              maxHeight: 300,
			              disableIfEmpty: true,
			              disabledText: '-------- Select centres from the list ---------',
			              buttonWidth: '756px',
			              buttonClass: 'btn btn-primary',
			              inheritClass: true, 
			              filterBehavior: 'both',
			              enableCaseInsensitiveFiltering: true,
			              buttonText: function(options, select) {
			               if (options.length === 0) {
			                         return 'No Exam City selected ...';
			               }
			               else if (options.length > 8) {
			                         return 'More than 8 exam cities selected!';
			               }
			               else {
			                     var labels = [];
			                      options.each(function() {
			                         if ($(this).attr('label') !== undefined) {
			                            labels.push($(this).attr('label'));
			                         }
			                         else {
			                                  labels.push($(this).text());
			                         }
			                });
			                return labels.join(', ') + '';
			                }
			                 },			                 
			                 optionLabel: function(element) {
			                     return $(element).html() ;
			                 }			              
		});	 
});


$(document).on('click', '#samecityAlloc', function() {
	if($('#sameCityPref').val() === null){
		$("#errorModalMessage").html('Please choose allocation preference!');
		$('#errorModal').modal("show");
	}else if($('#multiExamCityList').val().length == 0){
		$("#errorModalMessage").html('Please choose exam city for allocation!');
		$('#errorModal').modal("show");
	}else {
		var  isOverlapAllowed = false;
		if ($('#sameCityOverlap').is(":checked")) {
 			isOverlapAllowed = true;
  		}
		var sameCityData = {"cityPref" : $('#sameCityPref').val(),
		"sameCityList":$('#multiExamCityList').val(),"isOverlapAllowed":isOverlapAllowed};
		sendAjaxRequest(sameCityData,"cityWiseAllocation");
	}
});

$(document).on('click', '#intercityAlloc', function() {
	if($('#interCityPref').val() === null){
		$("#errorModalMessage").html('Please choose allocation preference!');
		$('#errorModal').modal("show");
	}else if($('#candidateInterCity').val().length == 0){
		$("#errorModalMessage").html('Please choose candidate city for allocation!');
		$('#errorModal').modal("show");
	}else if($('#movedInterCity').val().length == 0){
		$("#errorModalMessage").html('Please choose moved city for allocation!');
		$('#errorModal').modal("show");
	}else {
		var interCityData = {"cityPref" : $('#interCityPref').val(),"candidateCity":$('#candidateInterCity').val(),
		"movedCity":$('#movedInterCity').val()}
		sendAjaxRequest(interCityData,"interCityWiseAllocation");
	}
});

$(document).on('click', '#singleCandidateAllocButton', function() {
	if($('#emailId').val() === null){
		$("#errorModalMessage").html('Please enter email ID!');
		$('#errorModal').modal("show");
	}else if($('#singleCandidateCity').val().length == 0){
		$("#errorModalMessage").html('Please choose city for allocation!');
		$('#errorModal').modal("show");
	}else if($('#singleCandidateSlot').val().length == 0){
		$("#errorModalMessage").html('Please choose slot for allocation!');
		$('#errorModal').modal("show");
	}else {
		var candidateInfo = {"emailId" : $('#emailId').val(),"singleCandidateCity":$('#singleCandidateCity').val(),
		"singleCandidateSlot":$('#singleCandidateSlot').val()}
		sendAjaxRequest(interCityData,"interCityWiseAllocation");
	}
});



$(document).on('click', '#AllCandidateWiseAlloc', function() {
	 $.ajax({
			type : 'GET',
			url : "candidateWiseAllocation/1",
			contentType : "application/json;charset=utf-8",
			success : function(data, $xhr, text) {
				$("#successModalMessage").html(text.responseText);
				$('#successModal').modal("show");
			},
			error : function($xhr, textStatus, errorThrown) {
				$("#errorModalMessage").html("Something went wrong while allocation");
				$('#errorModal').modal("show");				
			}
		});
});

$(document).on('click', '#candidateWiseFirstDayAllocBTN', function() {
	$("#loading").show();
	 $.ajax({
			type : 'GET',
			url : "candidateWiseAllocation/2",
			contentType : "application/json;charset=utf-8",
			success : function(data, $xhr, text) {
				$("#loading").hide();
				$("#successModalMessage").html(text.responseText);
				$('#successModal').modal("show");
			},
			error : function($xhr, textStatus, errorThrown) {
				$("#loading").hide();
				$("#errorModalMessage").html("Something went wrong while allocation");
				$('#errorModal').modal("show");				
			}
		});
});



$(document)
.on('click','#sameCityAllocList',function() {			
			$("#citywiseAllocation").removeAttr("hidden");
			$("#interCitywiseAllocation").attr('hidden', 'true');
			$("#candidateWiseAllocation").attr('hidden', 'true');
			$("#singleCandidateAllocation").attr('hidden', 'true');
			$("#candidateWiseFirstDayAllocation").attr('hidden', 'true');	
});

$(document)
.on('click','#interCityAlloc',function() {			
			$("#citywiseAllocation").attr('hidden', 'true');
			$("#interCitywiseAllocation").removeAttr("hidden");
			$("#candidateWiseAllocation").attr('hidden', 'true');	
			$("#singleCandidateAllocation").attr('hidden', 'true');	
			$("#candidateWiseFirstDayAllocation").attr('hidden', 'true');	
});

$(document)
.on('click','#candidateWiseAlloc',function() {			
			$("#citywiseAllocation").attr('hidden', 'true');
			$("#interCitywiseAllocation").attr('hidden', 'true');
			$("#candidateWiseAllocation").removeAttr("hidden");
			$("#singleCandidateAllocation").attr('hidden', 'true');
			$("#candidateWiseFirstDayAllocation").attr('hidden', 'true');	
});

$(document)
.on('click','#singleCandidateAlloc',function() {			
			$("#citywiseAllocation").attr('hidden', 'true');
			$("#interCitywiseAllocation").attr('hidden', 'true');
			$("#candidateWiseAllocation").attr('hidden', 'true');
			$("#singleCandidateAllocation").removeAttr("hidden");
			$("#candidateWiseFirstDayAllocation").attr('hidden', 'true');	
});

$(document)
.on('click','#candidateWiseFirstDayAlloc',function() {			
			$("#citywiseAllocation").attr('hidden', 'true');
			$("#interCitywiseAllocation").attr('hidden', 'true');
			$("#candidateWiseAllocation").attr('hidden', 'true');
			$("#singleCandidateAllocation").attr('hidden', 'true');
			$("#candidateWiseFirstDayAllocation").removeAttr("hidden");
			
});

function sendAjaxRequest(requestData, requestedUrl) {
   $.ajax({
			type : 'POST',
			url : requestedUrl,
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(requestData),
			success : function(data, $xhr, text) {
				$("#successModalMessage").html(text.responseText);
				$('#successModal').modal("show");
			},
			error : function($xhr, textStatus, errorThrown) {
				$("#errorModalMessage").html("Something went wrong while allocation");
				$('#errorModal').modal("show");				
			}
		});
}

function remove(link) { 
	var result = confirm("Want to delete?");
	if (result) {
        link.parentNode.parentNode.removeChild(link.parentNode);
	}
}