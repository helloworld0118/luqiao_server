package com.core.mapper;

import com.core.entity.ProjectStaffRole;
import com.core.entity.ProjectStaffRoleExample;
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

public interface ProjectStaffRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @SelectProvider(type=ProjectStaffRoleSqlProvider.class, method="countByExample")
    long countByExample(ProjectStaffRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @DeleteProvider(type=ProjectStaffRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProjectStaffRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @Delete({
        "delete from project_staff_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @Insert({
        "insert into project_staff_role (id, staff, ",
        "role, project)",
        "values (#{id,jdbcType=INTEGER}, #{staff,jdbcType=INTEGER}, ",
        "#{role,jdbcType=INTEGER}, #{project,jdbcType=INTEGER})"
    })
    int insert(ProjectStaffRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @InsertProvider(type=ProjectStaffRoleSqlProvider.class, method="insertSelective")
    int insertSelective(ProjectStaffRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @SelectProvider(type=ProjectStaffRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="staff", property="staff", jdbcType=JdbcType.INTEGER),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER)
    })
    List<ProjectStaffRole> selectByExample(ProjectStaffRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @Select({
        "select",
        "id, staff, role, project",
        "from project_staff_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="staff", property="staff", jdbcType=JdbcType.INTEGER),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER)
    })
    ProjectStaffRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @UpdateProvider(type=ProjectStaffRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ProjectStaffRole record, @Param("example") ProjectStaffRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @UpdateProvider(type=ProjectStaffRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ProjectStaffRole record, @Param("example") ProjectStaffRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @UpdateProvider(type=ProjectStaffRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProjectStaffRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_staff_role
     *
     * @mbg.generated Thu Dec 14 18:44:41 CST 2017
     */
    @Update({
        "update project_staff_role",
        "set staff = #{staff,jdbcType=INTEGER},",
          "role = #{role,jdbcType=INTEGER},",
          "project = #{project,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProjectStaffRole record);
}