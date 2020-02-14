package br.com.itau.twitter.client;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import static br.com.itau.twitter.util.Constants.*;

import org.springframework.stereotype.Component;

@Component
public class TwitterClient {
	
	public Twitter getInstanceTwitter() throws Exception{
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	            .setOAuthConsumerKey(CONSUMER_KEY)
	            .setOAuthConsumerSecret(CONSUMER_SECRET)
	            .setOAuthAccessToken(ACCESS_TOKEN)
	            .setOAuthAccessTokenSecret(TOKEN_SECRET);

	    TwitterFactory tf = new TwitterFactory(cb.build());
	    	
	    return tf.getInstance();
	}
}
