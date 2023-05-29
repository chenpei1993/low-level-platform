
export default {

    created(el, binding){
        let perVal = binding.value;
        if(binding.value){
            let permissions = sessionStorage.getItem("permissions")
            if (permissions == null) {
                return;
            }
            permissions = permissions.toLocaleLowerCase();
            let hasPermission = permissions.indexOf(perVal.toLocaleString()) === -1
            if (hasPermission) {
                el.style.display = "none"
            }
        }
    }
}
