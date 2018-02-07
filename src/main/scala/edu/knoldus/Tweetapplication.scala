package edu.knoldus

import twitter4j._

import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object Tweetapplication extends App {

  val log = Logger.getLogger(this.getClass)
  val obj1 = new Tweeterapplication
  val input = "#Budget2018"
  val twitter: Twitter = obj1.getTwitterInstance()
  val result = obj1.searchingTweetsOnTheBasisOfHashtag(input,twitter)

  val tweetcount = obj1.findingnumberoftweets(result)
   tweetcount onComplete {
    case Success(count) => log.info("Number of tweets are " + count)
    case Failure(count : Throwable) => log.info(s"$count")
  }

  val averagetweetsperday = obj1.findingaveragetweetsperday(result)
     averagetweetsperday onComplete {
       case Success(average) => log.info("Average tweets per day " + average)
       case Failure(average : Throwable) => log.info(s"$average")
     }

  val averageretweet = obj1.averageretweet(result)
     averageretweet onComplete {
       case Success(retweetcount) => log.info("Average retweets per tweet "+  retweetcount)
       case Failure(retweetcount: Throwable) => log.info(s"$retweetcount")
     }

  val averagelikes = obj1.averagelikes(result)
      averagelikes onComplete {
        case Success(likescount) => log.info("Average likes per tweet " + likescount)
        case Failure(likescount: Throwable) => log.info(s"$likescount")
      }

 Thread.sleep(10000)
}
