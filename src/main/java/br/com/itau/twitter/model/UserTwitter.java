package br.com.itau.twitter.model;

import lombok.Data;

@Data
public class UserTwitter {
	private String name;
	private Integer countFollow;
	
	public UserTwitter(String name, Integer countFollow) {
		super();
		this.name = name;
		this.countFollow = countFollow;
	}
	
}
