package com.github.wisaitas.sharelib.dtos.queries;

public class PaginationQuery {
    private Integer page;
    private Integer pageSize;
    private String sort;
    private String order;

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
