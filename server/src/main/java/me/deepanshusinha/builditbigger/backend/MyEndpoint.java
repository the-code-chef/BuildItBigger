package me.deepanshusinha.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import me.deepanshusinha.android.joke_provider.JokeProvider;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.deepanshusinha.me",
                ownerName = "backend.builditbigger.deepanshusinha.me"
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getJokeFromBackend")
    public MyBean getJokeFromBackend() {
        MyBean response = new MyBean();
        JokeProvider joker = new JokeProvider();
        response.setData(joker.getRandomJoke());

        return response;
    }

}
