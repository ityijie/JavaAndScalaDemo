package loadLocaldata;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by Administrator on 2016/12/1.
 */
public class loaddata {
    private static Logger logger = LoggerFactory.getLogger(loaddata.class);
    public static void main(String[] args) throws Exception {

        MQConfigure device_status = loadMQConfigure("mq.xml", "device_status");
        logger.info("测试"+device_status.getName());
        logger.info("测试"+device_status.getFailover());
        logger.info("测试"+device_status.getPassword());
        logger.info("测试"+device_status.getUrl());
        logger.info("测试"+device_status.getUsername());


    }

    public static MQConfigure loadMQConfigure(String fileName, String configureName) throws IOException {
        List<URL> urls = new ArrayList<URL>();
        Enumeration<URL> es = Thread.currentThread().getContextClassLoader().getResources(fileName);
        if (es != null) {
            while (es.hasMoreElements()) {
                urls.add(es.nextElement());
            }
        }
        if (urls.size() == 0) {
            throw new java.lang.RuntimeException("no mq config find! please put " + fileName + " in your classpath");
        }
        Map<String, MQConfigure> configures = new HashMap<String, MQConfigure>();
        for (URL url : urls) {
            logger.info("loadMQConfigure url:" + url.getFile());
            loadConfigureFromURL(url, configures);
        }
        return configures.get(configureName);
    }

    @SuppressWarnings("unchecked")
    private static void loadConfigureFromURL(URL url, Map<String, MQConfigure> configures) {
        InputStream in = null;
        try {
            in = url.openStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(in);
            Element rootElement = document.getRootElement();
            List<Element> startElements = (ArrayList<Element>) rootElement.elements();
            for (Element startElement : startElements) {
                String tag = startElement.getName();
                if (tag.equalsIgnoreCase("mq")) {
                    // name
                    MQConfigure configure = new MQConfigure();
                    if (startElement.attributeValue(new org.dom4j.QName("name")) != null) {
                        configure.setName(startElement.attributeValue(new org.dom4j.QName("name")));
                        configure.setFailover(configure.getName());
                    }
                    else {
                        throw new RuntimeException("mq name can't not be null!");
                    }
                    // URL
                    if (startElement.attributeValue(new org.dom4j.QName("url")) != null) {
                        configure.setUrl(startElement.attributeValue(new org.dom4j.QName("url")));
                    }
                    else {
                        throw new RuntimeException("mq url can't not be null!");
                    }
                    // username
                    if (startElement.attributeValue(new org.dom4j.QName("username")) != null) {
                        configure.setUsername(startElement.attributeValue(new org.dom4j.QName("username")));
                    }
                    // password
                    if (startElement.attributeValue(new org.dom4j.QName("password")) != null) {
                        configure.setPassword(startElement.attributeValue(new org.dom4j.QName("password")));
                    }
                    // failover
                    if (startElement.attributeValue(new org.dom4j.QName("failover")) != null) {
                        configure.setFailover(startElement.attributeValue(new org.dom4j.QName("failover")));
                    }
                    // // threads
                    // if (startElement.attributeValue(new org.dom4j.QName("threads")) != null) {
                    // configure.threads = Integer
                    // .valueOf(startElement.attributeValue(new org.dom4j.QName("threads")));
                    // }
                    configures.put(configure.getName(), configure);
                }
            }
        } catch (Exception e) {
            logger.error(new StringBuffer("mq loadConfigure error !").append(" config url :")
                    .append(url.getFile()).toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {}
            }
        }
    }
}
