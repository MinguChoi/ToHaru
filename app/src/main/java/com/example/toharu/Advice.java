package com.example.toharu;

public class Advice {
    private String mood;
    private String advice;

<<<<<<< Updated upstream
    public Advice() { }
=======
    public Advice(){ }
>>>>>>> Stashed changes

    public Advice(String mood, String advice){
        this.mood = mood;
        this.advice = advice;
    }

    public String getMood(){return mood;}
    public String getAdvice(){return advice;}
}
