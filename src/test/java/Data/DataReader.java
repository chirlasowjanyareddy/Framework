package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        // read json to string
        String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src//test//java//Data//PurchaseOrder.json"));
        // string to haskmap
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
}
