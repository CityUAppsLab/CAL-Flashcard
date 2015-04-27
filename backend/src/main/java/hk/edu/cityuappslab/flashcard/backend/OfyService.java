package hk.edu.cityuappslab.flashcard.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import hk.edu.cityuappslab.flashcard.backend.model.Flashcard;

/**
 * Created by kinnimew on 20/4/15.
 */
public class OfyService {

    //https://github.com/objectify/objectify

    static {
        ObjectifyService.register(MyBean.class);
        ObjectifyService.register(Flashcard.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
