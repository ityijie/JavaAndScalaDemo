name := "demo1"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.2"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.6.2"

libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.2"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql_2.10
libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.6.2"


libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.2"

libraryDependencies += "com.google.code.gson" % "gson" % "2.7"

//libraryDependencies += "io.netty" % "netty-all" % "5.0.0.Alpha2"

libraryDependencies += "org.apache.activemq" % "activemq-core" % "5.5.1"

libraryDependencies += "org.springframework" % "spring-beans" % "4.3.2.RELEASE"

libraryDependencies += "org.springframework" % "spring-jdbc" % "3.2.0.RELEASE"

libraryDependencies += "commons-collections" % "commons-collections" % "3.2.1"

libraryDependencies += "commons-pool" % "commons-pool" % "1.6"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.20"

libraryDependencies += "c3p0" % "c3p0" % "0.9.1.2"

libraryDependencies += "junit" % "junit" % "4.12"

// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "javax.mail" % "mail" % "1.4.7"

// https://mvnrepository.com/artifact/dom4j/dom4j
libraryDependencies += "dom4j" % "dom4j" % "1.6.1"



// https://mvnrepository.com/artifact/org.apache.flume/flume-ng-core
libraryDependencies += "org.apache.flume" % "flume-ng-sdk" % "1.6.0"

// https://mvnrepository.com/artifact/org.apache.flume.flume-ng-clients/flume-ng-log4jappender
libraryDependencies += "org.apache.flume.flume-ng-clients" % "flume-ng-log4jappender" % "1.6.0"






resolvers += "repo2" at "http://repo2.maven.org/maven2/"