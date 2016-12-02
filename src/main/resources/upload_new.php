<?php
	$allowedMimeTypes = array( 'image/png' );

	$maxFileSize = 20000;

	$filePath = '../fileadmin/user_download/baufi-testsiegel/';
	
	function output() {
		echo '<html><head><title>Upload</title></head><body>';	
	
		if(isset($_REQUEST['submit']) && $_REQUEST['submit'] == "senden") {
		
			$theFile = $_FILES['theFile'];
			if (!check_mimetype($theFile['type'])) {
				die('Mime-Type '.$theFile['type'].' nicht erlaubt');
			}
			if (!check_filesize($theFile['size'])) {
				die('Maximale Dateigroesse ueberschritten');
			}

			$isUploaded = move_uploaded_file($theFile['tmp_name'], $filePath.$theFile['name']);
			if (!$isUploaded) {
				die('Fehler beim Upload');
			}
			echo '<span style="color:green;">Upload erfolgreich</span>';		
		}
		echo get_form();
		echo '</body></html>';
	}

	function get_form() {
		$content = '<form enctype="multipart/form-data" action="' . htmlspecialchars($_SERVER['PHP_SELF']) . '" method="post">
					<label for="theFile">Datei</label>
					<input name="theFile" type="file">
					<input type="submit" name="submit" value="senden">
				</form>';
		return $content;
	}
	
	function check_mimetype($mimeType) {
		return in_array($mimeType, $allowedMimeTypes);	
	}

	function check_filesize($filesize) {
		return $filesize < $maxFileSize;
	}
	
	output();
