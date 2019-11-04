package com.market.sapphires.sbt.v3.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.market.sapphires.sbt.v3.util.MessageUtil;

import lombok.Data;

@Data
public class LoginUser implements UserDetails {

    private long id;

    public String getIdToString() {
        return String.valueOf(this.id);
    }

    private String username;

    private String fullname;

    private String password;

    private String comment;

    private Set<LoginUserGroup> groups = new HashSet<>();

    public String getGroupsString() {
        return String.join(", ",
                this.groups.stream()
                        .map(g -> MessageUtil
                                .getValue(new StringBuilder("users.group.").append(g.getName()).toString()))
                        .collect(Collectors.toList()));
    }

    private boolean enabled;

    public String getEnabledString() {
        if (this.enabled) {
            return MessageUtil.getValue("users.enabled.enable");
        } else {
            return MessageUtil.getValue("users.enabled.disable");
        }
    }

    private boolean locked;

    private long createdDate;

    private long updatedDate;

    private int version;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.groups.stream().forEach(g -> {
            g.getPermissions().forEach(p -> authorities.add(p.getAuthority()));
        });

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
