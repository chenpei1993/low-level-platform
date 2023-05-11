
export default {

    created(el, binding){
        let perVal = binding.value;
        if(binding.value){
            let permissions = sessionStorage.getItem("permissions")
            if(permissions != null){
                permissions.toLocaleLowerCase();
            }
            console.log(permissions)
            let hasPermission = permissions.indexOf(perVal.toLocaleString()) === -1
            if(hasPermission){
                el.style.display="none"
            }
        }
    }
}
