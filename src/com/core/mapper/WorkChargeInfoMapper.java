package com.core.mapper;

import com.core.entity.WorkChargeInfo;
import com.core.entity.WorkChargeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface WorkChargeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @SelectProvider(type=WorkChargeInfoSqlProvider.class, method="countByExample")
    long countByExample(WorkChargeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @DeleteProvider(type=WorkChargeInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(WorkChargeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @Delete({
        "delete from ${database}.work_charge_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("database")String database,@Param("id")Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @Options(useGeneratedKeys=true)
    @Insert({
        "insert into work_charge_info (id, project, ",
        "foreman, worker_type, ",
        "unit_price, unit_price_type, ",
        "create_date, update_date, ",
        "count, remark)",
        "values (#{id,jdbcType=INTEGER}, #{project,jdbcType=INTEGER}, ",
        "#{foreman,jdbcType=INTEGER}, #{workerType,jdbcType=INTEGER}, ",
        "#{unitPrice,jdbcType=INTEGER}, #{unitPriceType,jdbcType=INTEGER}, ",
        "#{createDate,jdbcType=VARCHAR}, #{updateDate,jdbcType=VARCHAR}, ",
        "#{count,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(WorkChargeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    
    @Options(useGeneratedKeys=true)
    @InsertProvider(type=WorkChargeInfoSqlProvider.class, method="insertSelective")
    int insertSelective(WorkChargeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @SelectProvider(type=WorkChargeInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="foreman", property="foreman", jdbcType=JdbcType.INTEGER),
        @Result(column="worker_type", property="workerType", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price", property="unitPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price_type", property="unitPriceType", jdbcType=JdbcType.INTEGER),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<WorkChargeInfo> selectByExample(WorkChargeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @Select({
        "select",
        "id, project, foreman, worker_type, unit_price, unit_price_type, create_date, ",
        "update_date, count, remark",
        "from work_charge_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="foreman", property="foreman", jdbcType=JdbcType.INTEGER),
        @Result(column="worker_type", property="workerType", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price", property="unitPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price_type", property="unitPriceType", jdbcType=JdbcType.INTEGER),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    WorkChargeInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @UpdateProvider(type=WorkChargeInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WorkChargeInfo record, @Param("example") WorkChargeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @UpdateProvider(type=WorkChargeInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") WorkChargeInfo record, @Param("example") WorkChargeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @UpdateProvider(type=WorkChargeInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WorkChargeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table work_charge_info
     *
     * @mbg.generated Tue Jan 23 16:49:08 CST 2018
     */
    @Update({
        "update work_charge_info",
        "set project = #{project,jdbcType=INTEGER},",
          "foreman = #{foreman,jdbcType=INTEGER},",
          "worker_type = #{workerType,jdbcType=INTEGER},",
          "unit_price = #{unitPrice,jdbcType=INTEGER},",
          "unit_price_type = #{unitPriceType,jdbcType=INTEGER},",
          "create_date = #{createDate,jdbcType=VARCHAR},",
          "update_date = #{updateDate,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WorkChargeInfo record);
}