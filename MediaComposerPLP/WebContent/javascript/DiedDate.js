$(function() {
	$('#row_dim').hide();
	$('#yn').change(function() {
		if ($('#yn').val() == 'Yes') {
			$('#row_dim').show();
		} else {
			$('#row_dim').hide();
		}
	});
});

function validateDiedDate() {
	if (document.form.composerBornDate.value < document.form.composerDiedDate.value){
		return true;
	} else {
		alert("Died Date should be After Born Date");
		return false;
	}
}

function validateArtistDiedDate() {
	if (document.form.artistBornDate.value < document.form.artistDiedDate.value){
		return true;
	} else {
		alert("Died Date should be After Born Date");
		return false;
	}
}