package org.zerhusen.ams.payload;

import java.util.Collection;

import org.zerhusen.ams.model.AmsPatientQueries;
import org.zerhusen.ams.model.AmsQueryResponse;

public class QueryPayload {

	private AmsPatientQueries query;
	
	private Collection<AmsQueryResponse> responses;

	public QueryPayload() {
	
	}

	public QueryPayload(AmsPatientQueries query, Collection<AmsQueryResponse> responses) {
	
		this.query = query;
		this.responses = responses;
	}

	public AmsPatientQueries getQuery() {
		return query;
	}

	public void setQuery(AmsPatientQueries query) {
		this.query = query;
	}

	public Collection<AmsQueryResponse> getResponses() {
		return responses;
	}

	public void setResponses(Collection<AmsQueryResponse> responses) {
		this.responses = responses;
	}
	
	
	
	
}
