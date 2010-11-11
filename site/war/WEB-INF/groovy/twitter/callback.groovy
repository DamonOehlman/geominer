import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.RequestToken;
import Storage;

Twitter twitter = new Twitter();
twitter.setOAuthConsumer('K7BhAacKov8QoFwEHYRU7Q', 'mgJvRtKIhZ9lXHY39RlM2BRoLLcpQpk8EG1FTMSIA');

// Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
String verifier = request.getParameter("oauth_verifier");

twitter.getOAuthAccessToken(requestToken, verifier);

// request.getSession().removeAttribute("twitter");
request.getSession().removeAttribute("requestToken");

Storage.createPerson();
response.outputStream << 'Verified successfully: ' + twitter.verifyCredentials().name;