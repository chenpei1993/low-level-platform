import Question from "./Question"

export default class InputQuestion extends Question{
    constructor(question){
        super(question.idx, question.type, question.required, question.questionDesc)
    }

    generate(wrapper){
        let divElement = document.createElement("div")
        divElement.appendChild(super._label())
        divElement.classList.add("question-wrapper")
        this.inputElement = document.createElement("input")
        this.inputElement.classList.add("question-input")
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