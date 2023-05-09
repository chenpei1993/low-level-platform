import Question from "./Question"

export default class SingleCheckQuestion extends Question{
    constructor(question){
        super(question.idx, question.type, question.isRequired, question.questionDesc)
        this.options = question.options
    }

    generate(wrapper){
        if(this.options == null){
            return
        }
        let divElement = document.createElement("div")
        divElement.classList.add("question-wrapper")
        divElement.appendChild(super._label())
        for(let i = 0; i < this.options.length; i++){

            let wrapperElement = document.createElement("div")

            let inputElement = document.createElement("input")
            inputElement.type = "radio"
            inputElement.value = this.options[i]
            inputElement.name = this.idx
            inputElement.idx = this.idx
            wrapperElement.appendChild(inputElement)

            let spanElement = document.createElement("span")
            spanElement.innerText = this.options[i]
            wrapperElement.appendChild(spanElement)

            divElement.appendChild(wrapperElement)
        }
        wrapper.appendChild(divElement)
    }
}

