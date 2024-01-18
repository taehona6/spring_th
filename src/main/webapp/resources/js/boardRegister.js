console.log("boardRegister in");

document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('files').click();
})

//실행파일, 이미지파일, 파일 최대 사이즈 정규표현식 작성
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;
function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }
    else if(fileSize > maxSize){
        return 0;
    }else{ return 1; }   
}

document.addEventListener('change', (e)=>{
    console.log(e.target);
    if(e.target.id=='files'){
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);
        document.getElementById('regBtn').disabled = false;

        let div = document.getElementById('fileZone');
        div.innerHTML='';
        //<ul class="list-group list-group-flush">
        //<li class="list-group-item"></li>

        let isOk = 1;
        let ul = `<ul class="list-group list-group-flush">`;
            for(let file of fileObj){
                let validResult = fileValidation(file.name,file.size);
                isOk *= validResult;
                ul += `<li class="list-group-item">`;
                ul += `<div${validResult? ' class="fw-bold">업로드 가능' :  ' class="fw-bold text-danger">업로드 불가능'} </div>`
                ul += `${file.name}`
                ul += `<span class="badge rounded-pill text-bg-${validResult? 'success':'danger'}">${Math.round(file.size/1024.0)}KB</span>`
                ul += `</li>`
            }
            ul += `</ul>`;
            div.innerHTML = ul;

            if(isOk == 0 ){
                document.getElementById('regBtn').disabled = true;
            }
    }
})