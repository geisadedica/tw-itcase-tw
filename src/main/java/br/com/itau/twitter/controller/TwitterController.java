package br.com.itau.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.itau.twitter.model.UserTwitter;
import br.com.itau.twitter.service.TwitterService;

@RestController
public class TwitterController {
	private final TwitterService twitterService;
	
	@Autowired
	public TwitterController(TwitterService twitterService) {
		this.twitterService = twitterService;
	}
	
	
	@RequestMapping("/most-follow-twitter")
	public String getMostFollowsTwitter() {
		UserTwitter[] resp = twitterService.getUserMostFollowers();
		
		StringBuilder retData = new StringBuilder("<html>" +
			       "<body>" +
			       "<table border ='1'>" +
			       "<tr>" +
			       "<td>User Name</td>" +
			       "<td>Followers</td>" +
			       "</tr>");
		
       for(int i=0; i<5;i++) {
    	   retData.append("<tr><td>");
    	   retData.append(resp[i].getName());
    	   retData.append("</td><td>");
    	   retData.append(resp[i].getCountFollow());
    	   retData.append("</td></tr>");
       };

       retData.append("</table>");
       retData.append("</body>");
       retData.append("</html>");
			       
		return retData.toString();
	}
	
}
