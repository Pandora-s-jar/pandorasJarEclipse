$(document).ready(function(){
	jQuery("#btnChangeUsername").click(function(){
	  jQuery("#inputUsername").attr("disabled", false);
	});
	jQuery("#btnChangeEmail").click(function(){
		  jQuery("#inputEmail").attr("disabled", false);
		});
	jQuery("#btnChangePassword").click(function(){
		  jQuery("#inputPassword").attr("disabled", false);
		});
	jQuery("#btnChangeDescription").click(function(){
		  jQuery("#inputDescription").attr("disabled", false);
		});
});