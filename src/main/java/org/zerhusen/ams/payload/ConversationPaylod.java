package org.zerhusen.ams.payload;

import java.util.Collection;

import org.zerhusen.ams.model.AmsPatientConversation;

public class ConversationPaylod {

	public AmsPatientConversation conversation;
	
	public Collection<QueryPayload> queries;

	public Collection<QueryPayload> getQueries() {
		return queries;
	}

	public void setQueries(Collection<QueryPayload> queries) {
		this.queries = queries;
	}
	
	

	public AmsPatientConversation getConversation() {
		return conversation;
	}

	public void setConversation(AmsPatientConversation conversation) {
		this.conversation = conversation;
	}

	public ConversationPaylod() {
	
	}

	public ConversationPaylod(AmsPatientConversation conversation, Collection<QueryPayload> queries) {
		this.conversation = conversation;
		this.queries = queries;
	}

	
	
	
	
	
}
