package com.system.mapper.Synchro;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MessageMapper {
    void InsetintoMessage(Map<String, Object> map);
}
