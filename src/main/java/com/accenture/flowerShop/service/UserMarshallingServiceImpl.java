package com.accenture.flowerShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.oxm.Marshaller;

import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;


public class UserMarshallingServiceImpl {
    private Marshaller marshaller;
    @Autowired
    private Environment env;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    public void convertFromObjectToXMLAndSave(Object object)
            throws IOException {

        FileOutputStream os = null;
        String filepath = env.getProperty("marshaller.filepath");
        try {
            os = new FileOutputStream(filepath);
            getMarshaller().marshal(object, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
