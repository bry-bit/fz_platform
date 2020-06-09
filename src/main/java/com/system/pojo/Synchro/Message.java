package com.system.pojo.Synchro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String ID;
    private String USER_ID;
    private String TASK_ID;
    private String REFERENCE_ID;
    private String SENDER_ID;
    private String MESSAE_CATEGORY;
    private String MESSAGE_TYPE;
    private String MESSAGE_CONTENT;
    private String CREATION_DATE;
    private String IS_READ;
    private String LINK_TYPE;
    private String LINK_PARAM_0;
    private String IDENTIFIER;
    private String OPEN_TYPE;
    private String USER_HISTORY_MESSAGE_ID;
    private String IMPORTAN_LEVEL;
}
