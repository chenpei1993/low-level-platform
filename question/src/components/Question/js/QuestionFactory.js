import InputQuestion from "./InputQuestion"
import SingleCheckQuestion from "./SingleCheckQuestion"
import MultiCheckQuestion from "./MultiCheckQuestion"

export class QuestionFactory{
    static INPUT_QUESTION = 1
    static SINGLE_CHECK_QUESTION = 2
    static MULTI_CHECK_QUESTION = 3

    static getByType(question){
        if(QuestionFactory.INPUT_QUESTION === question.type){
            return new InputQuestion(question)
        }else if(QuestionFactory.SINGLE_CHECK_QUESTION === question.type){
            return new SingleCheckQuestion(question)
        }else if(QuestionFactory.MULTI_CHECK_QUESTION === question.type){
            return new MultiCheckQuestion(question)
        }else{
            throw "未定义类型"
        }
    }

    static getValueByType(question, arr){
        if(QuestionFactory.INPUT_QUESTION === question.type){
            return arr[0].value;
        }else if(QuestionFactory.SINGLE_CHECK_QUESTION === question.type){
            let value = ""
            arr.forEach(e => {
                if(e.checked){
                    value = e.value
                }
            })
            return value;
        }else if(QuestionFactory.MULTI_CHECK_QUESTION === question.type){
            let values = []
            arr.forEach(e => {
                if(e.checked){
                    values.push(e.value)
                }
            })
            return values.join(",")
        }else{
            throw "未定义类型"
        }
    }
}