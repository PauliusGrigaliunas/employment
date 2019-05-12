package vu.lt.rest;

import lombok.val;
import vu.lt.entities.Candidate;
import vu.lt.entities.ICandidate;
import vu.lt.persistence.CandidatesDAO;

import javax.annotation.Nonnull;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeesRestServices {


    @PersistenceContext
    private EntityManager em;
    @Inject private CandidatesDAO candidatesDAO;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ICandidate> getAll() {
        return candidatesDAO.loadAll();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{candidateId}")
    public Response find(@PathParam("candidateId") Integer candidateId) {
        ICandidate candidate = candidatesDAO.findOne(candidateId);
        if (candidate == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else return Response.ok(candidate).build();
    }

    @POST
    @Transactional
    @Path("/createNew")
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/update/{id}")
    public Response add(@PathParam("id") int id, final Candidate candidate) {
        val dbCandidate = em.find(Candidate.class, id);
        if (dbCandidate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
            //throw new IllegalArgumentException("candidate id " + id + "not found");
        }
        else {
            dbCandidate.setName(candidate.getName());
            em.merge(dbCandidate);
            return Response.ok(candidate).build();
        }
    }
}
