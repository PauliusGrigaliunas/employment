package vu.lt.myBatis.model;

public class Interview {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.INTERVIEW.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.INTERVIEW.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.INTERVIEW.CANDIDATE_ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    private Integer candidateId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.INTERVIEW.ID
     *
     * @return the value of PUBLIC.INTERVIEW.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.INTERVIEW.ID
     *
     * @param id the value for PUBLIC.INTERVIEW.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.INTERVIEW.NAME
     *
     * @return the value of PUBLIC.INTERVIEW.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.INTERVIEW.NAME
     *
     * @param name the value for PUBLIC.INTERVIEW.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.INTERVIEW.CANDIDATE_ID
     *
     * @return the value of PUBLIC.INTERVIEW.CANDIDATE_ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public Integer getCandidateId() {
        return candidateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.INTERVIEW.CANDIDATE_ID
     *
     * @param candidateId the value for PUBLIC.INTERVIEW.CANDIDATE_ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }
}