class MultiCheckQuestion extends Question{
    constructor(question){
        super(question.idx, question.type, question.isRequire, question.questionDesc)
        this.options = question.questionOptions
    }

    generate(wrapper){
        this.divElement = document.createElement("div")
        this.divElement.classList.add("question-wrapper")
        this.divElement.appendChild(super._label())
        for(let i = 0; i < this.options.length; i++){

            let wrapperElement = document.createElement("div")

            let inputElement = document.createElement("input")
            inputElement.type = "checkbox"
            inputElement.value = this.options[i]
            wrapperElement.appendChild(inputElement)

            let spanElement = document.createElement("span")
            spanElement.innerText = this.options[i]
            wrapperElement.appendChild(spanElement)

            this.divElement.appendChild(wrapperElement)
        }
        wrapper.appendChild(this.divElement)
    }

    getValue(){
        if(this.inputElement != null){
            return this.inputElement.innerText
        }
        return ""
    }
}