package edu.knoldus
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.concurrent.ExecutionContext.Implicits.global
import edu.knoldus.tweetapplication.twitter
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import scala.collection.JavaConverters._
import scala.concurrent.Future

class Tweeterapplication extends Instance {

  def searchingTweetsOnTheBasisOfHashtag(input: String, twitter: Twitter): List[Status] = {

    val query = new Query(input)
    query.setCount(100)
    twitter.search(query).getTweets.asScala.toList
  }

  /**
    *
    * @param inputvalue
    * @return
    */
  def findingnumberoftweets(inputvalue: List[Status]): Future[Int] = Future {

    inputvalue.size
  }

  /**
    *
    * @param inputvalue
    * @return
    */

  def findingaveragetweetsperday(inputvalue: List[Status]): Future[Long] = Future {
    try {
      val startingDate = "2018-01-30"
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val oldDate = LocalDate.parse(startingDate, formatter)
      val currentDate = "2018-02-03"
      val newDate = LocalDate.parse(currentDate, formatter)
      val diff = newDate.toEpochDay() - oldDate.toEpochDay()
      inputvalue.size / diff
    }
    catch {
      case exception: Exception => throw (exception)
    }

  }

  /**
    *
    * @param inputtweets
    * @return
    */
  def averageretweet(inputtweets: List[Status]): Future[Int] = Future {
    try {
      val retweetcount = inputtweets.map(_.getRetweetCount)
      retweetcount.sum / retweetcount.size
    }
    catch {
      case exception: Exception => throw(exception)
    }
  }

  /**
    *
    * @param inputtweets
    * @return
    */

  def averagelikes(inputtweets: List[Status]): Future[Int] = Future {

    try{
    val likecount = inputtweets.map(_.getFavoriteCount)
    likecount.sum / likecount.size
  }
    catch
      {
        case exception: Exception => throw(exception)

      }
  }
}
