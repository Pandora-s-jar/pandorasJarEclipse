jQuery.noConflict();

jQuery(document).ready(function(){


	jQuery("#btnChangeUsername").click(function(){
	  jQuery("#inputUsername").attr("readonly", false);
	});
	jQuery("#btnChangeEmail").click(function(){
		  jQuery("#inputEmail").attr("readonly", false);
		});
	jQuery("#btnChangePassword").click(function(){
		  jQuery("#inputPassword").attr("readonly", false);
		});
	jQuery("#btnChangeDescription").click(function(){
		  jQuery("#inputDescription").attr("readonly", false);
		});

	var bool = true;
	jQuery("#addFriend").click(function(){
		if(bool)
		{
			var inputText = document.createElement("input");
			inputText.setAttribute("type", "text");
			inputText.setAttribute("id", "nameFriend");
			inputText.setAttribute("name", "nameFriend");
			inputText.setAttribute("value", "Insert username");
			var buttonOK = document.createElement("input");
			buttonOK.setAttribute("id", "sendNameFriend");
			buttonOK.setAttribute("type", "submit");
			buttonOK.setAttribute("value", "Send");
			buttonOK.setAttribute("class", "btn btn-primary btn-center background-color");
			jQuery("#insideForm").append("<br id='toDelete'>",inputText, buttonOK);
			bool = false;
		}
		else
		{
			jQuery("#toDelete").remove();
			jQuery("#sendNameFriend").remove();
			jQuery("#nameFriend").remove();
			bool = true;
		}
		event.stopPropagation();

	});
});