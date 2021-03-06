package com.core.mapper;

import com.core.entity.Expense;
import com.core.entity.ExpenseExample;
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

public interface ExpenseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@SelectProvider(type = ExpenseSqlProvider.class, method = "countByExample")
	long countByExample(ExpenseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@DeleteProvider(type = ExpenseSqlProvider.class, method = "deleteByExample")
	int deleteByExample(ExpenseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@Delete({ "delete from expense", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@Insert({ "insert into expense (id, project, ", "person, price, create_date, ", "date, bill_type, ",
			"price_type, department, ", "presenter_name, presenter, ", "remark)",
			"values (#{id,jdbcType=INTEGER}, #{project,jdbcType=INTEGER}, ",
			"#{person,jdbcType=VARCHAR}, #{price,jdbcType=INTEGER}, #{createDate,jdbcType=VARCHAR}, ",
			"#{date,jdbcType=VARCHAR}, #{billType,jdbcType=INTEGER}, ",
			"#{priceType,jdbcType=INTEGER}, #{department,jdbcType=INTEGER}, ",
			"#{presenterName,jdbcType=VARCHAR}, #{presenter,jdbcType=INTEGER}, ", "#{remark,jdbcType=VARCHAR})" })
	int insert(Expense record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@InsertProvider(type = ExpenseSqlProvider.class, method = "insertSelective")
	int insertSelective(Expense record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@SelectProvider(type = ExpenseSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "project", property = "project", jdbcType = JdbcType.INTEGER),
			@Result(column = "person", property = "person", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
			@Result(column = "create_date", property = "createDate", jdbcType = JdbcType.VARCHAR),
			@Result(column = "date", property = "date", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bill_type", property = "billType", jdbcType = JdbcType.INTEGER),
			@Result(column = "price_type", property = "priceType", jdbcType = JdbcType.INTEGER),
			@Result(column = "department", property = "department", jdbcType = JdbcType.INTEGER),
			@Result(column = "presenter_name", property = "presenterName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "presenter", property = "presenter", jdbcType = JdbcType.INTEGER),
			@Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR) })
	List<Expense> selectByExample(ExpenseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@Select({ "select", "id, project, person, price, create_date, date, bill_type, price_type, department, ",
			"presenter_name, presenter, remark", "from expense", "where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "project", property = "project", jdbcType = JdbcType.INTEGER),
			@Result(column = "person", property = "person", jdbcType = JdbcType.VARCHAR),
			@Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
			@Result(column = "create_date", property = "createDate", jdbcType = JdbcType.VARCHAR),
			@Result(column = "date", property = "date", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bill_type", property = "billType", jdbcType = JdbcType.INTEGER),
			@Result(column = "price_type", property = "priceType", jdbcType = JdbcType.INTEGER),
			@Result(column = "department", property = "department", jdbcType = JdbcType.INTEGER),
			@Result(column = "presenter_name", property = "presenterName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "presenter", property = "presenter", jdbcType = JdbcType.INTEGER),
			@Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR) })
	Expense selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@UpdateProvider(type = ExpenseSqlProvider.class, method = "updateByExampleSelective")
	int updateByExampleSelective(@Param("record") Expense record, @Param("example") ExpenseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@UpdateProvider(type = ExpenseSqlProvider.class, method = "updateByExample")
	int updateByExample(@Param("record") Expense record, @Param("example") ExpenseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@UpdateProvider(type = ExpenseSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(Expense record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table expense
	 * @mbg.generated  Thu Jan 25 10:19:25 CST 2018
	 */
	@Update({ "update expense", "set project = #{project,jdbcType=INTEGER},", "person = #{person,jdbcType=VARCHAR},",
			"price = #{price,jdbcType=INTEGER},", "create_date = #{createDate,jdbcType=VARCHAR},",
			"date = #{date,jdbcType=VARCHAR},", "bill_type = #{billType,jdbcType=INTEGER},",
			"price_type = #{priceType,jdbcType=INTEGER},", "department = #{department,jdbcType=INTEGER},",
			"presenter_name = #{presenterName,jdbcType=VARCHAR},", "presenter = #{presenter,jdbcType=INTEGER},",
			"remark = #{remark,jdbcType=VARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(Expense record);
}