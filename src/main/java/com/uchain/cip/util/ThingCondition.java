package com.uchain.cip.util;

import lombok.Getter;

/**
 * 帖子筛选条件
 * */
@Getter
public class ThingCondition {
    private String title;
    private int type;
    private int orderBy;
    private int ascOrDesc;
    private int pageIndex;
    private int pageSize;

    public ThingCondition() {
        title = null;
        type = 0;
        orderBy = 0;
        ascOrDesc = 0;
        pageIndex = 1;
        pageSize = 5;
    }
}
