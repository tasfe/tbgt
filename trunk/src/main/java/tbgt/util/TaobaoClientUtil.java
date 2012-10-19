package tbgt.util;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

import java.util.Locale;

public class TaobaoClientUtil {

    public static TaobaoClient getTaobaoClient() {

        String appKey = TbgtResourceUtil.getInstance().getMessage("appkey");
        String appSecret = TbgtResourceUtil.getInstance().getMessage("appSecret");
        String topServerUrl = TbgtResourceUtil.getInstance().getMessage("top.server.url");
        if ("true".equals(TbgtResourceUtil.getInstance().getMessage("proxySet"))) {
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("proxyHost", TbgtResourceUtil.getInstance().getMessage("proxyHost"));
            System.getProperties().put("proxyPort", TbgtResourceUtil.getInstance().getMessage("proxyPort"));
        }
        TaobaoClient taobaoClient = null;
        String taobaoclient_class = TbgtResourceUtil.getInstance().getMessage("tabao.client.class");
        try {
            taobaoClient = (TaobaoClient)Class.forName(taobaoclient_class).getConstructor(String.class,String.class,String.class).newInstance(topServerUrl, appKey, appSecret);
        } catch (Exception e) {
           taobaoClient = new DefaultTaobaoClient(topServerUrl, appKey, appSecret); 
        }
        return taobaoClient;
    }
}
