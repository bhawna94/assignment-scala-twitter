package edu.knoldus

import com.typesafe.config.ConfigFactory
import twitter4j._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

//scalastyle:off
object TweetApplication extends App {

  val config = ConfigFactory.load("application.conf")
  val consumerKey = config.getString("keydetails.details.consumerKey")
  val consumerSecret = config.getString("keydetails.details.consumerSecret")
  val accessToken = config.getString("keydetails.details.accessToken")
  val accessTokenSecret = config.getString("keydetails.details.accessTokenSecret")
  val obj = new TweeterApplication
  val twitter: Twitter = obj.getTwitterInstance(consumerKey, consumerSecret, accessToken, accessTokenSecret)
  val sleepCount = 1000
  val log = Logger.getLogger(this.getClass)
  val input = "#Budget2018"
  val result = obj.searchingTweetsOnTheBasisOfHashTag(input, twitter)

  val tweetCount = obj.findingNumberOfTweets(result)
  tweetCount onComplete {
    case Success(count) => log.info("\nNumber of tweets are " + count + "\n")
    case Failure(count: Throwable) => log.info(s"$count")
  }

  val averageTweetsPerDay = obj.findingAverageTweetsPerDay(result)
  averageTweetsPerDay onComplete {
    case Success(average) => log.info("Average tweets per day " + average + "\n")
    case Failure(average: Throwable) => log.info(s"$average")
  }

  val averageRetweet = obj.findAverageRetweet(result)
  averageRetweet onComplete {
    case Success(retweetCount) => log.info("Average retweets per tweet " + retweetCount + "\n")
    case Failure(retweetCount: Throwable) => log.info(s"$retweetCount")
  }

  val averageLikes = obj.findAverageLikes(result)
  averageLikes onComplete {
    case Success(likescount) => log.info("Average likes per tweet " + likescount + "\n")
    case Failure(likescount: Throwable) => log.info(s"$likescount")
  }

  Thread.sleep(1000)
}
