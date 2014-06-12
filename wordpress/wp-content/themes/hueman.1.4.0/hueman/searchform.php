				
<form method="get" class="searchform themeform" action="<?php echo home_url('/'); ?>">
	<div>
		<input type="text" class="search" name="s"  onblur="if(this.value=='')this.value='<?php _e('ZOEK PATIENT','hueman'); ?>';" onfocus="if(this.value=='<?php _e('ZOEK PATIENT','hueman'); ?>')this.value='';" value="<?php _e('ZOEK PATIENT','hueman'); ?>" />
	 
	</div>
</form>