// Loaded via <script> tag, create shortcut to access PDF.js exports.
var pdfjsLib = window['pdfjs-dist/build/pdf'];

// The workerSrc property shall be specified.
pdfjsLib.GlobalWorkerOptions.workerSrc = '/pdfjs/build/pdf.worker.js';

$(document).ready(function() {
	//$('#topbarTitle').text("Application Form Preview");

	
	$("canvas").each(function() {
		if (this.id == "pdfModalCanvas1")
			return;
		loadPDF($(this).attr("class").split(" ")[0], this.id);
	})

	$("canvas").click(function() {
		//console.log("clicked")
		if (this.id == "pdfModalCanvas1")
			return;
		loadPDF($(this).attr("class").split(" ")[0], "pdfModalCanvas1");
	})
});

function loadPDF(url, target) {
	// Asynchronous download of PDF
	var loadingTask = pdfjsLib.getDocument(url);
	console.log("loadingTask" +loadingTask+" url "+url+" target "+target);
	loadingTask.promise.then(function(pdf) {
		// Fetch the first page
		var pageNumber = 1;
		pdf.getPage(pageNumber).then(function(page) {
			var scale = 1.5;
			var viewport = page.getViewport({
				scale : scale
			});

			// Prepare canvas using PDF page dimensions
			var canvas = document.getElementById(target);
			var context = canvas.getContext('2d');
			canvas.height = viewport.height;
			canvas.width = viewport.width;
			//console.log("Canvas prepared "+canvas);
			// Render PDF page into canvas context
			var renderContext = {
				canvasContext : context,
				viewport : viewport
			};		
			var renderTask = page.render(renderContext);
			//console.log("rendering completed "+renderTask);
			renderTask.promise.then(function() {
			});
		});
	}, function(reason) {
		// PDF loading error
		//console.log("before error");
		console.error(reason);
	});
}