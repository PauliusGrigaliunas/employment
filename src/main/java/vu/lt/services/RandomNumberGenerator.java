package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped
public class RandomNumberGenerator implements Serializable {

    @Futureable
    public Future<Integer> generateCandidateNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedJerseyNumber = new Random().nextInt(100);
        return new AsyncResult<>(generatedJerseyNumber);
    }
}
