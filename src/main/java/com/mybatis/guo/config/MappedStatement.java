package com.mybatis.guo.config;

import javax.print.attribute.standard.PageRanges;

/**
 * @author guoqinglin
 * @create 2020-06-01-12:12
 */
public class MappedStatement {
    private String namespace;
    private String sourceId;
    private String resultType;
    private String Sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return Sql;
    }

    public void setSql(String sql) {
        Sql = sql;
    }
}
