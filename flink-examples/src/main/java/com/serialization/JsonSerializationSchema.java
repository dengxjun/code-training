package com.serialization;

import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.serialization.SerializationSchema;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/14
 */
public class JsonSerializationSchema implements SerializationSchema {
    @Override
    public byte[] serialize(Object obj) {
        return JSONObject.toJSONString(obj).getBytes();
    }
}
