<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH KONOBARA -->
<jsp:useBean id="rasporedi" type="java.util.List" scope="session"/>
<jsp:useBean id="reoni" type="java.util.List" scope="session"/>

<html>
<head>
 
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
 <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
 
 <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script src="modernizr.min.js"></script>
 <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/fabric.js/1.3.0/fabric.min.js"></script>

 <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
  
<style>
#canvas-container {
    position: relative;
    width: 600px;
    height: 600px;
    box-shadow: 0 0 5px 1px black;
    margin: 10px auto;
    border: 5px solid;
	border-color: #90C3D4;
}
#canvas-containerJSON {
    position: relative;
    width: 600px;
    height: 600px;
    box-shadow: 0 0 5px 1px black;
    margin: 10px auto;
    border: 5px solid;
	border-color: #90C3D4;
}
#canvas-container.over {
    border: 5px solid #D4A190;
}
#images img.img_dragging {
    opacity: 0.4;
}
/* 
Styles below based on  http://www.html5rocks.com/en/tutorials/dnd/basics/ 
*/

/* Prevent the text contents of draggable elements from being selectable. */
[draggable] {
    -moz-user-select: none;
    -khtml-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    /* Required to make elements draggable in old WebKit */
    -khtml-user-drag: element;
    -webkit-user-drag: element;
    cursor: move;
}
</style>
</head>
<body>
<div id="images">
    <img id = "4" draggable="true" src="images/table1.jpg" width="100" height="100"></img>
    <img id = "8" draggable="true" src="images/table2.jpg" width="100" height="100"></img>
    <img id = "6" draggable="true" src="images/table3.jpg" width="100" height="100"></img>
	<button type="button" id="btn_edit"> JSON </button> 
    <button type="button" id="btn_load"> LOAD </button>  	
	<button type="button" id="btn_add_reon"> ADD </button>  
</div>

<div id="canvas-container">
    <canvas id="canvas" width="600" height="600" style="border:1px solid #A1D490;"></canvas>
</div>

    <div id="rasporedModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
                    <h4 id="modalTitle" class="modal-title"></h4>
                </div>
                <div id="modalBody" class="modal-body">
				<form id="createAppointmentForm" class="form-horizontal">
						<div class="control-group">
							<div class="controls">
								<input type="hidden" id="top" />
								<input type="hidden" id="left" />
								<input type="hidden" id="reonId" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="top">Top:</label>
							<div class="controls controls-row" id="top" style="margin-top:5px;"></div>
						</div>
						<div class="control-group">
							<label class="control-label" for="left">Left:</label>
							<div class="controls controls-row" id="left" style="margin-top:5px;"></div>
						</div>
						
							<select id="reonId">
								<c:forEach items="${reoni}" var="reon">
								<option value=${reon.idReona}>${reon.nazivReona}</option>
								</c:forEach>
							</select> 
						
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" id="submitButton"class="btn btn-primary">Save changes</button>
					</form>
				</div>
               
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
<script>
var json_data1;
<c:forEach items="${rasporedi}" var="raspored">
		json_data1 = ${raspored.jsonRaspored}	
</c:forEach>
</script>
<script>
/* Drag and Drop code adapted from http://www.html5rocks.com/en/tutorials/dnd/basics/ */

var canvas = new fabric.Canvas('canvas');
/* 
NOTE: the start and end handlers are events for the <img> elements; the rest are bound to 
the canvas container.
*/

function handleDragStart(e) {
    [].forEach.call(images, function (img) {
        img.classList.remove('img_dragging');
    });
    this.classList.add('img_dragging');
}

function handleDragOver(e) {
    if (e.preventDefault) {
        e.preventDefault(); // Necessary. Allows us to drop.
    }

    e.dataTransfer.dropEffect = 'copy'; // See the section on the DataTransfer object.
    // NOTE: comment above refers to the article (see top) -natchiketa

    return false;
}

function handleDragEnter(e) {
    // this / e.target is the current hover target.
    this.classList.add('over');
}

function handleDragLeave(e) {
    this.classList.remove('over'); // this / e.target is previous target element.
}

function handleDrop(e) {
    // this / e.target is current target element.

    if (e.stopPropagation) {
        e.stopPropagation(); // stops the browser from redirecting.
    }

    var img = document.querySelector('#images img.img_dragging');

    console.log('event: ', e);

    var newImage = new fabric.Image(img, {
        width: img.width,
        height: img.height,
        // Set the center of the new object based on the event coordinates relative
        // to the canvas container.
        left: e.layerX,
        top: e.layerY
    });
    canvas.add(newImage);

    return false;
}

function handleDragEnd(e) {
    // this/e.target is the source node.
    [].forEach.call(images, function (img) {
        img.classList.remove('img_dragging');
    });
}

if (Modernizr.draganddrop) {
    // Browser supports HTML5 DnD.

    // Bind the event listeners for the image elements
    var images = document.querySelectorAll('#images img');
    [].forEach.call(images, function (img) {
        img.addEventListener('dragstart', handleDragStart, false);
        img.addEventListener('dragend', handleDragEnd, false);
    });
    // Bind the event listeners for the canvas
    var canvasContainer = document.getElementById('canvas-container');
    canvasContainer.addEventListener('dragenter', handleDragEnter, false);
    canvasContainer.addEventListener('dragover', handleDragOver, false);
    canvasContainer.addEventListener('dragleave', handleDragLeave, false);
    canvasContainer.addEventListener('drop', handleDrop, false);
} else {
    // Replace with a fallback to a library solution.
    alert("This browser doesn't support the HTML5 Drag and Drop API.");
}

$('#btn_edit').click(function(){
    var canvas_ = new fabric.Canvas('canvas');
	//JSON DATA   
	var json_data = JSON.stringify(canvas.toDatalessJSON()); 
	//LOAD JSON DATA
	canvas_.loadFromJSON(JSON.parse(json_data), function(obj) {
		
			canvas_.renderAll();
			console.log(' this is a callback. invoked when canvas is loaded!xxx ');
   
			canvas_.forEachObject(function(obj){
					console.log(obj.name);
  
					if(obj.name === 'recta'){      
						obj.set({
							left: 100,
							top:200,
							height: 700,
							width: 700,
							scaleX: .35,
							scaleY:.35,
							lockScalingY: .35
						});
            
						canvas_.add(obj); 
					}
    
			});
			
			
	});
	toastr.success('Uspesno ste definisali raspored stolova!');
	window.location.href = "./CreateRasporedStolovaController?stoloviJson=" + json_data;

});

$('#btn_load').click(function(){
		
		canvas.loadFromJSON(JSON.parse(JSON.stringify(json_data1)), function(obj) {
			canvas.renderAll();
			console.log(' this is a callback. invoked when canvas is loaded!xxx ');
   
			canvas.forEachObject(function(obj){
					console.log(obj.name);
  
					if(obj.name === 'recta'){      
						obj.set({
							left: 100,
							top:200,
							height: 700,
							width: 700,
							scaleX: .35,
							scaleY:.35,
							lockScalingY: .35
						});
            
						canvas.add(obj); 
					}
    
			});
		});
});
$('#btn_add_reon').click(function(){
	//OVDE DODAVANJE REONA
	var activeObject = canvas.getActiveObject(),
	
    activeGroup = canvas.getActiveGroup();
    if (activeObject) {

		var top = activeObject.get('top');
		$('#rasporedModal #top').text(top);
		$('#rasporedModal #top').val(top);
		var left = activeObject.get('left');
		$('#rasporedModal #left').text(left);
		$('#rasporedModal #left').val(left);
	
				// change the border color just for fun
				$(this).css('border-color', 'red');
				
				$('#rasporedModal').modal();
                    return false;
        /*if (confirm('Are you sure?')) {
            //canvas.remove(activeObject);
        }*/
    }
    /*else if (activeGroup) {
        if (confirm('Are you sure?')) {
            var objectsInGroup = activeGroup.getObjects();
            canvas.discardActiveGroup();
            objectsInGroup.forEach(function(object) {
            canvas.remove(object);
            });
        }
    }*/
});
$(document).on('click','#submitButton',function(e){
		e.preventDefault();
		var top = $('#rasporedModal #top').val();
		var left = $('#rasporedModal #left').val();
		var rid = $('#rasporedModal #reonId option:selected').val();
		window.location.href = "./AddReonToStoController?idReona=" + rid + "&top=" + top + "&left=" + left;
		toastr.success('Uspesno ste definisali reon stolu!');
		
	});

</script>
</body>
</html>
