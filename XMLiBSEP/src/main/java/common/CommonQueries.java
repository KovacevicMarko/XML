package common;

/**
 * Pomocna klasa za definisanje query-a.
 * @author marko
 *
 */
public class CommonQueries 
{

	/*
	 * Vrati sve predlozene akte.
	 */
	public static String getAllProposedActs()
	{
		StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AKT_PREDLOZEN_COL_ID);
	    query.append("\")");
		return query.toString();
	}
	
	/*
	 * Vrati sve usvojene akte.
	 */
	public static String getAllApprovedActs()
	{
		StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AKT_USVOJEN_COL_ID);
	    query.append("\")");
		return query.toString();
	}
	
	/*
	 * Vrati sve predolzene amandmane.
	 */
	public static String getAllProposedAmandmans()
	{
		StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AMANDMAN_PREDLOZEN_COL_ID);
	    query.append("\")");
	    return query.toString();
	}
	
	
	
	
	/*
	 * Vrati sve usvojene amandmane.
	 */
	public static String getAllApprovedAmandmans()
	{
		StringBuilder query = new StringBuilder();
	    query.append("fn:collection(\"");
	    query.append(DatabaseConnection.AMANDMAN_USVOJEN_COL_ID);
	    query.append("\")");
	    return query.toString();
	}
}
