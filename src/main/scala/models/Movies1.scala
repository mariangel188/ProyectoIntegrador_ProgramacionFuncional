package models

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import java.io.File

case class Movies1(
                    adult: Boolean,
                    belongs_to_collection: String,
                    budget: Int,
                    genres: String, // JSON como cadena
                    homepage: String,
                    id: Int,
                    imdb_id: String,
                    original_language: String,
                    original_title: String,
                    overview: String,
                    popularity: Double,
                    poster_path: String,
                    production_companies: String, // JSON como cadena
                    production_countries: String, // JSON como cadena
                    release_date: String,
                    revenue: Long,
                    runtime: Option[Double],
                    spoken_languages: String, // JSON como cadena
                    status: String,
                    tagline: String,
                    title: String,
                    video: Boolean,
                    vote_average: Double,
                    vote_count: Int,
                    keywords: String, // JSON como cadena
                    cast: String, // JSON como cadena
                    crew: String, // JSON como cadena
                    ratings: String // JSON como cadena
                  )

object ejemplo6_Movies extends App{
  val path2DataFile2 = "C:/Users/Maria Angel/Downloads/pi_movies_complete.csv"

  // Configurar lectura del CSV con delimitador ';'
  val dataSource2 = new File(path2DataFile2)
    .readCsv[List, Movies1](rfc.withHeader.withCellSeparator(';'))

  // Filtrar filas válidas
  val rows_ok = dataSource2.collect {
    case Right(movie) => movie
  }
  val rows_fall = dataSource2.collect{
    case Left(error) => error
  }

  val len_rows_ok = rows_ok.length
  val len_rows_fail = rows_fall.length

  println(s"Numero de datos correctos: ${len_rows_ok}")
  println(s"Numero de datos incorrectos: ${len_rows_fail}")

  /*val sum_runtime = rows_ok.map(_.runtime).sum
  println(s"Suma de runtime: ${sum_runtime}")

  val max_runtime = rows_ok.map(_.runtime).max
  println(s"Max. de runtime: ${max_runtime}")*/
}

object parte2Analisis extends App {
  val path2DataFile2 = "C:/Users/Usuario/Downloads/pi_movies_complete.csv"

  val dataSource2 = new File(path2DataFile2)
    .readCsv[List, Movies1](rfc.withHeader.withCellSeparator(';'))

  // Filtrar filas válidas
  val rows_ok = dataSource2.collect {
    case Right(movie) => movie
  }
  val rows_fall = dataSource2.collect {
    case Left(error) => error
  }
}

