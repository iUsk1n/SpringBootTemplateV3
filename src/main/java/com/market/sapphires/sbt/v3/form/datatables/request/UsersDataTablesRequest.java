package com.market.sapphires.sbt.v3.form.datatables.request;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.market.sapphires.sbt.v3.form.datatables.request.base.DataTablesRequest;
import com.market.sapphires.sbt.v3.form.datatables.request.base.DataTablesRequestColumns;

import lombok.Getter;

@Getter
public class UsersDataTablesRequest extends DataTablesRequest {

    @Getter
    enum COLUMNS {
        /** */
        USERNAME("username"),
        /** */
        FULLNAME("fullname"),
        /** */
        ENABLED("enabled"),
        /** */
        GROUPS("groups"),
        /** */
        COMMENT("comment"),
        /** */
        UPDATEDDATE("updatedDate");

        COLUMNS(String name) {
            this.name = name;
        }

        private String name;
    }

    private String searchUsername;

    public UsersDataTablesRequest(HttpServletRequest request) {
        super(request, COLUMNS.values().length, 1);

        Optional<DataTablesRequestColumns> columnUsername = this.getColumns().stream()
                .filter(c -> c.isSearchable() && COLUMNS.USERNAME.getName().equals(c.getData()))
                .findFirst();
        if (columnUsername.isPresent()) {
            String searchValue = columnUsername.get().getSearch().getValue();
            if (!StringUtils.isEmpty(searchValue)) {
                this.searchUsername = searchValue;
            }
        }

    }

}
