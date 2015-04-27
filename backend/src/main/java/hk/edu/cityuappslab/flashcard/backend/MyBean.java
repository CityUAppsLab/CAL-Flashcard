package hk.edu.cityuappslab.flashcard.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The object model for the data we are sending through endpoints
 */
@Entity
public class MyBean {

    @Id
    Long id;
    private String myData;
    private int age;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

}