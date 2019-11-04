package com.market.sapphires.sbt.v3.form.datatables.request.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataTablesRequest {

    public DataTablesRequest(HttpServletRequest request, int columnsLength, int orderLength) {
        this.draw = Integer.parseInt(request.getParameter("draw"));
        this.start = Integer.parseInt(request.getParameter("start"));
        this.length = Integer.parseInt(request.getParameter("length"));

        for (int i = 0; i < columnsLength; i++) {
            StringBuilder sb = new StringBuilder("columns[").append(i).append("]");

            DataTablesRequestColumns c = new DataTablesRequestColumns();
            c.setData(request.getParameter(new StringBuilder(sb).append("[data]").toString()));
            c.setName(request.getParameter(new StringBuilder(sb).append("[name]").toString()));
            c.setSearchable(Boolean
                    .parseBoolean(request.getParameter(new StringBuilder(sb).append("[searchable]").toString())));
            c.setOrderable(
                    Boolean.parseBoolean(request.getParameter(new StringBuilder(sb).append("[orderable]").toString())));
            c.getSearch().setValue(request.getParameter(new StringBuilder(sb).append("[search][value]").toString()));
            c.getSearch()
                    .setRegex(Boolean.parseBoolean(
                            request.getParameter(new StringBuilder(sb).append("[search][regex]").toString())));

            this.columns.add(c);
        }

        for (int i = 0; i < orderLength; i++) {
            StringBuilder sb = new StringBuilder("order[").append(i).append("]");

            DataTablesRequestOrder o = new DataTablesRequestOrder();
            o.setColumn(
                    Integer.parseInt(request.getParameter(new StringBuilder(sb).append("[column]").toString())));
            o.setDir(request.getParameter(new StringBuilder(sb).append("[dir]").toString()));

            this.order.add(o);
        }

        this.search.setValue(request.getParameter("search[value]"));
        this.search.setRegex(Boolean.parseBoolean(request.getParameter("search[regex]")));
    }

    private int draw;

    private int start;

    private int length;

    private List<DataTablesRequestColumns> columns = new ArrayList<>();

    private List<DataTablesRequestOrder> order = new ArrayList<>();

    private DataTablesRequestSearch search = new DataTablesRequestSearch();

}
