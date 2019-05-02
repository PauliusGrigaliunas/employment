package vu.lt.rest;

import vu.lt.entities.Candidate;
import vu.lt.persistence.CandidatesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeesRestServices extends HttpServlet {


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
    public String newCandidate(final Candidate candidate) {

        try {
            em.persist(candidate);
            if(candidate.getName().isEmpty()){
                throw new ServletException();
            }
        }catch (NullPointerException e){

            Response.serverError();
            //response.setStatus(HttpServletResponse.SC_CONFLICT);
            return e.toString();
        }
        catch (Exception e) {
            Response.serverError();
            return e.toString();
        }
        return candidate.toString();
    }
}
