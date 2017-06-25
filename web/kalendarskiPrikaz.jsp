<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH KONOBARA -->
<jsp:useBean id="konobari" type="java.util.List" scope="session"/>
<jsp:useBean id="reoni" type="java.util.List" scope="session"/>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.css" type="text/css" rel="stylesheet" />
	
	
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	}
		
	#wrap {
		width: 1100px;
		margin: 0 auto;
	}
		
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
	}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
	}
		
	#external-events .fc-event {
		margin: 10px 0;
		cursor: pointer;
	}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
	}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
	}

	#bootstrapModalFullCalendar {
		float: right;
		width: 900px;
	}

</style>
</head>
<body>
	<div id='wrap'>

		<div id='external-events'>
			<h4>Draggable Events</h4>
			<c:forEach items="${konobari}" var="konobar">
				<div class='fc-event' id=${konobar.korisnik_id}>${konobar.korisnik_ime}</div>
			</c:forEach>
			<p>
				<input type='checkbox' id='drop-remove' />
				<label for='drop-remove'>remove after drop</label>
			</p>
		</div>
		
		<div id='bootstrapModalFullCalendar'></div>
		
		<div style='clear:both'></div>
		
	</div>

    <div id="fullCalModal" class="modal fade">
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
								<input type="hidden" id="apptStartTime"/>
								<input type="hidden" id="apptEndTime"/>
								<input type="hidden" id="title"/>
								<input type="hidden" id="id" />
								<input type="hidden" id="reonId" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="apptStartTime">Start:</label>
							<div class="controls controls-row" id="apptStartTime" style="margin-top:5px;"></div>
						</div>
						<div class="control-group">
							<label class="control-label" for="apptEndTime">End:</label>
							<div class="controls controls-row" id="apptEndTime" style="margin-top:5px;"></div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="title">Title:</label>
							<div class="controls controls-row" id="title" style="margin-top:5px;"></div>
						</div>
						<div class="control-group">
							<label class="control-label" for="id">Id:</label>
							<div class="controls controls-row" id="id" style="margin-top:5px;"></div>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
	<script src='jquery-ui.custom.min.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
			
		
		
            $('#bootstrapModalFullCalendar').fullCalendar({
                header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultView: 'agendaWeek',
			
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar
			
			selectable: true,
			selectHelper: true,
			
			drop: function() {
				//alert("dropped");
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
			},
			eventClick: function(calEvent, jsEvent, view) {
				
				starttime=moment(calEvent.start).format('YYYY/MM/DD HH:mm');
				endtime=moment(calEvent.end).format('YYYY/MM/DD HH:mm');
				
				$('#fullCalModal #apptStartTime').text(starttime);
				$('#fullCalModal #apptEndTime').text(endtime);
				$('#fullCalModal #title').text(calEvent.title);
				$('#fullCalModal #id').text(calEvent.id);

				
				$('#fullCalModal #apptStartTime').val(starttime);
				$('#fullCalModal #apptEndTime').val(endtime);
				$('#fullCalModal #title').val(calEvent.title);
				$('#fullCalModal #id').val(calEvent.id);
	
				// change the border color just for fun
				$(this).css('border-color', 'red');
				
				$('#fullCalModal').modal();
                    return false;
				

			},
			eventDrop: function(event, delta, revertFunc) {
				
				alert(event.title + " was dropped");
				
			},
			select: function(start, end, allDay) {
				starttime=moment(start).format('YYYY/MM/DD HH:mm');
				endtime=moment(end).format('YYYY/MM/DD HH:mm');
				var mywhen = starttime + ' - ' + endtime;
				alert(mywhen);
				
			},
                events:
                [
                   {
                      "title":"Free Pizza",
                      "allday":"false",
                      "description":"<p>This is just a fake description for the Free Pizza.</p><p>Nothing to see!</p>",
                      "start":moment().subtract('days',14),
                      "end":moment().subtract('days',14),
                      "url":"http://www.mikesmithdev.com/blog/coding-without-music-vs-coding-with-music/"
                   },
                  
                   {
                      "title":"CSS Meetup",
                      "allday":"false",
                      "description":"<p>This is just a fake description for the CSS Meetup.</p><p>Nothing to see!</p>",
                      "start":moment().add('days',27),
                      "end":moment().add('days',27),
                      "url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"
                   }
                ]
            });
			
				/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title: $.trim($(this).text()), // use the element's text as the event title
				stick: true, // maintain when user navigates (see docs on the renderEvent method)
				id: $(this).attr('id')
			});

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});

		});
		
        });
		
		$(document).on('click','#submitButton',function(e){
		e.preventDefault();
		var eid = $('#fullCalModal #id').val();
		var etit = $('#fullCalModal #title').val();
		var dod = $('#fullCalModal #apptStartTime').val();
		var ddo = $('#fullCalModal #apptEndTime').val();
		var rid = $('#fullCalModal #reonId option:selected').val();
		
		window.location.href = "./CreateSmenaController?idRad=" + eid + "&imeRad=" + etit + "&dOD=" + dod + "&dDO=" + ddo + "&rId=" + rid;
		toastr.success('Uspesno definisan raspored rada za radnika: ' + etit);
		
	});
    </script>
</body>
</html>