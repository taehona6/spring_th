document.getElementById('idValidBtn').addEventListener('click',()=>{
    let email = document.getElementById('email').value
    idValidation(email).then(result=>{
        if(result == "1"){
            alert('사용 가능한 이메일입니다.')
            document.getElementById('realemail').value = email
            document.getElementById('email').setAttribute('disabled',true)
            console.log(document.getElementById('realemail').value)
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

document.getElementById('regBtn').disabled=true;
document.addEventListener('change', (e)=>{
    
    email = document.getElementById('realemail').value
    pwd1 = document.getElementById('pwd').value
    pwd2 = document.getElementById('pwd2').value
    nickname = document.getElementById('nickName').value
    regbtn = document.getElementById('regBtn')

    if(nickname != "" && pwd1!= "" && pwd2!="" && email != "" && pwd1==pwd2){
        regbtn.disabled=false;
    }else{
        regbtn.disabled=true;
    }
})