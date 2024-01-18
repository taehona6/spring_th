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
                        <input type="text" class="cmtText" value="${cvo.content}"readonly><button type="button" class="btn btn-primary cmtModBtn">수정</button>
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