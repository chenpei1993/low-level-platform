export default class TaskUtil{

    static sendTypeOptions = [
        {label: "企业微信", value: 1},
        {label: "手机号", value: 2},
        {label: "邮箱", value: 3},
    ]

    static getSendTypeOptionByValue(value){
        for(let i = 0; i < TaskUtil.sendTypeOptions.length; i++){
            let type = TaskUtil.sendTypeOptions[i]
            if(type.value === value){
                return type.label
            }
        }
        return ""
    }

    static statusOptions = [
        {label: "未开始", value: 1},
        {label: "执行中", value: 2},
        {label: "已完成", value: 3},
    ]

    static getStatusOptionByValue(value){
        for(let i = 0; i < TaskUtil.statusOptions.length; i++){
            let data = TaskUtil.statusOptions[i]
            if(data.value === value){
                return data.label
            }
        }
        return ""
    }
}
