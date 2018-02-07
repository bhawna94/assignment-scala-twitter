package edu.knoldus

import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

trait Instance {


    def getTwitterInstance(): Twitter = {
      val configurationBuilder = new ConfigurationBuilder()
        .setDebugEnabled(true)
        .setOAuthConsumerKey("2SZL0AUdhysIo4UcuRgjPl8np")
        .setOAuthConsumerSecret("4fNTe3GnxW3YKRIORSBVKDsyiJlEpDx2zQLuotWz6GzkvYI7Uw")
        .setOAuthAccessToken("958855995442081792-nY9TrHu5wqFezVwspqUR6lEgBU907Si")
        .setOAuthAccessTokenSecret("9T9kIOe3X8WoziXq8oMLYkABRXvzpC5beZaWGfxhbrVaD")
       new TwitterFactory(configurationBuilder.build).getInstance
    }


}
