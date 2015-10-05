function createToc()
{
	infoHidden = false;
	var h1 = $('td h4');
	var l = h1.length;
	var toc = "<ul>";
	for (var i=0; i<l; i++){
		var ident = "toc"+i;
		var tr = h1[i].parentNode.parentNode;
		h1[i].setAttribute('id', ident);
		if (tr.classList.contains("error"))
		{
			var temp = "<li><a style=\"color:red;\" href='#" + ident + "' >" + h1[i].textContent + "</a></li>";
		}
		else
		{
			var temp = "<li><a href='#" + ident + "' >" + h1[i].textContent + "</a></li>";
		}
		toc += temp;
	}
	toc+="</ul>";
	var tocWrapper = document.getElementById('toc');
	console.log(toc);
	tocWrapper.innerHTML += toc;
	
	var buttons = document.getElementsByClassName("hideLowLevel");
        for (var i = 0; i < buttons.length; i++) {
			buttons[i].addEventListener('click', function () {
				manageActions(infoHidden);
			}, false);
    }
	
	manageActions(infoHidden)	
}
function markMethodAsFailed() {
	var a = $('table tr.error').parent();
	var l = a.length;
	for (var i=0; i<l; i++)
	{
		a[i].children[0].classList.remove("success")
		a[i].children[0].classList.add("error")
		a[i].children[1].classList.remove("success")
		a[i].children[1].classList.add("error")
	}
}
function addLinkBackToTheJob() {
	if (location.pathname.indexOf('/job/') > -1) {
		var jobPath = location.pathname.replace(/(\/job\/[^/]*)\/.*$/, '$1'),
			$a = $('<a></a>').attr('href', jobPath).text('Back to job'),
			$a = $a.wrap('<p></p>');
			$p = $('p').first();
		$p.before($a);
	}
}

$(document).ready(function () {
	markMethodAsFailed();
	createToc();
	addLinkBackToTheJob();
});

    function manageActions(direction) {
        // get all elements with class lowLevelAction
        var elements = document.getElementsByClassName("info");
        for (var i = 0; i < elements.length; i++) {
            if (direction == false) {
                //hide lowLevelActions
                elements[i].style.display = "none";
            } else if (direction == true) {
                //show lowLevelActions
                elements[i].style.display = "";
            }
        }
		
		var buttons = document.getElementsByClassName("hideLowLevel");
        for (var i = 0; i < buttons.length; i++) {
		if(direction == false){
			buttons[i].firstElementChild.classList.remove("icon-eye-open")
			buttons[i].firstElementChild.classList.add("icon-eye-close")
			} else{
			buttons[i].firstElementChild.classList.remove("icon-eye-close")
			buttons[i].firstElementChild.classList.add("icon-eye-open")
			}
		}
		infoHidden = !infoHidden;
    }