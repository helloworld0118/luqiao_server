package com.core.mapper;

import com.core.entity.Menu;
import com.core.entity.MenuExample;
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

public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @SelectProvider(type=MenuSqlProvider.class, method="countByExample")
    long countByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @DeleteProvider(type=MenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Delete({
        "delete from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Insert({
        "insert into menu (id, name, ",
        "parent, type, url, ",
        "icon, remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{parent,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, #{url,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @SelectProvider(type=MenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent", property="parent", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Select({
        "select",
        "id, name, parent, type, url, icon, remark",
        "from menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent", property="parent", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Menu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Update({
        "update menu",
        "set name = #{name,jdbcType=VARCHAR},",
          "parent = #{parent,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menu record);
}