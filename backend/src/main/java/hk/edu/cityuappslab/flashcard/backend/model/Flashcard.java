package hk.edu.cityuappslab.flashcard.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by kinnimew on 21/4/15.
 */

@Entity
public class Flashcard {

    @Id private Long id;
    private String question;
    private String answer;
    @Index private Date created;

    public Flashcard(){
        this.created = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreated() {
        return created;
    }
}
