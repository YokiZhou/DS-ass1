/*COMP90015 Assignment1  
  Shengqi Zhou 893295 */
package dict.server;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class Dictionary {

    public static Properties properties = new Properties();
    private static HashMap<String,String> dict = new HashMap<>();

    static{
        try {
            InputStream in = Dictionary.class.getClassLoader().getResourceAsStream("dictionary.properties");

            properties.load(in);

            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + "=" + properties.getProperty(key));
                dict.put(key,properties.getProperty(key));
            }

        } catch (FileNotFoundException e) {
            System.out.println(".properties does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized String find(String key){
        String value = dict.get(key);
        if(value ==null){
            return null;
        }else{
            return value;
        }
    }

    public synchronized String add(String key,String value) throws IOException {

        if(key == null || value == null) {
        	return "word & meaning can not be null!";
        }
        else if(dict.containsKey(key)){
            return "duplicate word!";
        }
        else {
        	dict.put(key,value);
            return null;
        }
    	/*if(key!=null && value!=null){
            if(dict.containsKey(key)){
                return "duplicate word!";
            }
            else {
            dict.put(key,value);
            return null;
            }
        } else if(key == null && value ==null){
        	//dict.remove(key);
            return "word & meaning can not be null!";
        } else if(key==null){
        	//dict.remove(key);
            return "word can not be null!";
        } else{
        	//dict.remove(key);
            return "meaning can not be null!";
        }*/
    }

    public synchronized String remove(String key){
        if(dict.containsKey(key)) {  //表示找到了
            dict.remove(key);
            return null;
        }else{
            return "can not find "+key+" in dictionary!";
        }
    }
}

