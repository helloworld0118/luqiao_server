package com.core.mapper;

import com.core.entity.Oil;
import com.core.entity.OilExample.Criteria;
import com.core.entity.OilExample.Criterion;
import com.core.util.Utils;
import com.core.entity.OilExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class OilSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String countByExample(OilExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("oil");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String deleteByExample(OilExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("oil");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String insertSelective(Oil record) {
        SQL sql = new SQL();
        sql.INSERT_INTO(Utils.getDatabase()+".oil");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=INTEGER}");
        }
        
        if (record.getMechanicName() != null) {
            sql.VALUES("mechanic_name", "#{mechanicName,jdbcType=VARCHAR}");
        }
        
        if (record.getMechanic() != null) {
            sql.VALUES("mechanic", "#{mechanic,jdbcType=INTEGER}");
        }
        
        if (record.getIcount() != null) {
            sql.VALUES("icount", "#{icount,jdbcType=INTEGER}");
        }
        
        if (record.getPlateNumber() != null) {
            sql.VALUES("plate_number", "#{plateNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getDriverName() != null) {
            sql.VALUES("driver_name", "#{driverName,jdbcType=VARCHAR}");
        }
        
        if (record.getOilType() != null) {
            sql.VALUES("oil_type", "#{oilType,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.VALUES("supplier", "#{supplier,jdbcType=INTEGER}");
        }
        
        if (record.getSupplierName() != null) {
            sql.VALUES("supplier_name", "#{supplierName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitPrice() != null) {
            sql.VALUES("unit_price", "#{unitPrice,jdbcType=INTEGER}");
        }
        
        if (record.getStaffName() != null) {
            sql.VALUES("staff_name", "#{staffName,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.VALUES("date", "#{date,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStaff() != null) {
            sql.VALUES("staff", "#{staff,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String selectByExample(OilExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("project");
        sql.SELECT("mechanic_name");
        sql.SELECT("mechanic");
        sql.SELECT("icount");
        sql.SELECT("plate_number");
        sql.SELECT("driver_name");
        sql.SELECT("oil_type");
        sql.SELECT("supplier");
        sql.SELECT("supplier_name");
        sql.SELECT("unit_price");
        sql.SELECT("staff_name");
        sql.SELECT("date");
        sql.SELECT("create_date");
        sql.SELECT("staff");
        sql.SELECT("price");
        sql.SELECT("remark");
        sql.FROM(Utils.getDatabase()+".oil");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        Oil record = (Oil) parameter.get("record");
        OilExample example = (OilExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("oil");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=INTEGER}");
        }
        
        if (record.getMechanicName() != null) {
            sql.SET("mechanic_name = #{record.mechanicName,jdbcType=VARCHAR}");
        }
        
        if (record.getMechanic() != null) {
            sql.SET("mechanic = #{record.mechanic,jdbcType=INTEGER}");
        }
        
        if (record.getIcount() != null) {
            sql.SET("icount = #{record.icount,jdbcType=INTEGER}");
        }
        
        if (record.getPlateNumber() != null) {
            sql.SET("plate_number = #{record.plateNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getDriverName() != null) {
            sql.SET("driver_name = #{record.driverName,jdbcType=VARCHAR}");
        }
        
        if (record.getOilType() != null) {
            sql.SET("oil_type = #{record.oilType,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.SET("supplier = #{record.supplier,jdbcType=INTEGER}");
        }
        
        if (record.getSupplierName() != null) {
            sql.SET("supplier_name = #{record.supplierName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitPrice() != null) {
            sql.SET("unit_price = #{record.unitPrice,jdbcType=INTEGER}");
        }
        
        if (record.getStaffName() != null) {
            sql.SET("staff_name = #{record.staffName,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{record.date,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStaff() != null) {
            sql.SET("staff = #{record.staff,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{record.price,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("oil");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=INTEGER}");
        sql.SET("mechanic_name = #{record.mechanicName,jdbcType=VARCHAR}");
        sql.SET("mechanic = #{record.mechanic,jdbcType=INTEGER}");
        sql.SET("icount = #{record.icount,jdbcType=INTEGER}");
        sql.SET("plate_number = #{record.plateNumber,jdbcType=VARCHAR}");
        sql.SET("driver_name = #{record.driverName,jdbcType=VARCHAR}");
        sql.SET("oil_type = #{record.oilType,jdbcType=VARCHAR}");
        sql.SET("supplier = #{record.supplier,jdbcType=INTEGER}");
        sql.SET("supplier_name = #{record.supplierName,jdbcType=VARCHAR}");
        sql.SET("unit_price = #{record.unitPrice,jdbcType=INTEGER}");
        sql.SET("staff_name = #{record.staffName,jdbcType=VARCHAR}");
        sql.SET("date = #{record.date,jdbcType=VARCHAR}");
        sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        sql.SET("staff = #{record.staff,jdbcType=INTEGER}");
        sql.SET("price = #{record.price,jdbcType=INTEGER}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        
        OilExample example = (OilExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    public String updateByPrimaryKeySelective(Oil record) {
        SQL sql = new SQL();
        sql.UPDATE("oil");
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=INTEGER}");
        }
        
        if (record.getMechanicName() != null) {
            sql.SET("mechanic_name = #{mechanicName,jdbcType=VARCHAR}");
        }
        
        if (record.getMechanic() != null) {
            sql.SET("mechanic = #{mechanic,jdbcType=INTEGER}");
        }
        
        if (record.getIcount() != null) {
            sql.SET("icount = #{icount,jdbcType=INTEGER}");
        }
        
        if (record.getPlateNumber() != null) {
            sql.SET("plate_number = #{plateNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getDriverName() != null) {
            sql.SET("driver_name = #{driverName,jdbcType=VARCHAR}");
        }
        
        if (record.getOilType() != null) {
            sql.SET("oil_type = #{oilType,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.SET("supplier = #{supplier,jdbcType=INTEGER}");
        }
        
        if (record.getSupplierName() != null) {
            sql.SET("supplier_name = #{supplierName,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitPrice() != null) {
            sql.SET("unit_price = #{unitPrice,jdbcType=INTEGER}");
        }
        
        if (record.getStaffName() != null) {
            sql.SET("staff_name = #{staffName,jdbcType=VARCHAR}");
        }
        
        if (record.getDate() != null) {
            sql.SET("date = #{date,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStaff() != null) {
            sql.SET("staff = #{staff,jdbcType=INTEGER}");
        }
        
        if (record.getPrice() != null) {
            sql.SET("price = #{price,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil
     *
     * @mbg.generated Fri Jan 26 10:46:02 CST 2018
     */
    protected void applyWhere(SQL sql, OilExample example, boolean includeExamplePhrase) {
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