package com.core.mapper;

import com.core.entity.UnitTypeCombination;
import com.core.entity.UnitTypeCombinationExample.Criteria;
import com.core.entity.UnitTypeCombinationExample.Criterion;
import com.core.util.Utils;
import com.core.entity.UnitTypeCombinationExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class UnitTypeCombinationSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String countByExample(UnitTypeCombinationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM(Utils.getDatabase()+".unit_type_combination");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String deleteByExample(UnitTypeCombinationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM(Utils.getDatabase()+".unit_type_combination");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String insertSelective(UnitTypeCombination record) {
        SQL sql = new SQL();
        sql.INSERT_INTO(Utils.getDatabase()+".unit_type_combination");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getBaseType() != null) {
            sql.VALUES("base_type", "#{baseType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialSpec() != null) {
            sql.VALUES("material_spec", "#{materialSpec,jdbcType=INTEGER}");
        }
        
        if (record.getBasePriceType() != null) {
            sql.VALUES("base_price_type", "#{basePriceType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialType() != null) {
            sql.VALUES("material_type", "#{materialType,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String selectByExample(UnitTypeCombinationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("base_type");
        sql.SELECT("material_spec");
        sql.SELECT("base_price_type");
        sql.SELECT("material_type");
        sql.SELECT("remark");
        sql.FROM(Utils.getDatabase()+".unit_type_combination");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        UnitTypeCombination record = (UnitTypeCombination) parameter.get("record");
        UnitTypeCombinationExample example = (UnitTypeCombinationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE(Utils.getDatabase()+".unit_type_combination");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getBaseType() != null) {
            sql.SET("base_type = #{record.baseType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialSpec() != null) {
            sql.SET("material_spec = #{record.materialSpec,jdbcType=INTEGER}");
        }
        
        if (record.getBasePriceType() != null) {
            sql.SET("base_price_type = #{record.basePriceType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialType() != null) {
            sql.SET("material_type = #{record.materialType,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE(Utils.getDatabase()+".unit_type_combination");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("base_type = #{record.baseType,jdbcType=INTEGER}");
        sql.SET("material_spec = #{record.materialSpec,jdbcType=INTEGER}");
        sql.SET("base_price_type = #{record.basePriceType,jdbcType=INTEGER}");
        sql.SET("material_type = #{record.materialType,jdbcType=INTEGER}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        
        UnitTypeCombinationExample example = (UnitTypeCombinationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String updateByPrimaryKeySelective(UnitTypeCombination record) {
        SQL sql = new SQL();
        sql.UPDATE(Utils.getDatabase()+".unit_type_combination");
        
        if (record.getBaseType() != null) {
            sql.SET("base_type = #{baseType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialSpec() != null) {
            sql.SET("material_spec = #{materialSpec,jdbcType=INTEGER}");
        }
        
        if (record.getBasePriceType() != null) {
            sql.SET("base_price_type = #{basePriceType,jdbcType=INTEGER}");
        }
        
        if (record.getMaterialType() != null) {
            sql.SET("material_type = #{materialType,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected void applyWhere(SQL sql, UnitTypeCombinationExample example, boolean includeExamplePhrase) {
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