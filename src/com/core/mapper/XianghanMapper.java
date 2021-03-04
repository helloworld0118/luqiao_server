package com.core.mapper;

import com.core.entity.Xianghan;
import com.core.entity.XianghanExample;
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

public interface XianghanMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@SelectProvider(type = XianghanSqlProvider.class, method = "countByExample")
	long countByExample(XianghanExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@DeleteProvider(type = XianghanSqlProvider.class, method = "deleteByExample")
	int deleteByExample(XianghanExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@Delete({ "delete from xianghan", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@Insert({ "insert into xianghan (id, project, ", "node, childNode, ", "col2, col3, col4, ", "col5, col6, col7, ",
			"col8, col9, col10, ", "col11, col12, col13, ", "col14, col15, col16, ", "col17, col18, col19, ",
			"col20, col21, col22, ", "col23, col24, col25, ", "col26, create_date, ", "remark)",
			"values (#{id,jdbcType=INTEGER}, #{project,jdbcType=INTEGER}, ",
			"#{node,jdbcType=INTEGER}, #{childnode,jdbcType=INTEGER}, ",
			"#{col2,jdbcType=VARCHAR}, #{col3,jdbcType=VARCHAR}, #{col4,jdbcType=VARCHAR}, ",
			"#{col5,jdbcType=VARCHAR}, #{col6,jdbcType=VARCHAR}, #{col7,jdbcType=VARCHAR}, ",
			"#{col8,jdbcType=VARCHAR}, #{col9,jdbcType=VARCHAR}, #{col10,jdbcType=VARCHAR}, ",
			"#{col11,jdbcType=VARCHAR}, #{col12,jdbcType=VARCHAR}, #{col13,jdbcType=VARCHAR}, ",
			"#{col14,jdbcType=VARCHAR}, #{col15,jdbcType=VARCHAR}, #{col16,jdbcType=VARCHAR}, ",
			"#{col17,jdbcType=VARCHAR}, #{col18,jdbcType=VARCHAR}, #{col19,jdbcType=VARCHAR}, ",
			"#{col20,jdbcType=VARCHAR}, #{col21,jdbcType=VARCHAR}, #{col22,jdbcType=VARCHAR}, ",
			"#{col23,jdbcType=VARCHAR}, #{col24,jdbcType=VARCHAR}, #{col25,jdbcType=VARCHAR}, ",
			"#{col26,jdbcType=VARCHAR}, #{createDate,jdbcType=VARCHAR}, ", "#{remark,jdbcType=VARCHAR})" })
	int insert(Xianghan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@Options(useGeneratedKeys = true)
	@InsertProvider(type = XianghanSqlProvider.class, method = "insertSelective")
	int insertSelective(Xianghan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@SelectProvider(type = XianghanSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "project", property = "project", jdbcType = JdbcType.INTEGER),
			@Result(column = "node", property = "node", jdbcType = JdbcType.INTEGER),
			@Result(column = "childNode", property = "childnode", jdbcType = JdbcType.INTEGER),
			@Result(column = "col2", property = "col2", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col3", property = "col3", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col4", property = "col4", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col5", property = "col5", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col6", property = "col6", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col7", property = "col7", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col8", property = "col8", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col9", property = "col9", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col10", property = "col10", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col11", property = "col11", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col12", property = "col12", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col13", property = "col13", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col14", property = "col14", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col15", property = "col15", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col16", property = "col16", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col17", property = "col17", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col18", property = "col18", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col19", property = "col19", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col20", property = "col20", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col21", property = "col21", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col22", property = "col22", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col23", property = "col23", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col24", property = "col24", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col25", property = "col25", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col26", property = "col26", jdbcType = JdbcType.VARCHAR),
			@Result(column = "create_date", property = "createDate", jdbcType = JdbcType.VARCHAR),
			@Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR) })
	List<Xianghan> selectByExample(XianghanExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@Select({ "select", "id, project, node, childNode, col2, col3, col4, col5, col6, col7, col8, col9, ",
			"col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, ",
			"col21, col22, col23, col24, col25, col26, create_date, remark", "from xianghan",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "project", property = "project", jdbcType = JdbcType.INTEGER),
			@Result(column = "node", property = "node", jdbcType = JdbcType.INTEGER),
			@Result(column = "childNode", property = "childnode", jdbcType = JdbcType.INTEGER),
			@Result(column = "col2", property = "col2", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col3", property = "col3", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col4", property = "col4", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col5", property = "col5", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col6", property = "col6", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col7", property = "col7", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col8", property = "col8", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col9", property = "col9", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col10", property = "col10", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col11", property = "col11", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col12", property = "col12", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col13", property = "col13", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col14", property = "col14", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col15", property = "col15", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col16", property = "col16", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col17", property = "col17", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col18", property = "col18", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col19", property = "col19", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col20", property = "col20", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col21", property = "col21", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col22", property = "col22", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col23", property = "col23", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col24", property = "col24", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col25", property = "col25", jdbcType = JdbcType.VARCHAR),
			@Result(column = "col26", property = "col26", jdbcType = JdbcType.VARCHAR),
			@Result(column = "create_date", property = "createDate", jdbcType = JdbcType.VARCHAR),
			@Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR) })
	Xianghan selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@UpdateProvider(type = XianghanSqlProvider.class, method = "updateByExampleSelective")
	int updateByExampleSelective(@Param("record") Xianghan record, @Param("example") XianghanExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@UpdateProvider(type = XianghanSqlProvider.class, method = "updateByExample")
	int updateByExample(@Param("record") Xianghan record, @Param("example") XianghanExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@UpdateProvider(type = XianghanSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(Xianghan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table xianghan
	 * @mbg.generated  Wed Jan 17 19:31:18 CST 2018
	 */
	@Update({ "update xianghan", "set project = #{project,jdbcType=INTEGER},", "node = #{node,jdbcType=INTEGER},",
			"childNode = #{childnode,jdbcType=INTEGER},", "col2 = #{col2,jdbcType=VARCHAR},",
			"col3 = #{col3,jdbcType=VARCHAR},", "col4 = #{col4,jdbcType=VARCHAR},", "col5 = #{col5,jdbcType=VARCHAR},",
			"col6 = #{col6,jdbcType=VARCHAR},", "col7 = #{col7,jdbcType=VARCHAR},", "col8 = #{col8,jdbcType=VARCHAR},",
			"col9 = #{col9,jdbcType=VARCHAR},", "col10 = #{col10,jdbcType=VARCHAR},",
			"col11 = #{col11,jdbcType=VARCHAR},", "col12 = #{col12,jdbcType=VARCHAR},",
			"col13 = #{col13,jdbcType=VARCHAR},", "col14 = #{col14,jdbcType=VARCHAR},",
			"col15 = #{col15,jdbcType=VARCHAR},", "col16 = #{col16,jdbcType=VARCHAR},",
			"col17 = #{col17,jdbcType=VARCHAR},", "col18 = #{col18,jdbcType=VARCHAR},",
			"col19 = #{col19,jdbcType=VARCHAR},", "col20 = #{col20,jdbcType=VARCHAR},",
			"col21 = #{col21,jdbcType=VARCHAR},", "col22 = #{col22,jdbcType=VARCHAR},",
			"col23 = #{col23,jdbcType=VARCHAR},", "col24 = #{col24,jdbcType=VARCHAR},",
			"col25 = #{col25,jdbcType=VARCHAR},", "col26 = #{col26,jdbcType=VARCHAR},",
			"create_date = #{createDate,jdbcType=VARCHAR},", "remark = #{remark,jdbcType=VARCHAR}",
			"where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(Xianghan record);
}