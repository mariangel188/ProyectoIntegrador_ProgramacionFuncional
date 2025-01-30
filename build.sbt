
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
lazy val root = (project in file("."))
  .settings(
    name := "ProyectoIntegrador_ProgramacionFuncional",
    libraryDependencies ++= Seq(
      "io.reactivex" %% "rxscala" % "0.27.0",
      "com.lihaoyi" %% "scalarx" % "0.4.1",
      "com.nrinaudo" %% "kantan.csv" % "0.6.1",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.6.1",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "org.apache.spark" %% "spark-core" % "3.3.0",
      "org.apache.spark" %% "spark-sql" % "3.3.0"
    )
  )

