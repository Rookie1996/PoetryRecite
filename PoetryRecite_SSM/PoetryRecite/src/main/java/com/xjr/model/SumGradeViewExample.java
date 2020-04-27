package com.xjr.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SumGradeViewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SumGradeViewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andEuseridIsNull() {
            addCriterion("euserid is null");
            return (Criteria) this;
        }

        public Criteria andEuseridIsNotNull() {
            addCriterion("euserid is not null");
            return (Criteria) this;
        }

        public Criteria andEuseridEqualTo(Long value) {
            addCriterion("euserid =", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridNotEqualTo(Long value) {
            addCriterion("euserid <>", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridGreaterThan(Long value) {
            addCriterion("euserid >", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("euserid >=", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridLessThan(Long value) {
            addCriterion("euserid <", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridLessThanOrEqualTo(Long value) {
            addCriterion("euserid <=", value, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridIn(List<Long> values) {
            addCriterion("euserid in", values, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridNotIn(List<Long> values) {
            addCriterion("euserid not in", values, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridBetween(Long value1, Long value2) {
            addCriterion("euserid between", value1, value2, "euserid");
            return (Criteria) this;
        }

        public Criteria andEuseridNotBetween(Long value1, Long value2) {
            addCriterion("euserid not between", value1, value2, "euserid");
            return (Criteria) this;
        }

        public Criteria andEusernameIsNull() {
            addCriterion("eusername is null");
            return (Criteria) this;
        }

        public Criteria andEusernameIsNotNull() {
            addCriterion("eusername is not null");
            return (Criteria) this;
        }

        public Criteria andEusernameEqualTo(String value) {
            addCriterion("eusername =", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameNotEqualTo(String value) {
            addCriterion("eusername <>", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameGreaterThan(String value) {
            addCriterion("eusername >", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameGreaterThanOrEqualTo(String value) {
            addCriterion("eusername >=", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameLessThan(String value) {
            addCriterion("eusername <", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameLessThanOrEqualTo(String value) {
            addCriterion("eusername <=", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameLike(String value) {
            addCriterion("eusername like", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameNotLike(String value) {
            addCriterion("eusername not like", value, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameIn(List<String> values) {
            addCriterion("eusername in", values, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameNotIn(List<String> values) {
            addCriterion("eusername not in", values, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameBetween(String value1, String value2) {
            addCriterion("eusername between", value1, value2, "eusername");
            return (Criteria) this;
        }

        public Criteria andEusernameNotBetween(String value1, String value2) {
            addCriterion("eusername not between", value1, value2, "eusername");
            return (Criteria) this;
        }

        public Criteria andEgradeSumIsNull() {
            addCriterion("egrade_sum is null");
            return (Criteria) this;
        }

        public Criteria andEgradeSumIsNotNull() {
            addCriterion("egrade_sum is not null");
            return (Criteria) this;
        }

        public Criteria andEgradeSumEqualTo(BigDecimal value) {
            addCriterion("egrade_sum =", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumNotEqualTo(BigDecimal value) {
            addCriterion("egrade_sum <>", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumGreaterThan(BigDecimal value) {
            addCriterion("egrade_sum >", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("egrade_sum >=", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumLessThan(BigDecimal value) {
            addCriterion("egrade_sum <", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("egrade_sum <=", value, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumIn(List<BigDecimal> values) {
            addCriterion("egrade_sum in", values, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumNotIn(List<BigDecimal> values) {
            addCriterion("egrade_sum not in", values, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("egrade_sum between", value1, value2, "egradeSum");
            return (Criteria) this;
        }

        public Criteria andEgradeSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("egrade_sum not between", value1, value2, "egradeSum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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