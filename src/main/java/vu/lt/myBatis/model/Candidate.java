package vu.lt.myBatis.model;

public class Candidate {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CANDIDATE.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CANDIDATE.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CANDIDATE.ID
     *
     * @return the value of PUBLIC.CANDIDATE.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CANDIDATE.ID
     *
     * @param id the value for PUBLIC.CANDIDATE.ID
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CANDIDATE.NAME
     *
     * @return the value of PUBLIC.CANDIDATE.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CANDIDATE.NAME
     *
     * @param name the value for PUBLIC.CANDIDATE.NAME
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    public void setName(String name) {
        this.name = name;
    }
}