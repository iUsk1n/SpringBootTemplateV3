package com.market.sapphires.sbt.v3.form.datatables.request.base;

import lombok.Data;

@Data
public class DataTablesRequestColumns {

    private String data;

    private String name;

    private boolean searchable;

    private boolean orderable;

    private DataTablesRequestSearch search = new DataTablesRequestSearch();

}
