package com.analytics.analytics.entity;

public enum Roles {
    ROLE_POWERUSER("role.poweruser"), ROLE_ADMIN("role.admin"), ROLE_USER("role.user"), ROLE_VIEWER("role.viewer");

    private String code;

    Roles(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
