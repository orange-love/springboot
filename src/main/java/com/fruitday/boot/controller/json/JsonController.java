package com.fruitday.boot.controller.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@Controller
public class JsonController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/time")
    @ResponseBody
    public String time(Date date) {
        System.out.println(date);
        return "hello world! By boot" + date;
    }

    /**
     * 序列化
     * @return
     * @throws IOException
     */
    @GetMapping("/parser")
    @ResponseBody
    public String parser() throws IOException {
        String json = "{\"name\":\"test\",\"id\":20}";
        JsonFactory factory = objectMapper.getFactory();
        JsonParser parser = factory.createParser(json);
        String key = "", value = "";
        JsonToken token = parser.nextToken();//是一个枚举类型
        while (token != JsonToken.END_OBJECT) {
            if (token == JsonToken.FIELD_NAME) {
                key += ("-" + parser.getCurrentName());
            } else if (token.toString().contains("VALUE")) {
                if (token == JsonToken.VALUE_STRING) {
                    value += ("-" + parser.getValueAsString());
                } else if (token == JsonToken.VALUE_NUMBER_INT) {
                    value += ("-" + parser.getValueAsInt());
                } else {
                    value += ("-" + parser.getText());
                }
            }
            token = parser.nextToken();
        }
        parser.close();
        return "parser:" + key + "=" + value;
    }

    /**
     * 反序列化
     * @return
     * @throws IOException
     */
    @GetMapping("/generator")
    @ResponseBody
    public String generator() throws IOException {
        JsonFactory factory = objectMapper.getFactory();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);

        generator.writeStartObject();
        generator.writeStringField("name", "wtest");
        generator.writeNumberField("id", 12);
        generator.writeEndObject();
        generator.close();

        return "generator:" + writer.toString();
    }
}
