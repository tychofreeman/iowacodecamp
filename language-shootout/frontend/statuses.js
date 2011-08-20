$(document).ready(function() {
		// check for new statuses every minute or so.

		// check for new replies every minute or so?
});

function getLatestStatuses(oldestStatusId, successFn) {
	$.ajax(
		{
			url: "statuses?max=20&start=newest&end=" + (oldestStatusId == null ? "oldest" : oldestStatusId)
			success: successFn
			dataType: "json"
		}
	);
}

function handleStatusUpdates(data, textStatus, jqXHR) {
	// ???
}
