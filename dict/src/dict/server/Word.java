/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize.*;

import java.io.IOException;

public class Word {
	
    public static String wordToJson(Word word) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        	//return object in json format
        	String jsonStr = mapper.writeValueAsString(word);
        	return jsonStr;
        	//mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
       
     
    }

	
    public static Word jsonToWord(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Word word = mapper.readValue(jsonStr, Word.class);
        return word;
    }
    
    private String instruction;

    private String key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg=null;
    
    public Word(String key, String value, String instruction) {
        this.key = key;
        this.value = value;
        this.instruction = instruction;
    }
    
    public Word() {
    }

    public Word(String key) {
        this.key = key;
    }

    public Word(String key, String value) {
        this.key = key;
        this.value = value;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Word{" +
                "instruction='" + instruction + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

}

