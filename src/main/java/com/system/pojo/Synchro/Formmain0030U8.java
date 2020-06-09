package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Formmain0030U8 {
    private String code;
    private String name;
    private String sort_code;
    private String email;
    private String phone;
}
