package com.heiden.dbp.zuul.model;

import lombok.Data;

@Data
public class HeadersInfo {

    private boolean customSensitiveHeaders;

    private String sensitiveHeaders;

}
