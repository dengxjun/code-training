package com.serialization;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/14
 */
public class JsonDeserializationSchema implements DeserializationSchema {
    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        return null;
    }

    @Override
    public boolean isEndOfStream(Object o) {
        return false;
    }

    @Override
    public TypeInformation getProducedType() {
        return null;
    }
}
