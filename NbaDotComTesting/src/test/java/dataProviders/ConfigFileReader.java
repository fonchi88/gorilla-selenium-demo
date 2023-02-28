package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;

    private String propertyFilePath;

    public ConfigFileReader(String fileName){

        propertyFilePath = "configs//"+fileName+".properties";
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();

            try{
                properties.load(reader);
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("properties file not found: "+e);
        }
    }

    public String getUrl(){
        String url = properties.getProperty("baseUrl");
        if(url != null) return url;
        else throw new RuntimeException("Base url not specified in configurations");
    }
}
