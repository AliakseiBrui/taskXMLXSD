package com.epam.task4.factory;

import com.epam.task4.entity.AnswerType;
import com.epam.task4.entity.Answer;

public class AnswerFactory {

    private AnswerFactory(){}

    public static Answer createAnswer(AnswerType answerType, String answerPage){

        return new Answer(answerType,answerPage);
    }
}
