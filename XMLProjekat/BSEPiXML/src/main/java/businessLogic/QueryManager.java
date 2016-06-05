package businessLogic;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;
import common.JaxbXmlConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.validation.Schema;

import java.io.File;
import java.util.ArrayList;

public class QueryManager<T> {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private DatabaseClient client;

    private ServerEvaluationCall invoker;

    private JaxbXmlConverter<T> converter;

    private Schema schema;

    public QueryManager(DatabaseClient client, Schema schema ,JaxbXmlConverter converter){
        try {
            this.client = client;
            this.converter = converter;
            this.schema = schema;
            invoker = client.newServerEval();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Izvrsavanje query-a.
     * @param query
     * @return
     */
    public ArrayList<T> executeQuery(String query){
        ArrayList<T> ret = null;
        try {
            EvalResultIterator response = null;
            // Invoke the query
            invoker.xquery(query);
            response = invoker.eval();
            ret = new ArrayList<>();
            for (EvalResult result : response) {
                if(converter.writeStringToFile(result.getString())){
                    T bean = converter.convertFromXml(new File("tmp.xml"),schema);
                    if (bean != null) {
                        ret.add(bean);
                    } else {
                         logger.error("[QueryManager] ERROR: Could not convert xml to JAXB bean!");
                    }
                } else {
                    logger.error("[QueryManager] ERROR: Could not write query result to file!");
                }
            }
        } catch (Exception e){
            System.out.println("Unexpected error: " + e.getMessage());
        }
        return ret;
    }
	
}
