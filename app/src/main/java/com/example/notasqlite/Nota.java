package com.example.notasqlite;

public class Nota {
	
	String id, body;
	
	public Nota(String id, String body){
		this.id = id;
		this.body = body;
	}
	
	public String getId() {
		return id;
	}
	
	public String getBody() {
		return body;
	}
	
}