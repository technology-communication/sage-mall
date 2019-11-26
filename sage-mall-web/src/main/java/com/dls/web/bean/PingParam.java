package com.dls.web.bean;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class PingParam {
    @NotEmpty(message = "{test}")
    private String test;
}
