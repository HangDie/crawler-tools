package cn.hd.crawler.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author 张富华
 * @date 2019/2/14
 */
public final class ObjectMapperUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static final Logger log = LoggerFactory.getLogger(ObjectMapperUtil.class);

    /**
     * json转对象数组
     * @param json
     * @param clazz 类型
     * @return
     */
    public static <T> List<T> convertList(String json, Class<T> clazz){
        if(json == null || json.length() == 0) return null;
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        try{
            return mapper.readValue(json, javaType);
        }catch (Exception e){
            log.error("json转化失败", e);
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 对象转化
     * @return
     */
    public static <T> T convertObj(String json, Class<T> clazz){
        if(json == null || json.length() == 0) return null;
        try{
            return mapper.readValue(json, clazz);
        }catch (Exception e){
            log.error("json转化失败", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 对象转string
     * @param data
     * @return
     */
    public static String objectToString(Object data){
        if(data == null) return null;
        try{
            return mapper.writeValueAsString(data);
        }catch (Exception e){
            log.error("对象转化失败", e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
