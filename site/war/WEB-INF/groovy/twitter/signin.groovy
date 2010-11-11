import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.RequestToken;

Twitter twitter = new Twitter();

twitter.setOAuthConsumer('K7BhAacKov8QoFwEHYRU7Q', 'mgJvRtKIhZ9lXHY39RlM2BRoLLcpQpk8EG1FTMSIA');

StringBuffer callbackURL = request.getRequestURL();
int index = callbackURL.lastIndexOf("/");
callbackURL.replace(index, callbackURL.length(), "").append("/callback.groovy");

RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
request.getSession().setAttribute("requestToken", requestToken);

redirect requestToken.getAuthenticationURL();