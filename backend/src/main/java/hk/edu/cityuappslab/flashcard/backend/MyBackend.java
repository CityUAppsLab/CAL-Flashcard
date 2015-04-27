package hk.edu.cityuappslab.flashcard.backend;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;

import java.util.List;

import javax.inject.Named;

import hk.edu.cityuappslab.flashcard.backend.model.Flashcard;

import static hk.edu.cityuappslab.flashcard.backend.OfyService.ofy;

/**
 * Created by kinnimew on 21/4/15.
 */

@Api(name = "myBackend", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.flashcard.cityuappslab.edu.hk", ownerName = "backend.flashcard.cityuappslab.edu.hk", packagePath = ""))
public class MyBackend {


    // Create
    @ApiMethod(name = "addFlashcard", httpMethod = ApiMethod.HttpMethod.POST)
    public Flashcard addFlashcard(@Named("question") String question, @Named("answer") String answer) {
        Flashcard flashcard = new Flashcard();
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);

        ofy().save().entity(flashcard).now();

        return flashcard;
    }

    // Retrieve
    @ApiMethod(name = "getAllFlashcard", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Flashcard> getAllFlashcard() {
        List<Flashcard> flashcards = ofy().load().type(Flashcard.class).order("-created").list();
        return flashcards;
    }

    // Delete
    @ApiMethod(name = "deleteFlashcard", httpMethod = ApiMethod.HttpMethod.DELETE)
    public Flashcard deleteFlashcard(@Named("flashcardId") Long flashcardId) throws NotFoundException {
        Flashcard flashcard = ofy().load().type(Flashcard.class).id(flashcardId).now();
        ofy().delete().type(Flashcard.class).id(flashcardId).now();

        return flashcard;
    }
}
