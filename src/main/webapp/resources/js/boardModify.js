console.log('modify in')

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;
        e.target.closest('li').remove()

        let add = uuid+","
        document.getElementById('uuids').value += add
    }
})