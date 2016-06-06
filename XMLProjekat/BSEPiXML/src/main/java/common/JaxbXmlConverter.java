package common;

import javax.xml.bind.*;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Klasa za konvertoranje JAXB binova u xml fajl i obrnuto.
 * @author marko
 *
 * @param <T>
 */
public class JaxbXmlConverter<T> 
{
	/**
	 * Konvertovanje Jaxb u xml.
	 * @param bean
	 * @return
	 */
	public boolean ConvertJaxbToXml(T  bean)
	{
        boolean ret = false;
        File file = new File("tmp.xml");
        FileOutputStream fileOutputStream = null;
        try 
        {
            fileOutputStream = new FileOutputStream(file);
            JAXBContext jc = JAXBContext.newInstance("model");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.gradskaskupstina.rs/");
            marshaller.marshal(bean,fileOutputStream);
            fileOutputStream.close();
            ret = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }
	
	/**
	 * Konvertovanje iz xml-a u Jaxb.
	 * @param file
	 * @param schema
	 * @return
	 */
	public T convertFromXml(File file, Schema schema)
	{
        T ret = null;
        try 
        {
            JAXBContext jc = JAXBContext.newInstance("model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(schema);
            ret = (T) JAXBIntrospector.getValue(unmarshaller.unmarshal(file));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }
	
    /**
	 * Upisati string sadrzaj unutar xml-a.
     */
    public boolean writeStringToFile(String fileContent) {
        boolean ret = false;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("tmp.xml");
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.close();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

}
