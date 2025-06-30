package com.et.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Point;

import java.io.IOException;

/*
* 自定义序列化器
* */
//public class PointSerializer extends JsonSerializer<Point> {
//    @Override
//    public void serialize(Point value, JsonGenerator gen, SerializerProvider provider)
//        throws IOException {
//        gen.writeString(String.format("POINT (%f %f)", value.getX(), value.getY()));
//    }
//}