package org.mondemand.transport;

import org.mondemand.Context;
import org.mondemand.Level;
import org.mondemand.LogMessage;
import org.mondemand.StatsMessage;
import org.mondemand.Transport;

public class StderrTransport implements Transport {
	
	public StderrTransport() {
	}

	public void sendLogs(String programId, LogMessage[] messages, Context[] contexts) {
		if(messages == null) return;
		
		for(int i=0; i<messages.length; ++i) {
			try {
				if(messages[i].getTraceId() != null) {
					System.err.println("[" + Level.STRINGS[messages[i].getLevel()] + "] - " + 
							messages[i].getFilename()  + ":" + messages[i].getLine() + " " +
							"[" + messages[i].getTraceId() + "] " + 
							messages[i].getMessage() + "(" + messages[i].getRepeat() + ")");					
				} else {
					System.err.println("[" + Level.STRINGS[messages[i].getLevel()] + "] - " + 
						messages[i].getFilename()  + ":" + messages[i].getLine() + " " +
						messages[i].getMessage() + "(" + messages[i].getRepeat() + ")");
				}
			} catch(Exception e) {}
		}
	}

	public void sendStats(String programId, StatsMessage[] messages, Context[] contexts) {
		if(messages == null) return;
		
		for(int i=0; i<messages.length; ++i) {
			try {
				System.err.println("[" + messages[i].getKey() + "] " + "counter=" + messages[i].getCounter());
			} catch(Exception e) {}
		}
	}

	public void shutdown() {
		// do nothing
	}

}