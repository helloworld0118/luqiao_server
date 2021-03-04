package com.core.mapper;

import com.core.entity.MechanicsProjectOwner;
import com.core.entity.MechanicsProjectOwnerExample;
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

public interface MechanicsProjectOwnerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @SelectProvider(type=MechanicsProjectOwnerSqlProvider.class, method="countByExample")
    long countByExample(MechanicsProjectOwnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @DeleteProvider(type=MechanicsProjectOwnerSqlProvider.class, method="deleteByExample")
    int deleteByExample(MechanicsProjectOwnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @Delete({
        "delete from ${database}.mechanics_project_owner",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("database") String database,@Param("id")Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @Insert({
        "insert into mechanics_project_owner (id, project, ",
        "mechanics, unit_price, ",
        "type, owner, unit_price_type)",
        "values (#{id,jdbcType=INTEGER}, #{project,jdbcType=INTEGER}, ",
        "#{mechanics,jdbcType=INTEGER}, #{unitPrice,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{owner,jdbcType=INTEGER}, #{unitPriceType,jdbcType=INTEGER})"
    })
    int insert(MechanicsProjectOwner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @Options(useGeneratedKeys=true)
    @InsertProvider(type=MechanicsProjectOwnerSqlProvider.class, method="insertSelective")
    int insertSelective(MechanicsProjectOwner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @SelectProvider(type=MechanicsProjectOwnerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="mechanics", property="mechanics", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price", property="unitPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="owner", property="owner", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price_type", property="unitPriceType", jdbcType=JdbcType.INTEGER)
    })
    List<MechanicsProjectOwner> selectByExample(MechanicsProjectOwnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @Select({
        "select",
        "id, project, mechanics, unit_price, type, owner, unit_price_type",
        "from mechanics_project_owner",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="mechanics", property="mechanics", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price", property="unitPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="owner", property="owner", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price_type", property="unitPriceType", jdbcType=JdbcType.INTEGER)
    })
    MechanicsProjectOwner selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @UpdateProvider(type=MechanicsProjectOwnerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MechanicsProjectOwner record, @Param("example") MechanicsProjectOwnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @UpdateProvider(type=MechanicsProjectOwnerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MechanicsProjectOwner record, @Param("example") MechanicsProjectOwnerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @UpdateProvider(type=MechanicsProjectOwnerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MechanicsProjectOwner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mechanics_project_owner
     *
     * @mbg.generated Thu Dec 21 16:06:03 CST 2017
     */
    @Update({
        "update mechanics_project_owner",
        "set project = #{project,jdbcType=INTEGER},",
          "mechanics = #{mechanics,jdbcType=INTEGER},",
          "unit_price = #{unitPrice,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "owner = #{owner,jdbcType=INTEGER},",
          "unit_price_type = #{unitPriceType,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MechanicsProjectOwner record);
}