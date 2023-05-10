export default class Question{
    constructor(idx, type, isRequired, questionDesc){
        this.idx = idx
        this.type = type
        this.isRequired = isRequired
        this.questionDesc = questionDesc
    }

    generate(wrapper){
        throw "必须集成此方法"
    }

    getValue(){
        throw "必须集成此方法"
    }

    _label(){
        let label = document.createElement("span")
        if(this.isRequired){
            let requiredElement = document.createElement("span")
            requiredElement.innerText = "*"
            requiredElement.classList.add("question-required")
            label.appendChild(requiredElement)
        }
        let _label = document.createElement("span")
        _label.innerText = this.idx + ". " + this.questionDesc
        label.appendChild(_label)
        return label
    }
}