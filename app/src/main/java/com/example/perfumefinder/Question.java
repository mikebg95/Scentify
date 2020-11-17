package com.example.perfumefinder;

public class Question {
    private String question;
    private boolean entry;
    private String[] options;
    private String subject;

    // constructor for multiple choice
    public Question(String subject, String question, String[] options) {
        this.subject = subject;
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getSubject() {
        return subject;
    }
}
