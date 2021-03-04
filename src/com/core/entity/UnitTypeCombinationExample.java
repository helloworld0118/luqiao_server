package com.core.entity;

import java.util.ArrayList;
import java.util.List;

public class UnitTypeCombinationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public UnitTypeCombinationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNull() {
            addCriterion("base_type is null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNotNull() {
            addCriterion("base_type is not null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeEqualTo(Integer value) {
            addCriterion("base_type =", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotEqualTo(Integer value) {
            addCriterion("base_type <>", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThan(Integer value) {
            addCriterion("base_type >", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_type >=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThan(Integer value) {
            addCriterion("base_type <", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("base_type <=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIn(List<Integer> values) {
            addCriterion("base_type in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotIn(List<Integer> values) {
            addCriterion("base_type not in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeBetween(Integer value1, Integer value2) {
            addCriterion("base_type between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("base_type not between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecIsNull() {
            addCriterion("material_spec is null");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecIsNotNull() {
            addCriterion("material_spec is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecEqualTo(Integer value) {
            addCriterion("material_spec =", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecNotEqualTo(Integer value) {
            addCriterion("material_spec <>", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecGreaterThan(Integer value) {
            addCriterion("material_spec >", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_spec >=", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecLessThan(Integer value) {
            addCriterion("material_spec <", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecLessThanOrEqualTo(Integer value) {
            addCriterion("material_spec <=", value, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecIn(List<Integer> values) {
            addCriterion("material_spec in", values, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecNotIn(List<Integer> values) {
            addCriterion("material_spec not in", values, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecBetween(Integer value1, Integer value2) {
            addCriterion("material_spec between", value1, value2, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andMaterialSpecNotBetween(Integer value1, Integer value2) {
            addCriterion("material_spec not between", value1, value2, "materialSpec");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeIsNull() {
            addCriterion("base_price_type is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeIsNotNull() {
            addCriterion("base_price_type is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeEqualTo(Integer value) {
            addCriterion("base_price_type =", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeNotEqualTo(Integer value) {
            addCriterion("base_price_type <>", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeGreaterThan(Integer value) {
            addCriterion("base_price_type >", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_price_type >=", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeLessThan(Integer value) {
            addCriterion("base_price_type <", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("base_price_type <=", value, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeIn(List<Integer> values) {
            addCriterion("base_price_type in", values, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeNotIn(List<Integer> values) {
            addCriterion("base_price_type not in", values, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeBetween(Integer value1, Integer value2) {
            addCriterion("base_price_type between", value1, value2, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("base_price_type not between", value1, value2, "basePriceType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIsNull() {
            addCriterion("material_type is null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIsNotNull() {
            addCriterion("material_type is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeEqualTo(Integer value) {
            addCriterion("material_type =", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotEqualTo(Integer value) {
            addCriterion("material_type <>", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThan(Integer value) {
            addCriterion("material_type >", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_type >=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThan(Integer value) {
            addCriterion("material_type <", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThanOrEqualTo(Integer value) {
            addCriterion("material_type <=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIn(List<Integer> values) {
            addCriterion("material_type in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotIn(List<Integer> values) {
            addCriterion("material_type not in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeBetween(Integer value1, Integer value2) {
            addCriterion("material_type between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("material_type not between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unit_type_combination
     *
     * @mbg.generated do_not_delete_during_merge Mon Dec 25 20:02:59 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unit_type_combination
     *
     * @mbg.generated Mon Dec 25 20:02:59 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}