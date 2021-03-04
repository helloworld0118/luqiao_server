package com.core.mapper;

import com.core.entity.LoanInfo;
import com.core.entity.LoanInfoExample.Criteria;
import com.core.entity.LoanInfoExample.Criterion;
import com.core.util.Utils;
import com.core.entity.LoanInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class LoanInfoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String countByExample(LoanInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("loan_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String deleteByExample(LoanInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("loan_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String insertSelective(LoanInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO(Utils.getDatabase()+".loan_info");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=INTEGER}");
        }
        
        if (record.getPerson() != null) {
            sql.VALUES("person", "#{person,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=INTEGER}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.VALUES("date", "#{date,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.VALUES("department", "#{department,jdbcType=INTEGER}");
        }
        
        if (record.getPresenter() != null) {
            sql.VALUES("presenter", "#{presenter,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.VALUES("reason", "#{reason,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String selectByExample(LoanInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("project");
        sql.SELECT("person");
        sql.SELECT("price");
        sql.SELECT("create_date");
        sql.SELECT("date");
        sql.SELECT("department");
        sql.SELECT("presenter");
        sql.SELECT("reason");
        sql.SELECT("remark");
        sql.FROM(Utils.getDatabase()+".loan_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        LoanInfo record = (LoanInfo) parameter.get("record");
        LoanInfoExample example = (LoanInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("loan_info");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=INTEGER}");
        }
        
        if (record.getPerson() != null) {
            sql.SET("person = #{record.person,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=INTEGER}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{record.date,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{record.department,jdbcType=INTEGER}");
        }
        
        if (record.getPresenter() != null) {
            sql.SET("presenter = #{record.presenter,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.SET("reason = #{record.reason,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    
    
    public String selectModelByExample(@Param("project")int project) {
        
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT li.person as person,li.price as price,li.date  as date ,li.department as department ,li.reason as reason, dp.`name` as departmentName, sf.`name` as staffName  FROM "+Utils.getDatabase()+".loan_info as li,"+Utils.getDatabase()+".department as dp, "+Utils.getDatabase()+".staff as sf \r\n" + 
				"where li.presenter=sf.id and li.department = dp.id");
		sb.append(" and li.project = "+project);
		System.out.println(sb.toString());
         return sb.toString();
    }
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("loan_info");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=INTEGER}");
        sql.SET("person = #{record.person,jdbcType=VARCHAR}");
        sql.SET("price = #{record.price,jdbcType=INTEGER}");
        sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        sql.SET("date = #{record.date,jdbcType=VARCHAR}");
        sql.SET("department = #{record.department,jdbcType=INTEGER}");
        sql.SET("presenter = #{record.presenter,jdbcType=VARCHAR}");
        sql.SET("reason = #{record.reason,jdbcType=VARCHAR}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        
        LoanInfoExample example = (LoanInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    public String updateByPrimaryKeySelective(LoanInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("loan_info");
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=INTEGER}");
        }
        
        if (record.getPerson() != null) {
            sql.SET("person = #{person,jdbcType=VARCHAR}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=INTEGER}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{date,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{department,jdbcType=INTEGER}");
        }
        
        if (record.getPresenter() != null) {
            sql.SET("presenter = #{presenter,jdbcType=VARCHAR}");
        }
        
        if (record.getReason() != null) {
            sql.SET("reason = #{reason,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan_info
     *
     * @mbg.generated Thu Dec 28 15:11:52 CST 2017
     */
    protected void applyWhere(SQL sql, LoanInfoExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}