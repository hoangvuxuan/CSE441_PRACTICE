package vn.edu.tlu.ex22;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class XMLParser {

    public String getXmlFromUrl(String url) {
        String xml = null;
        try {
            // Tạo client và thiết lập kết nối HTTP
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            // Gửi yêu cầu đến server và nhận phản hồi
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}

