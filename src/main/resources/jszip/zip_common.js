function zip_download_content( zip, entryName, filename )
{
	zip.file( entryName ).async("base64").then(
		function ( base64Text )
		{
			download("data:application/octet-stream;base64," + base64Text, filename, "application/octet-stream");
		}
	);
}

function zip_ext_image_to_tag( zip, entryName, imgTagId )
{
	zip.file( entryName ).async("base64").then(
		function ( base64Text )
		{
			var img = document.getElementById( imgTagId );
			img.setAttribute( 'src', 'data:image/jpeg;base64, ' + base64Text );
		}
	);

	var isOkExtFlag1 = new Boolean(false);
	var isOkExtFlag2 = new Boolean(false);

    // bim library : .xlsx(필수) / .rfa .smt(중 하나 필수)
    if (libraryType.equals("bim")) {
        isOkExtFlag1 = false;
        isOkExtFlag2 = false;

        for (File tempFile : fileList) {
            tempFileName = tempFile.getName();
            log.info("tempFileName: " + tempFileName);

            // 확장자가 맞으면
            if (fileStore.isExt(tempFileName, "xlsx")) {
                isOkExtFlag1 = true;
            }
            else if( (fileStore.isExt(tempFileName, "jpg")) || (fileStore.isExt(tempFileName, "png"))) {
                isOkExtFlag2 = true;
            }
        }
    } else if (libraryType.equals("content")) { // content : .xlsx .dyn(필수)
        isOkExtFlag1 = false;
        isOkExtFlag2 = false;

        for (File tempFile : fileList) {
            tempFileName = tempFile.getName();
            log.info("tempFileName: " + tempFileName);

            if ( fileStore.isExt(tempFileName, "xlsx") ) {
                isOkExtFlag1 = true;
            }
            if( fileStore.isExt(tempFileName, "jpg")){
                isOkExtFlag2 = true;
            }
        }
    }
}

alert("hihi");
