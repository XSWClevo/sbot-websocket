package com.example.demo.dto;

import java.util.List;

public class SearchTransformation {
    private String timeformat;
    private TimeModifier earliest;
    private TimeModifier _index_earliest;
    private String starttime;
    private Integer startdaysago;
    private Integer startminutesago;
    private Integer starthoursago;
    private Integer startmonthsago;
    private Num starttimeu;
    private TimeModifier latest;
    private TimeModifier _index_latest;
    private String endtime;
    private Integer enddaysago;
    private Integer endminutesago;
    private Integer endhoursago;
    private Integer endmonthsago;
    private Num endtimeu;
    private Integer searchtimespanhours;
    private Integer searchtimespanminutes;
    private Integer searchtimespandays;
    private Integer searchtimespanmonths;
    private Integer daysago;
    private Integer minutesago;
    private Integer hoursago;
    private Integer monthsago;
    private String savedsearch;
    private String savedsplunk;
    private List<String> keywords;
    private String string;
    private Argument field;
    private BooleanOperatorNot booleanOperatorNot;
    private Term term;
    private Num num;
    private BooleanOperatorOr booleanOperatorOr;
    private BooleanOperatorAnd booleanOperatorAnd;

    public class TimeModifier {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class Num {
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public class BooleanOperatorNot {
        private String operator;

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

    public class Term {
        private String term;

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }
    }

    public class BooleanOperatorOr {
        private String operator;

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

    public class BooleanOperatorAnd {
        private String operator;

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

    public TimeModifier getEarliest() {
        return earliest;
    }

    public void setEarliest(TimeModifier earliest) {
        this.earliest = earliest;
    }

    public TimeModifier get_index_earliest() {
        return _index_earliest;
    }

    public void set_index_earliest(TimeModifier _index_earliest) {
        this._index_earliest = _index_earliest;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Integer getStartdaysago() {
        return startdaysago;
    }

    public void setStartdaysago(Integer startdaysago) {
        this.startdaysago = startdaysago;
    }

    public Integer getStartminutesago() {
        return startminutesago;
    }

    public void setStartminutesago(Integer startminutesago) {
        this.startminutesago = startminutesago;
    }

    public Integer getStarthoursago() {
        return starthoursago;
    }

    public void setStarthoursago(Integer starthoursago) {
        this.starthoursago = starthoursago;
    }

    public Integer getStartmonthsago() {
        return startmonthsago;
    }

    public void setStartmonthsago(Integer startmonthsago) {
        this.startmonthsago = startmonthsago;
    }

    public Num getStarttimeu() {
        return starttimeu;
    }

    public void setStarttimeu(Num starttimeu) {
        this.starttimeu = starttimeu;
    }

    public TimeModifier getLatest() {
        return latest;
    }

    public void setLatest(TimeModifier latest) {
        this.latest = latest;
    }

    public TimeModifier get_index_latest() {
        return _index_latest;
    }

    public void set_index_latest(TimeModifier _index_latest) {
        this._index_latest = _index_latest;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getEnddaysago() {
        return enddaysago;
    }

    public void setEnddaysago(Integer enddaysago) {
        this.enddaysago = enddaysago;
    }

    public Integer getEndminutesago() {
        return endminutesago;
    }

    public void setEndminutesago(Integer endminutesago) {
        this.endminutesago = endminutesago;
    }

    public Integer getEndhoursago() {
        return endhoursago;
    }

    public void setEndhoursago(Integer endhoursago) {
        this.endhoursago = endhoursago;
    }

    public Integer getEndmonthsago() {
        return endmonthsago;
    }

    public void setEndmonthsago(Integer endmonthsago) {
        this.endmonthsago = endmonthsago;
    }

    public Num getEndtimeu() {
        return endtimeu;
    }

    public void setEndtimeu(Num endtimeu) {
        this.endtimeu = endtimeu;
    }

    public Integer getSearchtimespanhours() {
        return searchtimespanhours;
    }

    public void setSearchtimespanhours(Integer searchtimespanhours) {
        this.searchtimespanhours = searchtimespanhours;
    }

    public Integer getSearchtimespanminutes() {
        return searchtimespanminutes;
    }

    public void setSearchtimespanminutes(Integer searchtimespanminutes) {
        this.searchtimespanminutes = searchtimespanminutes;
    }

    public Integer getSearchtimespandays() {
        return searchtimespandays;
    }

    public void setSearchtimespandays(Integer searchtimespandays) {
        this.searchtimespandays = searchtimespandays;
    }

    public Integer getSearchtimespanmonths() {
        return searchtimespanmonths;
    }

    public void setSearchtimespanmonths(Integer searchtimespanmonths) {
        this.searchtimespanmonths = searchtimespanmonths;
    }

    public Integer getDaysago() {
        return daysago;
    }

    public void setDaysago(Integer daysago) {
        this.daysago = daysago;
    }

    public Integer getMinutesago() {
        return minutesago;
    }

    public void setMinutesago(Integer minutesago) {
        this.minutesago = minutesago;
    }

    public Integer getHoursago() {
        return hoursago;
    }

    public void setHoursago(Integer hoursago) {
        this.hoursago = hoursago;
    }

    public Integer getMonthsago() {
        return monthsago;
    }

    public void setMonthsago(Integer monthsago) {
        this.monthsago = monthsago;
    }

    public String getSavedsearch() {
        return savedsearch;
    }

    public void setSavedsearch(String savedsearch) {
        this.savedsearch = savedsearch;
    }

    public String getSavedsplunk() {
        return savedsplunk;
    }

    public void setSavedsplunk(String savedsplunk) {
        this.savedsplunk = savedsplunk;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Argument getField() {
        return field;
    }

    public void setField(Argument field) {
        this.field = field;
    }

    public BooleanOperatorNot getBooleanOperatorNot() {
        return booleanOperatorNot;
    }

    public void setBooleanOperatorNot(BooleanOperatorNot booleanOperatorNot) {
        this.booleanOperatorNot = booleanOperatorNot;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Num getNum() {
        return num;
    }

    public void setNum(Num num) {
        this.num = num;
    }

    public BooleanOperatorOr getBooleanOperatorOr() {
        return booleanOperatorOr;
    }

    public void setBooleanOperatorOr(BooleanOperatorOr booleanOperatorOr) {
        this.booleanOperatorOr = booleanOperatorOr;
    }

    public BooleanOperatorAnd getBooleanOperatorAnd() {
        return booleanOperatorAnd;
    }

    public void setBooleanOperatorAnd(BooleanOperatorAnd booleanOperatorAnd) {
        this.booleanOperatorAnd = booleanOperatorAnd;
    }
}
