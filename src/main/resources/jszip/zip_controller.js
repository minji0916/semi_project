function checkfilelist( zip)
{
    var isOkExtFlag1 = Boolean(false);
    var isOkExtFlag2 = Boolean(false);
    var isRealOkExt =  Boolean(false);

    // Zip파일내의 모든 파일을 순회한다.(압축을 푼것이 아니고 zip파일내의 파일목록)
    zip.forEach(
        function (relativePath, entry )
        {
                // 확장자 추출
                var fileName = entry.name;
                var index = fileName.lastIndexOf(".")+1;
                var ext = entry.name.substring(index, fileName.length);
                var libraryType = 'content';

                // 확장자 check
                if( libraryType == 'bim') { // .xlsx(필수) / .rfa .smt(중 하나 필수)
                    if(ext == 'xlsx'){
                        isOkExtFlag1 = true;
                    } else if( ext == 'jpg' || ext == 'png' ){
                        isOkExtFlag2 = true;
                    }
                }else if( libraryType == 'content') { // .xlsx .dyn(필수)
                    if(ext == 'xlsx'){
                        isOkExtFlag1 = true;
                    } else if(ext == 'jpg'){
                        isOkExtFlag2 = true;
                    }
                }
        }
    );

    isRealOkExt = isOkExtFlag1 && isOkExtFlag2;

    if( (!isRealOkExt) ){
        alert('필수 파일이 부족합니다. 파일 확장자를 확인해주세요');
        location.href='/';
    }
}

function showfilelist( zip, outId )
{
    var text = '<table border="1">';

    text += '<tr>';
    text += '<td>relativePath<br/>name</td>';
    text += '<td>dir/file</td>';
    text += '</tr>';

    // Zip파일내의 모든 파일을 순회한다.(압축을 푼것이 아니고 zip파일내의 파일목록)
    zip.forEach(
        function (relativePath, entry )
        {
                text += '<tr>';
                text += '<td>';

                // 사용자가 목록의 항목을 클릭할 경우 다운로드 받게 한다.
                text += '<a href="javascript:zip_download_content( zipInst, \'' + entry.name + '\', \'' + entry.name + '\' )">';
                text += relativePath + '<br/>'+ entry.name;
                text += '</a></td>';
                text += '<td>' + ( entry.dir ? 'dir' : 'file' ) + '</td>';
                text += '</tr>';
        }
    );
    text += '</table>';
    document.getElementById( outId ).innerHTML = text;
}