package com.example.demo.grcloud.page;

import java.io.Serializable;
import java.util.List;

public class GridDataModel implements Serializable {
    /**
     * 总记录数
     */
    private int total;
    /**
     * 当页数据
     */
    private List<?> rows;

    /**
     *
     * @param rows
     * @param total
     */
    public GridDataModel(List<?> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
