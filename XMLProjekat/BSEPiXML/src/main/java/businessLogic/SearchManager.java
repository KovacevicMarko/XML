package businessLogic;

import java.util.ArrayList;
import java.util.HashMap;

import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.MatchLocation;
import com.marklogic.client.query.MatchSnippet;

public class SearchManager 
{
	public SearchManager()
	{
		
	}
	public HashMap<String,ArrayList<String>> documentsMatchedWithParametar(String parameterofSearch, String uriOfCollection, DatabaseManager databaseManager) 
	{
		MatchDocumentSummary matches[] = databaseManager.searchByField(parameterofSearch, uriOfCollection);

        MatchDocumentSummary result;
        MatchLocation locations[];
        String text;

        HashMap<String,ArrayList<String>> returnMap = new HashMap<>();

        for (int i = 0; i < matches.length; i++) {
            result = matches[i];
            ArrayList<String> listOfMatched = new ArrayList<>();


            locations = result.getMatchLocations();


            for (MatchLocation location : locations) {


                String item = "";
                for (MatchSnippet snippet : location.getSnippets()) {
                    text = snippet.getText().trim();

                    if (!text.equals("")) {

                        item +=snippet.isHighlighted() ? text.toUpperCase() : text;

                        item +=" ";
                    }
                }
                listOfMatched.add(item);

            }
            
            returnMap.put(result.getUri(),listOfMatched);

            }

            return returnMap;
    }
	
	public HashMap<String,ArrayList<String>> documentsMatchedWithParametarAndTag(String tag,String parameterofSearch, String uriOfCollection, DatabaseManager databaseManager)
	{
        MatchDocumentSummary matches[] = databaseManager.searchByField(parameterofSearch, uriOfCollection);

        MatchDocumentSummary result;
        MatchLocation locations[];
        String text;

        HashMap<String,ArrayList<String>> returnMap = new HashMap<>();

        for (int i = 0; i < matches.length; i++) {
            result = matches[i];
            ArrayList<String> listOfMatched = new ArrayList<>();


            locations = result.getMatchLocations();


            for (MatchLocation location : locations) {
                String putanja = location.getPath();
                String retSplit [] = putanja.split(":");

                if(retSplit[retSplit.length-1].split("\\[")[0].equals(tag)) {
                    String item = "";
                    for (MatchSnippet snippet : location.getSnippets()) {
                        text = snippet.getText().trim();

                        if (!text.equals("")) {

                            item += snippet.isHighlighted() ? text.toUpperCase() : text;

                            item += " ";
                        }
                    }
                    listOfMatched.add(item);
                }

            }

            returnMap.put(result.getUri(),listOfMatched);

        }

        return returnMap;
    }

}
