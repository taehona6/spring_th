document.getElementById('idValidBtn').addEventListener('click',()=>{
    let email = document.getElementById('email').value
    idValidation(email).then(result=>{
        if(result == "1"){
            alert('사용 가능한 이메일입니다.')
        }else{
            alert('이미 사용중인 이메일입니다.')
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