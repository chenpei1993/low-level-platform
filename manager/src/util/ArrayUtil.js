export default class ArrayUtil{
    static delItem(arr, idx){
        let _arr = []
        for(let i = 0; i < arr.length; i++){
            if(i !== idx){
                _arr.push(arr[i])
            }
        }
        return _arr; 
    }
}