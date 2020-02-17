package br.com.caseit.tw.model;

import lombok.Data;

@Data
public class UserTw {
	private String name;
	private Integer countFollow;
	
	public UserTw(String name, Integer countFollow) {
		super();
		this.name = name;
		this.countFollow = countFollow;
	}
	
}
