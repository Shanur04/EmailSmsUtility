$(document).ready(function(e) {

	$.ajax({
		type : 'GET',
		processData : false,
		contentType : "application/json",
		async : false,
		url : 'getRjctCnt',
	// data : JSON
	// .stringify(jsonObj)
	}).done(function(data) {

		console.log(data)
		if (data.status == "success") {
			if (data.samePost != null) {
				$("#totalSamePost").text(data.samePost);
			} else {
				$("#totalSamePost").text("0");
			}
			if (data.multiplePost != null) {
				$("#totalMultiplePost").text(data.multiplePost);
			} else {
				$("#totalMultiplePost").text("0");
			}
			if (data.allPost != null) {
				$("#totalAllPost").text(data.allPost);
			} else {
				$("#totalAllPost").text("0");
			}
		}
	}).fail(function(xhr, err) {
		$("#totalSamePost").text("0");
		$("#totalMultiplePost").text("0");
		$("#totalAllPost").text("0");
		$("#errorModalMessage").html("Check For DB Column Related To Duplicate Rejected Candidates.");
		$('#errorModal').modal("show");
	});

})


$('input[type=radio][name=postRadio]').change(function() {
    console.log(this.value)
    
    $.ajax({
		type : 'GET',
		processData : false,
		contentType : "application/json",
		async : false,
		url : 'getRjctCntProof/'+this.value,
	// data : JSON
	// .stringify(jsonObj)
	}).done(function(data) {

		console.log(data)
		if (data.status == "success") {
			if (data.count != null) {
				$("#statusCount").text(data.count);
			} else {
				$("#statusCount").text("0");
			}
			
		}
	}).fail(function(xhr, err) {
		$("#totalSamePost").text("0");
		$("#totalMultiplePost").text("0");
		$("#totalAllPost").text("0");
		$("#errorModalMessage").html("Check Is isRejected Flag Or rejectedReason is Set Or Not");
		$('#errorModal').modal("show");
	});
    
});