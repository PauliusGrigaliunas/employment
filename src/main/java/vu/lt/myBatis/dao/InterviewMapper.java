package vu.lt.myBatis.dao;

import org.mybatis.cdi.Mapper;
import vu.lt.myBatis.model.Interview;

import java.util.List;

@Mapper
public interface InterviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.INTERVIEW
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.INTERVIEW
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    int insert(Interview record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.INTERVIEW
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    Interview selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.INTERVIEW
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    List<Interview> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.INTERVIEW
     *
     * @mbg.generated Sat Mar 30 20:43:11 EET 2019
     */
    int updateByPrimaryKey(Interview record);
}