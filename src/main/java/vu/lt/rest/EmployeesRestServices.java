package vu.lt.rest;

import lombok.val;
import vu.lt.entities.Candidate;
import vu.lt.persistence.CandidatesDAO;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeesRestServices {


    @PersistenceContext
    private EntityManager em;
    @Inject private CandidatesDAO candidatesDAO;



    @GET
    public List<Candidate> getAll() {
        return candidatesDAO.loadAll();
    }

    @GET
    @Path("/{candidateId}")
    public Candidate find(@PathParam("candidateId") Integer candidateId) {
        return em.find(Candidate.class, candidateId);
    }

    @POST
    @Transactional
    @Path("/createNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String newCandidate(@Nonnull Candidate candidate) {

        try {
            em.persist(candidate);
        }
        catch (Exception e) {
            return "required 'name'";
        }
        return candidate.getName();
    }

    @PUT
    @Consumes("application/json")
    @Transactional
    @Path("/update/{id}")
    public String add(@PathParam("id") int id, final Candidate candidate) {
        val dbCandidate = em.find(Candidate.class, id);
        if (dbCandidate == null) {
            throw new IllegalArgumentException("candidate id " + id + "not found");
        }
        dbCandidate.setName(candidate.getName());
        em.merge(dbCandidate);
        return dbCandidate.getName();
    }
}
