package com.market.sapphires.sbt.v3.form;

import com.market.sapphires.sbt.v3.entity.LoginUser;
import com.market.sapphires.sbt.v3.util.DataTimeUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDetailForm {

    public LoginUserDetailForm(LoginUser user) {
        this.id = user.getIdToString();
        this.username = user.getUsername();
        this.fullname = user.getFullname();
        this.comment = user.getComment();
        this.groupsString = user.getGroupsString();
        this.enabledString = user.getEnabledString();
        this.createdDateString = DataTimeUtil.format(user.getCreatedDate());
        this.updatedDateString = DataTimeUtil.format(user.getUpdatedDate());
    }

    private String id;

    private String username;

    private String fullname;

    private String password;

    private String comment;

    private String groupsString;

    private String enabledString;

    private String createdDateString;

    private String updatedDateString;

    private String version;

}
