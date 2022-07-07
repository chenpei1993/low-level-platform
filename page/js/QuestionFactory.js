class QuestionFactory{
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
}