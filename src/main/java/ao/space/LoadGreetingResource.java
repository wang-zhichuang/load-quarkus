package ao.space;

import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.config.ConfigMapping;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class LoadGreetingResource {

    @ConfigMapping(prefix = "greeting")
    interface GreetingConfig {
        String imperative();
        String reactive();
    }

    @Inject
    GreetingConfig greeting;

    @GET
    @Path("imperative")
    @Produces(MediaType.TEXT_PLAIN)
    public String imperativeGreeting() {
        return greeting.imperative();
    }

    @GET
    @Path("virtual")
    @Produces(MediaType.TEXT_PLAIN)
    public String virtualGreeting() {
        return greeting.imperative();
    }

    @GET
    @Path("reactive")
    @Produces(MediaType.TEXT_PLAIN)
    @RunOnVirtualThread
    public Uni<String> reactiveGreeting() {
        return Uni.createFrom().item(greeting.reactive());
    }

}
