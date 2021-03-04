package com.core.mapper;

import com.core.entity.BasePriceType;
import com.core.entity.BasePriceTypeExample;
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

public interface BasePriceTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @SelectProvider(type=BasePriceTypeSqlProvider.class, method="countByExample")
    long countByExample(BasePriceTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @DeleteProvider(type=BasePriceTypeSqlProvider.class, method="deleteByExample")
    int deleteByExample(BasePriceTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Delete({
        "delete from base_price_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Insert({
        "insert into base_price_type (id, name, ",
        "type, order_num, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{orderNum,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(BasePriceType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @InsertProvider(type=BasePriceTypeSqlProvider.class, method="insertSelective")
    int insertSelective(BasePriceType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @SelectProvider(type=BasePriceTypeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<BasePriceType> selectByExample(BasePriceTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Select({
        "select",
        "id, name, type, order_num, remark",
        "from ${database}.base_price_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    BasePriceType selectByPrimaryKey(@Param("database")String database,@Param("id") Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=BasePriceTypeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BasePriceType record, @Param("example") BasePriceTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=BasePriceTypeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BasePriceType record, @Param("example") BasePriceTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @UpdateProvider(type=BasePriceTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BasePriceType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_price_type
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    @Update({
        "update base_price_type",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "order_num = #{orderNum,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BasePriceType record);
}