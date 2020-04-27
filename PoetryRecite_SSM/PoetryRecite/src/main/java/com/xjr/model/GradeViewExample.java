package com.xjr.model;

import java.util.ArrayList;
import java.util.List;

public class GradeViewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GradeViewExample() {
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

        public Criteria andVidIsNull() {
            addCriterion("vid is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("vid is not null");
            return (Criteria) this;
        }

        public Criteria andVidEqualTo(Integer value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(Integer value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(Integer value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(Integer value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(Integer value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<Integer> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<Integer> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(Integer value1, Integer value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(Integer value1, Integer value2) {
            addCriterion("vid not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVuseridIsNull() {
            addCriterion("vuserid is null");
            return (Criteria) this;
        }

        public Criteria andVuseridIsNotNull() {
            addCriterion("vuserid is not null");
            return (Criteria) this;
        }

        public Criteria andVuseridEqualTo(Long value) {
            addCriterion("vuserid =", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotEqualTo(Long value) {
            addCriterion("vuserid <>", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridGreaterThan(Long value) {
            addCriterion("vuserid >", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("vuserid >=", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridLessThan(Long value) {
            addCriterion("vuserid <", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridLessThanOrEqualTo(Long value) {
            addCriterion("vuserid <=", value, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridIn(List<Long> values) {
            addCriterion("vuserid in", values, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotIn(List<Long> values) {
            addCriterion("vuserid not in", values, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridBetween(Long value1, Long value2) {
            addCriterion("vuserid between", value1, value2, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVuseridNotBetween(Long value1, Long value2) {
            addCriterion("vuserid not between", value1, value2, "vuserid");
            return (Criteria) this;
        }

        public Criteria andVusernameIsNull() {
            addCriterion("vusername is null");
            return (Criteria) this;
        }

        public Criteria andVusernameIsNotNull() {
            addCriterion("vusername is not null");
            return (Criteria) this;
        }

        public Criteria andVusernameEqualTo(String value) {
            addCriterion("vusername =", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameNotEqualTo(String value) {
            addCriterion("vusername <>", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameGreaterThan(String value) {
            addCriterion("vusername >", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameGreaterThanOrEqualTo(String value) {
            addCriterion("vusername >=", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameLessThan(String value) {
            addCriterion("vusername <", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameLessThanOrEqualTo(String value) {
            addCriterion("vusername <=", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameLike(String value) {
            addCriterion("vusername like", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameNotLike(String value) {
            addCriterion("vusername not like", value, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameIn(List<String> values) {
            addCriterion("vusername in", values, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameNotIn(List<String> values) {
            addCriterion("vusername not in", values, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameBetween(String value1, String value2) {
            addCriterion("vusername between", value1, value2, "vusername");
            return (Criteria) this;
        }

        public Criteria andVusernameNotBetween(String value1, String value2) {
            addCriterion("vusername not between", value1, value2, "vusername");
            return (Criteria) this;
        }

        public Criteria andVtestidIsNull() {
            addCriterion("vtestid is null");
            return (Criteria) this;
        }

        public Criteria andVtestidIsNotNull() {
            addCriterion("vtestid is not null");
            return (Criteria) this;
        }

        public Criteria andVtestidEqualTo(Integer value) {
            addCriterion("vtestid =", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidNotEqualTo(Integer value) {
            addCriterion("vtestid <>", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidGreaterThan(Integer value) {
            addCriterion("vtestid >", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vtestid >=", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidLessThan(Integer value) {
            addCriterion("vtestid <", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidLessThanOrEqualTo(Integer value) {
            addCriterion("vtestid <=", value, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidIn(List<Integer> values) {
            addCriterion("vtestid in", values, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidNotIn(List<Integer> values) {
            addCriterion("vtestid not in", values, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidBetween(Integer value1, Integer value2) {
            addCriterion("vtestid between", value1, value2, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestidNotBetween(Integer value1, Integer value2) {
            addCriterion("vtestid not between", value1, value2, "vtestid");
            return (Criteria) this;
        }

        public Criteria andVtestypeIsNull() {
            addCriterion("vtestype is null");
            return (Criteria) this;
        }

        public Criteria andVtestypeIsNotNull() {
            addCriterion("vtestype is not null");
            return (Criteria) this;
        }

        public Criteria andVtestypeEqualTo(String value) {
            addCriterion("vtestype =", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeNotEqualTo(String value) {
            addCriterion("vtestype <>", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeGreaterThan(String value) {
            addCriterion("vtestype >", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeGreaterThanOrEqualTo(String value) {
            addCriterion("vtestype >=", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeLessThan(String value) {
            addCriterion("vtestype <", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeLessThanOrEqualTo(String value) {
            addCriterion("vtestype <=", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeLike(String value) {
            addCriterion("vtestype like", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeNotLike(String value) {
            addCriterion("vtestype not like", value, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeIn(List<String> values) {
            addCriterion("vtestype in", values, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeNotIn(List<String> values) {
            addCriterion("vtestype not in", values, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeBetween(String value1, String value2) {
            addCriterion("vtestype between", value1, value2, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVtestypeNotBetween(String value1, String value2) {
            addCriterion("vtestype not between", value1, value2, "vtestype");
            return (Criteria) this;
        }

        public Criteria andVcontentIsNull() {
            addCriterion("vcontent is null");
            return (Criteria) this;
        }

        public Criteria andVcontentIsNotNull() {
            addCriterion("vcontent is not null");
            return (Criteria) this;
        }

        public Criteria andVcontentEqualTo(String value) {
            addCriterion("vcontent =", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentNotEqualTo(String value) {
            addCriterion("vcontent <>", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentGreaterThan(String value) {
            addCriterion("vcontent >", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentGreaterThanOrEqualTo(String value) {
            addCriterion("vcontent >=", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentLessThan(String value) {
            addCriterion("vcontent <", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentLessThanOrEqualTo(String value) {
            addCriterion("vcontent <=", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentLike(String value) {
            addCriterion("vcontent like", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentNotLike(String value) {
            addCriterion("vcontent not like", value, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentIn(List<String> values) {
            addCriterion("vcontent in", values, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentNotIn(List<String> values) {
            addCriterion("vcontent not in", values, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentBetween(String value1, String value2) {
            addCriterion("vcontent between", value1, value2, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVcontentNotBetween(String value1, String value2) {
            addCriterion("vcontent not between", value1, value2, "vcontent");
            return (Criteria) this;
        }

        public Criteria andVanswerIsNull() {
            addCriterion("vanswer is null");
            return (Criteria) this;
        }

        public Criteria andVanswerIsNotNull() {
            addCriterion("vanswer is not null");
            return (Criteria) this;
        }

        public Criteria andVanswerEqualTo(String value) {
            addCriterion("vanswer =", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerNotEqualTo(String value) {
            addCriterion("vanswer <>", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerGreaterThan(String value) {
            addCriterion("vanswer >", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerGreaterThanOrEqualTo(String value) {
            addCriterion("vanswer >=", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerLessThan(String value) {
            addCriterion("vanswer <", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerLessThanOrEqualTo(String value) {
            addCriterion("vanswer <=", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerLike(String value) {
            addCriterion("vanswer like", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerNotLike(String value) {
            addCriterion("vanswer not like", value, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerIn(List<String> values) {
            addCriterion("vanswer in", values, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerNotIn(List<String> values) {
            addCriterion("vanswer not in", values, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerBetween(String value1, String value2) {
            addCriterion("vanswer between", value1, value2, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVanswerNotBetween(String value1, String value2) {
            addCriterion("vanswer not between", value1, value2, "vanswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerIsNull() {
            addCriterion("vuseranswer is null");
            return (Criteria) this;
        }

        public Criteria andVuseranswerIsNotNull() {
            addCriterion("vuseranswer is not null");
            return (Criteria) this;
        }

        public Criteria andVuseranswerEqualTo(String value) {
            addCriterion("vuseranswer =", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerNotEqualTo(String value) {
            addCriterion("vuseranswer <>", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerGreaterThan(String value) {
            addCriterion("vuseranswer >", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerGreaterThanOrEqualTo(String value) {
            addCriterion("vuseranswer >=", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerLessThan(String value) {
            addCriterion("vuseranswer <", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerLessThanOrEqualTo(String value) {
            addCriterion("vuseranswer <=", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerLike(String value) {
            addCriterion("vuseranswer like", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerNotLike(String value) {
            addCriterion("vuseranswer not like", value, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerIn(List<String> values) {
            addCriterion("vuseranswer in", values, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerNotIn(List<String> values) {
            addCriterion("vuseranswer not in", values, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerBetween(String value1, String value2) {
            addCriterion("vuseranswer between", value1, value2, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVuseranswerNotBetween(String value1, String value2) {
            addCriterion("vuseranswer not between", value1, value2, "vuseranswer");
            return (Criteria) this;
        }

        public Criteria andVgradeIsNull() {
            addCriterion("vgrade is null");
            return (Criteria) this;
        }

        public Criteria andVgradeIsNotNull() {
            addCriterion("vgrade is not null");
            return (Criteria) this;
        }

        public Criteria andVgradeEqualTo(Integer value) {
            addCriterion("vgrade =", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeNotEqualTo(Integer value) {
            addCriterion("vgrade <>", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeGreaterThan(Integer value) {
            addCriterion("vgrade >", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("vgrade >=", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeLessThan(Integer value) {
            addCriterion("vgrade <", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeLessThanOrEqualTo(Integer value) {
            addCriterion("vgrade <=", value, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeIn(List<Integer> values) {
            addCriterion("vgrade in", values, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeNotIn(List<Integer> values) {
            addCriterion("vgrade not in", values, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeBetween(Integer value1, Integer value2) {
            addCriterion("vgrade between", value1, value2, "vgrade");
            return (Criteria) this;
        }

        public Criteria andVgradeNotBetween(Integer value1, Integer value2) {
            addCriterion("vgrade not between", value1, value2, "vgrade");
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