package com.woniu.utils;

import java.util.List;

public class MyPage<T> {
    private List<T> list;
    private int pagecount;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }
}
