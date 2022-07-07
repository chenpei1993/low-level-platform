class Question{
    constructor(idx, type, isRequire, questionDesc){
        this.idx = idx,
            this.type = type
        this.isRequire = isRequire
        this.questionDesc = questionDesc
    }

    generate(wrapper){
        throw "必须集成此方法"
    }

    getValue(){
        throw "必须集成此方法"
    }

    _label(){
        let label = document.createElement("span");
        label.innerText = this.idx + ". " + this.questionDesc
        return label
    }
}