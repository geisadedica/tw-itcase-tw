package br.com.itau.twitter.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.itau.twitter.client.TwitterClient;
import br.com.itau.twitter.model.UserTwitter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

@Slf4j
@Service
@AllArgsConstructor
public class TwitterService {
	
	private static List<String> tagList = Arrays.asList(
			"#cloud", "#container", "#devops", "#aws", "#microservices",
			"#docker", "#openstack", "#automation", "#gcp", "#azure",
			"#istio", "#sre");
		
	private static Integer MAX_USER = 5;
	private static UserTwitter userTwitter[] = new UserTwitter[MAX_USER];
	
	
	
	@Autowired
	TwitterClient tCliente;
	
	public UserTwitter[] getUserMostFollowers() {
		
		try {
			Twitter twitter = tCliente.getInstanceTwitter();
			
		    for(String tag: tagList) {
		    	Query query = new Query(tag);
			    QueryResult result = twitter.search(query);
				List<Status> statuses = result.getTweets();
				
				if(statuses != null && !statuses.isEmpty()) {
					for (Status status : statuses) {
						User user = status.getUser();
						Boolean hasList = false;
						
						for(int i = 0; i < MAX_USER; i++) {
							if(userTwitter[i] != null && userTwitter[i].getName().equals(user.getName())) {
								hasList = true;
								break;
							}
						}
						
						if(!hasList) {
							for(int i = 0; i < MAX_USER; i++) {
								if(userTwitter[i] == null || userTwitter[i].getCountFollow() < user.getFollowersCount()) {
									userTwitter[i] = new UserTwitter(user.getName(), user.getFollowersCount());
									break;
								}
							}													
						}
					}					
				}
		    }

		    for(int i = 0; i < MAX_USER; i++) {
		    	log.info("User: " + userTwitter[i].getName() + " Count: " + userTwitter[i].getCountFollow());
		    }
			
		    return userTwitter;
		    
		}catch(Exception e) {
			log.error("Erro: ", e.getMessage());
		}
		 return null;
	}

	public TwitterService() {
		super();
	}
}
