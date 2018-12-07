/** 
 * Vendor Portal Custom Scripts
 */

var timeoutID = null;

function showNotifBar() {
	notifBar.show();
	timeoutID = window.setTimeout(hideNotifBar, 5000);
}

function hideNotifBar() {
	notifBar.hide();
	if (timeoutID != null) {
		window.clearTimeout(timeoutID);
	}
}
