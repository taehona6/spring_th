document.getElementById('idValidBtn').addEventListener('click',()=>{
    let email = document.getElementById('email').value
    idValidation(email).then(result=>{
        if(result == "1"){
            alert('성공로직')
        }else{
            alert('실패')
        }
    })
    
})

async function idValidation(email){
    const url = "/member/validate"
    const config = {
        method : "post",
        headers : {
            "content-type" : "application/json; charset:utf-8"
        },
        body : email
    }
    const resp = await fetch(url,config)
    const result = await resp.text()
    return result;
}