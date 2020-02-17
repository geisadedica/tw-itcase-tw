package br.com.caseit.tw.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import br.com.caseit.tw.client.TwitterClient;
import br.com.caseit.tw.data.MongoDBClient;
import br.com.caseit.tw.model.UserTw;
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
public class TwService {
	
	private static List<String> tagList = Arrays.asList(
			"#cloud", "#container", "#devops", "#aws", "#microservices",
			"#docker", "#openstack", "#automation", "#gcp", "#azure",
			"#istio", "#sre");
		
	private static Integer MAX_USER = 5;
	private static UserTw usersTwitter[] = new UserTw[MAX_USER];
	
	@Autowired
	TwitterClient tCliente;
	
	//@Autowired
	//MongoDBClient mongoDb;
	
	public UserTw[] getUserMostFollowers() {
		
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
							if(usersTwitter[i] != null && usersTwitter[i].getName().equals(user.getName())) {
								hasList = true;
								break;
							}
						}
						
						if(!hasList) {
							for(int i = 0; i < MAX_USER; i++) {
								if(usersTwitter[i] == null || usersTwitter[i].getCountFollow() < user.getFollowersCount()) {
									usersTwitter[i] = new UserTw(user.getName(), user.getFollowersCount());
									break;
								}
							}													
						}
					}					
				}
		    }

		    for(int i = 0; i < MAX_USER; i++) {
		    	log.info("User: " + usersTwitter[i].getName() + " Count: " + usersTwitter[i].getCountFollow());
		    }
			
		    return usersTwitter;
		    
		}catch(Exception e) {
			log.error("Erro: ", e.getMessage());
		}
		 return null;
	}
	
	//public void saveList() {
	//	MongoOperations mongoOps = new MongoTemplate(mongoDb.getInstanceMongo(), "database");
	//    mongoOps.insert(usersTwitter);
	    
	//}

	public TwService() {
		super();
	}
}
