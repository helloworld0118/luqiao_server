package com.core.mapper;

import com.core.entity.BaseType;
import com.core.entity.BaseTypeExample;
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

public interface BaseTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @SelectProvider(type=BaseTypeSqlProvider.class, method="countByExample")
    long countByExample(BaseTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @DeleteProvider(type=BaseTypeSqlProvider.class, method="deleteByExample")
    int deleteByExample(BaseTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @Delete({
        "delete from base_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @Insert({
        "insert into base_type (id, name, ",
        "type, remark, order_num)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, #{orderNum,jdbcType=INTEGER})"
    })
    int insert(BaseType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @InsertProvider(type=BaseTypeSqlProvider.class, method="insertSelective")
    int insertSelective(BaseType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @SelectProvider(type=BaseTypeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER)
    })
    List<BaseType> selectByExample(BaseTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @Select({
        "select",
        "id, name, type, remark, order_num",
        "from ${database}.base_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER)
    })
    BaseType selectByPrimaryKey(@Param("database")String database,@Param("id") Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @UpdateProvider(type=BaseTypeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BaseType record, @Param("example") BaseTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @UpdateProvider(type=BaseTypeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BaseType record, @Param("example") BaseTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @UpdateProvider(type=BaseTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BaseType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_type
     *
     * @mbg.generated Mon Dec 18 10:30:11 CST 2017
     */
    @Update({
        "update base_type",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "order_num = #{orderNum,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BaseType record);
}