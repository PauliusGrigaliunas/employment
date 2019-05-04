package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.RandomNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateCandidateNumber implements Serializable {
    @Inject
    RandomNumberGenerator randomNumberGenerator;

    private Future<Integer> randomNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewCandidateNumber() {
        randomNumberGenerationTask = randomNumberGenerator.generateCandidateNumber();
        return "index?";
    }

    public boolean isCandidateGenerationRunning() {
        return randomNumberGenerationTask != null && !randomNumberGenerationTask.isDone();
    }

    public String getCandidateGenerationStatus() throws ExecutionException, InterruptedException {
        if (randomNumberGenerationTask == null) {
            return "Please press to get a number ";
        } else if (isCandidateGenerationRunning()) {
            return "Result generation in progress ...";
        }
        return "Suggested number: " + randomNumberGenerationTask.get();
    }
}