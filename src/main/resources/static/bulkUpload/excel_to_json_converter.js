/**
 * 
 */

$(document).ready(function(e) {
	
	var getAllCentreCodes;
	var getAllCity;
	var getAllStates;
	$.get( "getAllCentreCodes", function( data ) {
		getAllCentreCodes = data;
	});
	
	$.get( "getAllCity", function( data ) {
		getAllCity = data;
	});
	
	$.get( "getAllStates", function( data ) {
		getAllStates = data;
	});
	
	
	function setTheme() {
		theme = $.trim($("#themes").val());
		font_size = $.trim($("#font_size").val());
		
		editorAce1.setTheme("ace/theme/" + theme);
		$("#code1").css({"font-size" : (font_size + "px")});
		editorAce1.setOptions({
			enableBasicAutocompletion : true,
			enableSnippets : true,
			enableLiveAutocompletion : true
		});
	}
	
	function show_msg(msg, type)
	{
		if(type == "info")
		{
			$("#msg").html('<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>&nbsp;' + msg).removeClass("text-danger")
			.addClass("text-info");
			$("#msg_modal").modal({backdrop : false});
		}else if(type == "error")
		{
			$("#msg").html('<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>&nbsp;' + msg).removeClass("text-info")
			.addClass("text-danger");
			$("#msg_modal").modal({backdrop : false});
		}
	}
	
	 
	
	ace.require("ace/ext/language_tools");
	var editorAce1 = ace.edit("code1");
	editorAce1.focus();
	editorAce1.setOptions({
		enableBasicAutocompletion: true,
		enableSnippets: true,
		enableLiveAutocompletion: true
	});
    editorAce1.setTheme("ace/theme/chrome");
    editorAce1.getSession().setMode("ace/mode/json");
	
	$("#clear").click(function(e) {
    	e.preventDefault();
		editorAce1.setValue("");
    });
	
	$("#code1").resizable({
		handles: 's',
    	resize: function(event, ui) {
    		editorAce1.resize();
    	}
	});
	
	$(window).resize(function(e) {
        $("#code1").css({"width" : "100%"});
    });
	
	$("#browse").click(function(e) {
        e.preventDefault();
		$("#file").click();
    });
	
	$("#download").click(function(e) {
        e.preventDefault();
		data = $.trim(editorAce1.getValue());
		if(data == "")
		{
			show_msg("The editor is empty!", "error");
			return false;
		}
		blob = new Blob(["" + data + ""], {
			type : "text/plain;charset=utf-8"
		});
		saveAs(blob, "data.txt");
    });
	
	$("#beautify_json").click(function(e) {
        e.preventDefault();
		data = $.trim(editorAce1.getValue());
		if(data != "")
		{
			try{
				editorAce1.setValue(vkbeautify.json(data, 4));
			}catch(e){}
		}
    });
	
	themelist = ace.require("ace/ext/themelist");
	theme = "";
	$(themelist.themes).each(function(key, value) {
		theme += '<option value="' + value.name + '">' + value.caption + '</option>';
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
		workbook.SheetNames.forEach(function(sheetName) {
			var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
			console.log("Hai"+roa);
			if(roa.length > 0){
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
						type: 'binary'
					});
					editorAce1.getSession().setUseWorker(true);				
					editorAce1.setValue(vkbeautify.json(to_json(workbook), 4));
					var final_data = vkbeautify.json(to_json(workbook), 4);
					builddata(final_data);
				} catch (e) {
					if(name.split(".").pop() == "csv")
					{
						editorAce1.setValue(vkbeautify.json(CSV2JSON(data), 4));
					}else
					{
						editorAce1.getSession().setUseWorker(false);
						editorAce1.setValue("Please select an excel file.");
					}
				}
			};
			reader.readAsBinaryString(f);
		}
	}
	
	$("#file").change(function(e) {
		$('#loading').html("").show();
		// alert("Haiiai");
        e.preventDefault();
		editorAce1.setValue("Please wait while loading your file.");
		var ext = $.trim($("#file").val().split('.').pop().toLowerCase());       
		if($.trim($("#file").val()) == "")
		{
			editorAce1.getSession().setUseWorker(false);
			alert("Please upload valid excel file first");
			location.reload();
		}else if($.inArray(ext, ['xlsx']) == -1) {
			editorAce1.getSession().setUseWorker(false);
			alert("Please upload valid excel (xlsx) file");
			location.reload();
			// $("#file").change();
		}
		handleFile(e);
    });
	
	$("#convert").click(function(e) {
        e.preventDefault();
        var ext = $('#my_file_field').val().split('.').pop().toLowerCase();       
		if($.trim($("#file").val()) == "")
		{
			editorAce1.getSession().setUseWorker(false);
			alert("Please upload valid excel file first");
		}else if($.inArray(ext, ['xlsx']) == -1) 
			editorAce1.getSession().setUseWorker(false);
			alert("Please upload valid excel (xlsx) file");
		{
			$("#file").change();
		}
    });
	
	$(document).on('click', '#SubmitData', function(e){ 
		$('#loading').html("").show();
		// Your Code
		$(this).hide();
		e.preventDefault();		
		data = $.trim(editorAce1.getValue());
		if(data == "")
		{
			show_msg("The editor is empty resubmit!", "error");
			return false;
		}
		
		var xsrfTokenFromHiddenElement = $("meta[name='_csrf']").attr("content");

		var obj = JSON.parse(data);
		obj = trimArrAndObj(obj);
		for (var key in obj) {
		    if (!Object.prototype.hasOwnProperty.call(obj, key)) continue;
		    obj = obj[key];
		    break;
		}
		
		var error_msg = "";
		for(var i=0;i<obj.length;i++) {
			$.each(obj[i], function (key, value) {
				value = escapeHtml(value);
			   /* if(!value) { 
			    	error_msg += "Row "+(i+2)+" of uploaded XLXS file with "+key+" is null \n";
			    }*/
			    
			   /* if( /[^a-zA-Z0-9\-\/]/.test( value ) ) {
			    	error_msg += "Row "+(i+2)+" with value "+value+" of "+key+" of uploaded XLXS file has alphanumeric\n";
			    }*/
			    
			    if(key == 'centerCode' && $.isArray(getAllCentreCodes)) {
				    if(getAllCentreCodes.indexOf(value) != -1) {
				    	error_msg += "Row "+(i+2)+" with value "+value+" of "+key+" alreadyexists in the Database \n";
				    }
			    }
			    
			    if(key == 'cityDetails' && $.isArray(getAllCity)) {
				    if(getAllCity.indexOf(value.toLowerCase()) == -1) {
				    	error_msg += "cityDetails with value "+value+" does not exists \n";
				    }
			    }
			    
			    if(key == 'stateDetails' && $.isArray(getAllStates)) {
				    if(getAllStates.indexOf(value.toLowerCase()) == -1) {
				    	error_msg += "stateDetails with value "+value+" does not exists \n";
				    }
			    }
			    
			    /*
				 * if(!error_msg && key === "cityDetail") { obj[i].cityDetail =
				 * getCity(value); } if(!error_msg && key === "stateDetail") {
				 * obj[i].cityDetail = getState(value); }
				 */
			});
		}
		// alert(error_msg);
		if(error_msg)  {
			alert(error_msg);
			location.reload();
			return false;
		}
		
		var newJson = JSON.stringify(obj);
		
		// alert(newJson);
		
		$.ajax({
			   url: 'bulkUploadAjax',
			   data: newJson,
			   contentType: "application/json",
			   type : 'POST',
			   headers:	{
	 		    	"X-CSRF-TOKEN" : xsrfTokenFromHiddenElement
	 		    },	
			   dataType: 'text',
		       error: function(xhr,err)  {
		    	  var errData = "<br />";
		    	  var data=xhr.responseText;
		    	  if(data) {
		    	  var jsonResponse = JSON.parse(data);
		    	  var count = Object.keys(jsonResponse).length;
		    	  
		    	  if(count) {
			    	  $.each( jsonResponse, function( key, value ) {
			    		  errData += value+'<br />';
			    	  });	   
		    	  }  else  {
		    		  	  errData += jsonResponse+'<br />';
		    	  }
		    	  }
		    	  $('.table-responsive,#file,span.error').hide();    	  
			      $('.error').html('<div class="alert alert-danger"><p> <span class="glyphicon glyphicon-exclamation-sign"></span> <strong>Error in the uploaded File </strong>'+errData+'<br /></p></div><p><a class="btn btn-primary" href="bulkuploadcentre">Click here to upload again</a>');
		    	  
			      $('#loading').html("").hide();
			   },
			   success: function(data) {
				 if(data) {
					 $('.table-responsive,#file,span.error').hide();
					 $('.info').html('<div class="alert alert-success"><p> <span class="glyphicon glyphicon-ok"></span> <strong>Value(s) Added successfully</strong><a href="addCenterMaster#centreMasterTable">  Click to View</a><br /></p></div><p><a class="btn btn-primary" href="bulkuploadcentre">Click here to upload again</a>'); 
				 }
				 $('#loading').html("").hide();
														     /*
																 * var $title =
																 * $('<h1>')
																 * .text(
																 * data.talks[0].talk_title);
																 * var
																 * $description = $( '<p>')
																 * .text(
																 * data.talks[0].talk_description);
																 * $('#info').append(
																 * $title).append(
																 * $description);
																 */
			   },
		 });
		
	});
	
	function setcityDetail(id) {
		  for (var i = 0; i < jsonObj.length; i++) {
		    if (jsonObj[i].Id === id) {
		      jsonObj[i].cityDetail = "Hello";
		      return;
		    }
		  }
	}
	
	function getCity(cityId)  {
		alert(cityId);
	}
	function getState(stateId)  {
		alert(stateId);
	}
 


	function escapeHtml(unsafe) {
	    return unsafe
	         .replace(/&/g, "&amp;")
	         .replace(/</g, "&lt;")
	         .replace(/>/g, "&gt;")
	         .replace(/"/g, "&quot;")
	         .replace(/'/g, "&#039;");
	 }


	
	function trimArrAndObj(obj) {
		  if (!Array.isArray(obj) && typeof obj != 'object') return obj;
		  return Object.keys(obj).reduce(function(acc, key) {
		    acc[key.trim()] = typeof obj[key] == 'string'? obj[key].trim() : trimArrAndObj(obj[key]);
		    return acc;
		  }, Array.isArray(obj)? []:{});
		}
	function builddata(JSON_DATA)  {
		var obj = $.parseJSON(JSON_DATA);
		console.log(obj.Sheet1.length);
		console.log(obj.Sheet1);
		$('#example').DataTable( {
			"aaData": obj.Sheet1,
			"aoColumns": [
			      { "sTitle": "Centre Code",   "mData": "centerCode" },
			      { "sTitle": "Centre Name",  "mData": "centerName" },
			      { "sTitle": "Centre Address", "mData": "centerAddress" },
			      { "sTitle": "Centre Landmark",  "mData": "centerLandmark" },
				  { "sTitle": "Centre Pincode",   "mData": "centerPincode" },
			      { "sTitle": "Centre Google Map",  "mData": "centerGoogleMapurl" },
			      { "sTitle": "Centre City", "mData": "cityDetails" },
			      { "sTitle": "Centre State",  "mData": "stateDetails" },
				  { "sTitle": "Centre Contact Person",   "mData": "centerContactPerson" },
			      { "sTitle": "Centre Contact Number",  "mData": "centerContactNumber" },
			      { "sTitle": "Centre Contact EmailID", "mData": "centerContactEmailid" },
			      { "sTitle": "Centre Alias", "mData": "centerAlias" },
			      { "sTitle": "Centre Capacity",  "mData": "centerCapacity" }
	        ]
	    });
		
		if(obj.Sheet1.length)  {
			$('#submit_data').html('<br /><input type="button" class="btn btn-primary" value="Submit" id="SubmitData"></input>');
			$('#loading').html("").hide();
		}
		
	}
	// centerName centerAddress centerAlias centerCapacity centerCode
	// centerContactEmailid centerContactNumber centerContactPerson
	// centerGoogleMapurl centerLandmark centerPincode centerStatus cityDetail
	// stateDetail

});