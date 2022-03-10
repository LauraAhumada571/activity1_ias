package com.example.activity1.shared.domain;

import org.apache.commons.lang3.Validate;

public class PageQuery {
    private final Skip skip;
    private final Limit limit;

    public PageQuery(Skip skip, Limit limit) {
        this.skip = Validate.notNull(skip);
        this.limit = Validate.notNull(limit);
    }

    public Skip getSkip() {
        return skip;
    }

    public Limit getLimit() {
        return limit;
    }
}
