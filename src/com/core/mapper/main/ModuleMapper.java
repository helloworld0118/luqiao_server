package com.core.mapper.main;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.core.entity.main.Module;

public interface ModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module
     *
     * @mbg.generated Mon Dec 11 18:18:50 CST 2017
     */
    @Delete({
        "delete from luqiao.module",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module
     *
     * @mbg.generated Mon Dec 11 18:18:50 CST 2017
     */
    @Insert({
        "insert into luqiao.module (id, name, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Module record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module
     *
     * @mbg.generated Mon Dec 11 18:18:50 CST 2017
     */
    @Select({
        "select",
        "id, name, remark",
        "from luqiao.module",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Module selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module
     *
     * @mbg.generated Mon Dec 11 18:18:50 CST 2017
     */
    @Select({
        "select",
        "id, name, remark",
        "from luqiao.module"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Module> selectAll();
    
    
    @Select({
        "select",
        "id, name, remark",
        "from luqiao.module where id in (${ids})"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Module> selectByIds(@Param("ids") String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table module
     *
     * @mbg.generated Mon Dec 11 18:18:50 CST 2017
     */
    @Update({
        "update luqiao.module",
        "set name = #{name,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Module record);
}