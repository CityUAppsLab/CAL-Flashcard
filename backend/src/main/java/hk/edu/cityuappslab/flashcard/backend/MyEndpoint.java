/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package hk.edu.cityuappslab.flashcard.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import static hk.edu.cityuappslab.flashcard.backend.OfyService.ofy;


/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.flashcard.cityuappslab.edu.hk", ownerName = "backend.flashcard.cityuappslab.edu.hk", packagePath = ""))
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name, @Named("age") int age) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);
        response.setAge(age);

        ofy().save().entity(response).now();

        return response;
    }

    @ApiMethod(name = "addFreeRider", httpMethod = ApiMethod.HttpMethod.GET)
    public void addFreeRider(@Named("name") String name){
        MyBean response = new MyBean();
        response.setData(name);

        ofy().save().entity(response).now();

    }
}
