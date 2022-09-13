package org.acme.panache;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;

@ApplicationScoped
public class PriceStorage {

    @Incoming("prices")
    @ActivateRequestContext
    Uni<Void> store(int priceInUsd) {
        Price price = new Price();
        price.value = priceInUsd;
        return Panache.withTransaction(price::persist)
                .replaceWithVoid();

    }

}
