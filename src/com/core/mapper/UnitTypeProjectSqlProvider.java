package com.core.mapper;

import com.core.entity.UnitTypeProject;
import com.core.entity.UnitTypeProjectExample.Criteria;
import com.core.entity.UnitTypeProjectExample.Criterion;
import com.core.entity.UnitTypeProjectExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class UnitTypeProjectSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String countByExample(UnitTypeProjectExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("unit_type_project");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String deleteByExample(UnitTypeProjectExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("unit_type_project");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String insertSelective(UnitTypeProject record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("unit_type_project");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=INTEGER}");
        }
        
        if (record.getBaseId() != null) {
            sql.VALUES("base_id", "#{baseId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String selectByExample(UnitTypeProjectExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("project");
        sql.SELECT("base_id");
        sql.SELECT("type");
        sql.FROM("unit_type_project");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        UnitTypeProject record = (UnitTypeProject) parameter.get("record");
        UnitTypeProjectExample example = (UnitTypeProjectExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("unit_type_project");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=INTEGER}");
        }
        
        if (record.getBaseId() != null) {
            sql.SET("base_id = #{record.baseId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("unit_type_project");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=INTEGER}");
        sql.SET("base_id = #{record.baseId,jdbcType=INTEGER}");
        sql.SET("type = #{record.type,jdbcType=INTEGER}");
        
        UnitTypeProjectExample example = (UnitTypeProjectExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    public String updateByPrimaryKeySelective(UnitTypeProject record) {
        SQL sql = new SQL();
        sql.UPDATE("unit_type_project");
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=INTEGER}");
        }
        
        if (record.getBaseId() != null) {
            sql.SET("base_id = #{baseId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_project
     *
     * @mbg.generated Wed Dec 13 18:02:09 CST 2017
     */
    protected void applyWhere(SQL sql, UnitTypeProjectExample example, boolean includeExamplePhrase) {
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