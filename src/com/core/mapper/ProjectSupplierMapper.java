package com.core.mapper;

import com.core.entity.ProjectSupplier;
import com.core.entity.ProjectSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ProjectSupplierMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @SelectProvider(type=ProjectSupplierSqlProvider.class, method="countByExample")
    long countByExample(ProjectSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @DeleteProvider(type=ProjectSupplierSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProjectSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @Delete({
        "delete from project_supplier",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @Insert({
        "insert into project_supplier (id, supplier, ",
        "project)",
        "values (#{id,jdbcType=INTEGER}, #{supplier,jdbcType=INTEGER}, ",
        "#{project,jdbcType=INTEGER})"
    })
    int insert(ProjectSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @InsertProvider(type=ProjectSupplierSqlProvider.class, method="insertSelective")
    int insertSelective(ProjectSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @SelectProvider(type=ProjectSupplierSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="supplier", property="supplier", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER)
    })
    List<ProjectSupplier> selectByExample(ProjectSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @Select({
        "select",
        "id, supplier, project",
        "from project_supplier",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="supplier", property="supplier", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER)
    })
    ProjectSupplier selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @UpdateProvider(type=ProjectSupplierSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ProjectSupplier record, @Param("example") ProjectSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @UpdateProvider(type=ProjectSupplierSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ProjectSupplier record, @Param("example") ProjectSupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @UpdateProvider(type=ProjectSupplierSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProjectSupplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_supplier
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    @Update({
        "update project_supplier",
        "set supplier = #{supplier,jdbcType=INTEGER},",
          "project = #{project,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProjectSupplier record);
}