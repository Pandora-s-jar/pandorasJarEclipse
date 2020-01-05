jQuery(document).ready(function()
{

	if(!sessionStorage.getItem("logged"))
	{
		jQuery('#myModal').modal('show');
	
		jQuery(".modalCloseBtn").click(function()
		{
			//TODO replace "/" with home url
			window.location.replace("/");
		})
	}
});
