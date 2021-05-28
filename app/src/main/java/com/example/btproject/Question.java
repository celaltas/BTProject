package com.example.btproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String questionContent;
    private String choiceA, choiceB, choiceC, choiceD;
    private String choiceTrue;
    private int id = 0;


    public Question(String questionContent, String choiceA, String choiceB, String choiceC, String choiceD, String choiceTrue) {
        this.questionContent = questionContent;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.choiceTrue = choiceTrue;
        id++;
    }

    public static ArrayList<Question> getQuestionArrayList() {
        ArrayList<Question> questionArrayList = new ArrayList<>();

        questionArrayList.add(new Question("What is the a animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the b animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the c animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the d animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the e animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the f animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the g animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the h animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the ı animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the i animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the j animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the k animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the l animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the m animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the n animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the o animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the p animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the r animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the s animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));
        questionArrayList.add(new Question("What is the t animal in the world?", "Turtle", "Cheetah", "Rabbit", "Leopard", "Cheetah"));


        return questionArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getChoiceTrue() {
        return choiceTrue;
    }

    public void setChoiceTrue(String choiceTrue) {
        this.choiceTrue = choiceTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionContent='" + questionContent + '\'' +
                ", choiceA='" + choiceA + '\'' +
                ", choiceB='" + choiceB + '\'' +
                ", choiceC='" + choiceC + '\'' +
                ", choiceD='" + choiceD + '\'' +
                ", choiceTrue='" + choiceTrue + '\'' +
                ", id=" + id +
                '}';
    }
}
