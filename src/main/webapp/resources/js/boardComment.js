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