package com.feedbackforge.feedbackforgeapi.models.enums;

public enum ProfileEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (ProfileEnum x : ProfileEnum.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }

    ProfileEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
