class InputQuestion extends Question{
    constructor(question){
        super(question.idx, question.type, question.isRequire, question.questionDesc)
    }

    generate(wrapper){
        let divElement = document.createElement("div")
        divElement.appendChild(super._label())
        divElement.classList.add("question-wrapper")
        this.inputElement = document.createElement("input")
        divElement.appendChild(this.inputElement)
        wrapper.appendChild(divElement)
    }

    getValue(){
        if(this.inputElement != null){
            return this.inputElement.innerText
        }
        return ""
    }
}