package com.xjr.model;

import java.util.ArrayList;
import java.util.List;

public class TestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestExample() {
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

        public Criteria andTestidIsNull() {
            addCriterion("testid is null");
            return (Criteria) this;
        }

        public Criteria andTestidIsNotNull() {
            addCriterion("testid is not null");
            return (Criteria) this;
        }

        public Criteria andTestidEqualTo(Integer value) {
            addCriterion("testid =", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidNotEqualTo(Integer value) {
            addCriterion("testid <>", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidGreaterThan(Integer value) {
            addCriterion("testid >", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidGreaterThanOrEqualTo(Integer value) {
            addCriterion("testid >=", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidLessThan(Integer value) {
            addCriterion("testid <", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidLessThanOrEqualTo(Integer value) {
            addCriterion("testid <=", value, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidIn(List<Integer> values) {
            addCriterion("testid in", values, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidNotIn(List<Integer> values) {
            addCriterion("testid not in", values, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidBetween(Integer value1, Integer value2) {
            addCriterion("testid between", value1, value2, "testid");
            return (Criteria) this;
        }

        public Criteria andTestidNotBetween(Integer value1, Integer value2) {
            addCriterion("testid not between", value1, value2, "testid");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("typeid is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("typeid is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("typeid =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("typeid <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("typeid >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("typeid >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("typeid <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("typeid <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("typeid in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("typeid not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("typeid between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("typeid not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTestcontentIsNull() {
            addCriterion("testcontent is null");
            return (Criteria) this;
        }

        public Criteria andTestcontentIsNotNull() {
            addCriterion("testcontent is not null");
            return (Criteria) this;
        }

        public Criteria andTestcontentEqualTo(String value) {
            addCriterion("testcontent =", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentNotEqualTo(String value) {
            addCriterion("testcontent <>", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentGreaterThan(String value) {
            addCriterion("testcontent >", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentGreaterThanOrEqualTo(String value) {
            addCriterion("testcontent >=", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentLessThan(String value) {
            addCriterion("testcontent <", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentLessThanOrEqualTo(String value) {
            addCriterion("testcontent <=", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentLike(String value) {
            addCriterion("testcontent like", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentNotLike(String value) {
            addCriterion("testcontent not like", value, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentIn(List<String> values) {
            addCriterion("testcontent in", values, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentNotIn(List<String> values) {
            addCriterion("testcontent not in", values, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentBetween(String value1, String value2) {
            addCriterion("testcontent between", value1, value2, "testcontent");
            return (Criteria) this;
        }

        public Criteria andTestcontentNotBetween(String value1, String value2) {
            addCriterion("testcontent not between", value1, value2, "testcontent");
            return (Criteria) this;
        }

        public Criteria andOptionaIsNull() {
            addCriterion("optionA is null");
            return (Criteria) this;
        }

        public Criteria andOptionaIsNotNull() {
            addCriterion("optionA is not null");
            return (Criteria) this;
        }

        public Criteria andOptionaEqualTo(String value) {
            addCriterion("optionA =", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotEqualTo(String value) {
            addCriterion("optionA <>", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaGreaterThan(String value) {
            addCriterion("optionA >", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaGreaterThanOrEqualTo(String value) {
            addCriterion("optionA >=", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLessThan(String value) {
            addCriterion("optionA <", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLessThanOrEqualTo(String value) {
            addCriterion("optionA <=", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaLike(String value) {
            addCriterion("optionA like", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotLike(String value) {
            addCriterion("optionA not like", value, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaIn(List<String> values) {
            addCriterion("optionA in", values, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotIn(List<String> values) {
            addCriterion("optionA not in", values, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaBetween(String value1, String value2) {
            addCriterion("optionA between", value1, value2, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionaNotBetween(String value1, String value2) {
            addCriterion("optionA not between", value1, value2, "optiona");
            return (Criteria) this;
        }

        public Criteria andOptionbIsNull() {
            addCriterion("optionB is null");
            return (Criteria) this;
        }

        public Criteria andOptionbIsNotNull() {
            addCriterion("optionB is not null");
            return (Criteria) this;
        }

        public Criteria andOptionbEqualTo(String value) {
            addCriterion("optionB =", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotEqualTo(String value) {
            addCriterion("optionB <>", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbGreaterThan(String value) {
            addCriterion("optionB >", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbGreaterThanOrEqualTo(String value) {
            addCriterion("optionB >=", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLessThan(String value) {
            addCriterion("optionB <", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLessThanOrEqualTo(String value) {
            addCriterion("optionB <=", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbLike(String value) {
            addCriterion("optionB like", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotLike(String value) {
            addCriterion("optionB not like", value, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbIn(List<String> values) {
            addCriterion("optionB in", values, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotIn(List<String> values) {
            addCriterion("optionB not in", values, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbBetween(String value1, String value2) {
            addCriterion("optionB between", value1, value2, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptionbNotBetween(String value1, String value2) {
            addCriterion("optionB not between", value1, value2, "optionb");
            return (Criteria) this;
        }

        public Criteria andOptioncIsNull() {
            addCriterion("optionC is null");
            return (Criteria) this;
        }

        public Criteria andOptioncIsNotNull() {
            addCriterion("optionC is not null");
            return (Criteria) this;
        }

        public Criteria andOptioncEqualTo(String value) {
            addCriterion("optionC =", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotEqualTo(String value) {
            addCriterion("optionC <>", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncGreaterThan(String value) {
            addCriterion("optionC >", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncGreaterThanOrEqualTo(String value) {
            addCriterion("optionC >=", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLessThan(String value) {
            addCriterion("optionC <", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLessThanOrEqualTo(String value) {
            addCriterion("optionC <=", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncLike(String value) {
            addCriterion("optionC like", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotLike(String value) {
            addCriterion("optionC not like", value, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncIn(List<String> values) {
            addCriterion("optionC in", values, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotIn(List<String> values) {
            addCriterion("optionC not in", values, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncBetween(String value1, String value2) {
            addCriterion("optionC between", value1, value2, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptioncNotBetween(String value1, String value2) {
            addCriterion("optionC not between", value1, value2, "optionc");
            return (Criteria) this;
        }

        public Criteria andOptiondIsNull() {
            addCriterion("optionD is null");
            return (Criteria) this;
        }

        public Criteria andOptiondIsNotNull() {
            addCriterion("optionD is not null");
            return (Criteria) this;
        }

        public Criteria andOptiondEqualTo(String value) {
            addCriterion("optionD =", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotEqualTo(String value) {
            addCriterion("optionD <>", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondGreaterThan(String value) {
            addCriterion("optionD >", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondGreaterThanOrEqualTo(String value) {
            addCriterion("optionD >=", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLessThan(String value) {
            addCriterion("optionD <", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLessThanOrEqualTo(String value) {
            addCriterion("optionD <=", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondLike(String value) {
            addCriterion("optionD like", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotLike(String value) {
            addCriterion("optionD not like", value, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondIn(List<String> values) {
            addCriterion("optionD in", values, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotIn(List<String> values) {
            addCriterion("optionD not in", values, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondBetween(String value1, String value2) {
            addCriterion("optionD between", value1, value2, "optiond");
            return (Criteria) this;
        }

        public Criteria andOptiondNotBetween(String value1, String value2) {
            addCriterion("optionD not between", value1, value2, "optiond");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andExplainationIsNull() {
            addCriterion("explaination is null");
            return (Criteria) this;
        }

        public Criteria andExplainationIsNotNull() {
            addCriterion("explaination is not null");
            return (Criteria) this;
        }

        public Criteria andExplainationEqualTo(String value) {
            addCriterion("explaination =", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationNotEqualTo(String value) {
            addCriterion("explaination <>", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationGreaterThan(String value) {
            addCriterion("explaination >", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationGreaterThanOrEqualTo(String value) {
            addCriterion("explaination >=", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationLessThan(String value) {
            addCriterion("explaination <", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationLessThanOrEqualTo(String value) {
            addCriterion("explaination <=", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationLike(String value) {
            addCriterion("explaination like", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationNotLike(String value) {
            addCriterion("explaination not like", value, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationIn(List<String> values) {
            addCriterion("explaination in", values, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationNotIn(List<String> values) {
            addCriterion("explaination not in", values, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationBetween(String value1, String value2) {
            addCriterion("explaination between", value1, value2, "explaination");
            return (Criteria) this;
        }

        public Criteria andExplainationNotBetween(String value1, String value2) {
            addCriterion("explaination not between", value1, value2, "explaination");
            return (Criteria) this;
        }

        public Criteria andTipsIsNull() {
            addCriterion("tips is null");
            return (Criteria) this;
        }

        public Criteria andTipsIsNotNull() {
            addCriterion("tips is not null");
            return (Criteria) this;
        }

        public Criteria andTipsEqualTo(String value) {
            addCriterion("tips =", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsNotEqualTo(String value) {
            addCriterion("tips <>", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsGreaterThan(String value) {
            addCriterion("tips >", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsGreaterThanOrEqualTo(String value) {
            addCriterion("tips >=", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsLessThan(String value) {
            addCriterion("tips <", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsLessThanOrEqualTo(String value) {
            addCriterion("tips <=", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsLike(String value) {
            addCriterion("tips like", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsNotLike(String value) {
            addCriterion("tips not like", value, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsIn(List<String> values) {
            addCriterion("tips in", values, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsNotIn(List<String> values) {
            addCriterion("tips not in", values, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsBetween(String value1, String value2) {
            addCriterion("tips between", value1, value2, "tips");
            return (Criteria) this;
        }

        public Criteria andTipsNotBetween(String value1, String value2) {
            addCriterion("tips not between", value1, value2, "tips");
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