package edu.knoldus
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.concurrent.ExecutionContext.Implicits.global
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import scala.collection.JavaConverters._
import scala.concurrent.Future

class TweeterApplication  {

  def getTwitterInstance(consumerKey: String, consumerSecret: String, accessToken: String, accessTokenSecret: String): Twitter = {
    val configurationBuilder = new ConfigurationBuilder()
      .setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
    new TwitterFactory(configurationBuilder.build).getInstance
  }

  def searchingTweetsOnTheBasisOfHashTag(input: String, twitter: Twitter): List[Status] = {

    val count = 100
    val query = new Query(input)
    query.setCount(count)
    twitter.search(query).getTweets.asScala.toList
  }

  /**
    *
    * @param inputValue
    * @return
    */
  def findingNumberOfTweets(inputValue: List[Status]): Future[Int] = Future {

    inputValue.size
  }

  /**
    *
    * @param inputValue
    * @return
    */

  def findingAverageTweetsPerDay(inputValue: List[Status]): Future[Long] = Future {
    try {
      val startingDate = "2018-01-30"
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val oldDate = LocalDate.parse(startingDate, formatter)
      val currentDate = "2018-02-03"
      val newDate = LocalDate.parse(currentDate, formatter)
      val diff = newDate.toEpochDay() - oldDate.toEpochDay()
      inputValue.size / diff
    }
    catch {
      case exception: Exception => throw (exception)
    }

  }

  /**
    *
    * @param inputTweets
    * @return
    */
  def findAverageRetweet(inputTweets: List[Status]): Future[Int] = Future {
    try {
      val retweetcount = inputTweets.map(_.getRetweetCount)
      retweetcount.sum / retweetcount.size
    }
    catch {
      case exception: Exception => throw(exception)
    }
  }

  /**
    *
    * @param inputTweets
    * @return
    */

  def findAverageLikes(inputTweets: List[Status]): Future[Int] = Future {

    try{
    val likeCount = inputTweets.map(_.getFavoriteCount)
    likeCount.sum / likeCount.size
  }
    catch
      {
        case exception: Exception => throw(exception)

      }
  }
}
