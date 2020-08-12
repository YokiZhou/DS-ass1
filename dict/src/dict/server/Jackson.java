/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Jackson {
	
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        //use writeValueAsString method turn word to JSON
        Jackson json = new Jackson("10001");
        String jsonStr = mapper.writeValueAsString(json);
        System.out.println(jsonStr);
        //mapper.writeValue(new File("src/com/cn/test/JsonText.json"), json);
    }
    
    private String key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    public Jackson(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public Jackson() {
    }

    public Jackson(String key) {
        this.key = key;
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

}
