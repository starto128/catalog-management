package kr.co.strato.core.config;

import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringArrayConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if(ObjectUtils.isEmpty(attribute)){
            return null;
        }
        return attribute.stream().collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return Arrays.asList(dbData.split("\\|"));
    }
}
