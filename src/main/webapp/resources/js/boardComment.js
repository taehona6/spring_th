console.log('bc.js in')
console.log(bnoVal)
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    let cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == ''){
        alert('댓글을 입력하세요')
        return;
    }
    
    let cmtData = {
        bno:bnoVal,
        writer:document.getElementById('cmtWriter').innerText,
        content:cmtText.value
    }
    console.log(cmtData)
    postComment(cmtData).then(result=>{
        if(result.length>0){
            alert('등록성공')
            cmtText.value=""
            document.getElementById('cmtQty').innerText=result;
            spreadComment(bnoVal)
        }
    })
})



async function postComment(cmtData){
    try{
        const url = "/comment/"
        const config = {
            method : "post",
            headers:{
                "content-type" : "application/json;charset=UTF-8"
            },
            body:JSON.stringify(cmtData)
        }
        
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result
    }catch(error){
        console.log(error)
    }
}

async function getCommentList(bnoVal){
    try {
        const url = "/comment/"+bnoVal
        const resp = await fetch(url)
        const result = await resp.json()
        return result;
    } catch (error) {
        console.log(error)
    }
}

function spreadComment(bnoVal){
    getCommentList(bnoVal).then(result=>{
        console.log(result)
        let div = document.getElementById('cmtlistArea')
        if(result.length>0){
            div.innerHTML=''
            for(let cvo of result){
                let add = `
                <div class="mb-3 row">
                    <label class="col-sm-2 col-form-label cmtWriter">${cvo.writer}</label>
			        <div class="col-sm-10" data-cno="${cvo.cno}" data-writer="${cvo.writer}">
                        <input type="text" class="cmtText" value="${cvo.content}"readonly>
                        <button type="button" class="btn btn-primary cmtModBtn" data-bs-toggle="modal" data-bs-target="#exampleModal">수정</button>
                        <button type="button" class="btn btn-primary cmtDelBtn">삭제</button>
			        </div>
			    </div>
                `
                div.innerHTML += add;
            }
        }else{
            div.innerHTML = `<div>등록된 댓글이 없습니다.</div>`
        }
    })
}


/* <div class="modal-body">
<input type="text" id="cmtTextMod">
<button type="button" id="cmtModBtn">수정</button>
</div> */

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains("cmtModBtn")){
        let div = e.target.closest('div')
        let cmtText = div.querySelector(".cmtText").value
        
        document.getElementById('cmtTextMod').value = cmtText
        document.getElementById('cmtModBtn').setAttribute("data-cno",div.dataset.cno)
        document.getElementById('cmtModBtn').setAttribute("data-writer",div.dataset.writer)
        document.getElementById('modalTitle').innerText = "댓글 수정 : " + div.dataset.cno
    }else if(e.target.id == 'cmtModBtn'){
        let cmtData = {
            cno : e.target.dataset.cno,
            writer : e.target.dataset.writer,
            content : document.getElementById('cmtTextMod').value
        }
        updateComment(cmtData).then(result=>{
            if(result==1){
                document.querySelector('.btn-close').click();
                spreadComment(bnoVal)
            }else{
                alert('수정실패')
                document.querySelector('.btn-close').click();
            }
        })

    }else if(e.target.classList.contains("cmtDelBtn")){
        let cmtData = {
            cno : e.target.closest('div').dataset.cno,
            bno : bnoVal
        }
        deleteComment(cmtData).then(result=>{
            console.log(result)
            if(result.length>0){
                alert('삭제성공')
                spreadComment(bnoVal)
                document.getElementById('cmtQty').innerText=result;
            }
        })
    }

})


async function updateComment(cmtData){
    try {
        const url = "/comment/"
        const config = {
            method : "put",
            headers : {
                "content-type" : "application/json;charset=utf-8"
            },
            body : JSON.stringify(cmtData)
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result;
    } catch (error) {
        console.log(error)
    }
}

async function deleteComment(cmtData){
    try {
        const url = "/comment/"
        const config = {
            method : "delete",
            headers : {
                "content-type" : "application/json ; charset=utf-8"
            },
            body : JSON.stringify(cmtData)
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result;
    } catch (error) {
        console.log(error)
    }
}