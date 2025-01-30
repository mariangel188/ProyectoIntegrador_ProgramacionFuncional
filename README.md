 # ProyectoIntegrador_PFR : "Análisis Exploratorio del Dataset PI 2024 Movies".

Repositorio del proyecto integrador - Programación Funcional y Reactiva   

**INTEGRANTES:**   
- DIANA ROCIO BERMEO CABRERA
- STEVEEN ALEXANDER CUENCA GUAMAN
- MARIA ANGEL ROJAS ROJAS
- JHON JOSUÉ CALLE CASTILLO
  
**DOCENTE:**  ING. Santiago Quiñones Cuenca

## INTRODUCCIÓN
El proyecto se centra en el análisis exploratorio del dataset PI 2024 Movies, disponible en la plataforma Kaggle. Este conjunto de datos contiene información detallada sobre películas, abarcando características como presupuestos, ingresos, idiomas originales, géneros, compañías productoras, países de filmación, popularidad y votaciones, entre otros atributos relevantes.

El propósito principal es aprovechar técnicas de análisis de datos para extraer patrones, identificar tendencias y obtener información clave que permita comprender mejor la dinámica de la industria cinematográfica. A través de este análisis, se busca responder preguntas como: ¿qué factores están asociados con el éxito de una película? ¿Qué géneros son los más populares? ¿Qué patrones se pueden observar en los presupuestos y los ingresos?

Este proyecto no solo ofrece un estudio descriptivo del dataset, sino que también sirve como una aplicación práctica de métodos estadísticos y analíticos en un contexto de datos reales.  
## OBJETIVOS

El objetivo de este proyecto es realizar un análisis exploratorio del dataset PI 2024 Movies, enfocándose en:

- Calcular estadísticas básicas para las columnas numéricas.
- Explorar distribuciones de frecuencia en columnas categóricas.
- Identificar patrones y relaciones clave entre diferentes atributos para obtener conocimientos útiles.
- Presentar visualizaciones y hallazgos que ayuden a comunicar los resultados de manera clara y efectiva.

## DICCIONARIO DE DATOS

En este proyecto, el diccionario de datos sirve como guía para entender las características del dataset PI 2024 Movies, describiendo los atributos disponibles, sus tipos de datos, su propósito y cualquier observación relevante sobre su interpretación o uso.   


| Nombre          | Tipo                | Propósito                              | Observaciones                                 |
|-------------------------|---------------------|------------------------------------------|---------------------------------------|
| adult  | Boolean  | Nos indica si la película es para adultos | Tiene valores que pueden ser: True o False |
| belongs_to_collection | String | Es el nombre de la colección de la película a la que pertenece | Puede estar vacío si no le pertenece a alguna colección |
| budget | Int | Es el presupuesto de la película | Se expresa en dólares|
| genres | String (JSON) | Es el género que va a tener cada película | Se necesita convertir para ver los géneros por lo que está en formato JSON|
| homepage | String | Es la página web oficial de la película | Si no hay página web puede estar vacío |
| id | Int | Es el identificador único de la película | Es el identificador para cada película |
| imdb_id| String | Es el identificador IMDb | Esta en formato ttxxxxxxx |
| original_language | String |Es el idioma original de la película | Es el código ISO|
| original_title | String | Es el titulo original de la película |               |
| overview | String |Es el resumen de la película | Es la descripción de la película |
| popularity  | Double   | Es la popularidad de la película  | Depende de varios factores como la cantidad de visualización o la crítica que va a tener. |
| poster_path | String | Es la ruta del poster de la película |Es la ruta para acceder al poster|
| production_companies | String (JSON) | Son las compañías que producen las películas | Se necesita convertir para listar las compañías por lo que está en formato JSON |
| production_countries | String (JSON)|Es el o los países donde se fue filmada la película | Se necesita convertir para listar los países por lo que está en formato JSON|
| release_date| String | Es la fecha de estreno | Esta en formato:  YYYY-MM-DD |
| revenue  | Long  | Son los ingresos que se generaron por la película | Se expresa en dólares|
| runtime | Option[Double]  | Es la duración de la película en minutos | Puede ser none si la duración no está disponible |
| spoken_languages | String (JSON) | Son los idiomas que se hablaron en la película  | Se necesita convertir para listar los idiomas por lo que está en formato JSON |
| status | String | Es el estado de la película | Puede ser released o rumored |
| tagline | String | Es el eslogan de la película | El eslogan puede estar vacío|
| title | String   | Es el título de la película |     |
| video | Boolean  | Nos indica si hay algún video disponible | Tiene valores que pueden ser true o false |
| vote_average | Double | Es el promedio de votos  | Esta en una escala de 0 a 10 |
| vote_count | Int  | Es el número total de votos |          |
| keywords | String (JSON) | Son las palabras clave que están asociados a la película| Se necesita convertir para listar las palabras clave por lo que está en formato JSON |
| cast  | String (JSON) | Es el reparto de la película | Se necesita convertir para listar los actores por lo que está en formato JSON |
| crew | String (JSON) | Es el equipo de la producción |Se necesita convertir para listar los miembros del equipo por lo que está en formato JSON|
| ratings | String (JSON)  | Son las calificaciones de las películas  |Se necesita convertir para listar las calificaciones por lo que está en formato JSON |

## ANÁLISIS DE DATOS EN COLUMNAS NUMÉRICAS

El análisis de datos numéricos es un proceso que implica la recopilación, organización, interpretación y presentación de datos cuantitativos (números) con el objetivo de extraer conclusiones significativas y tomar decisiones informadas. Este tipo de análisis se usa ampliamente en diferentes áreas como la estadística, las ciencias sociales, la economía, la ingeniería, entre otras.

## Técnicas Comunes en el Análisis de Datos Numéricos

Algunas de las técnicas y herramientas comunes en el análisis de datos numéricos incluyen:

- **Estadística descriptiva**: Implica resumir y describir las características esenciales de los datos, como la media, la mediana, la moda, la desviación estándar, entre otros.
- **Análisis de tendencias**: Permite identificar patrones o comportamientos a lo largo del tiempo.
- **Correlación y regresión**: Se utilizan para determinar relaciones entre diferentes variables y predecir valores futuros.
- **Pruebas de hipótesis**: Evaluar si existe suficiente evidencia en los datos para aceptar o rechazar una suposición o afirmación.
- **Visualización de datos**: Involucra el uso de gráficos y diagramas (como histogramas, diagramas de dispersión, gráficos de barras) para representar los datos de manera comprensible.
- **Análisis de varianza (ANOVA)**: Se usa para comparar las medias de diferentes grupos y determinar si existen diferencias estadísticamente significativas.

A continuación se muestra el análisis básico de datos numéricos utilizando Scala en base al archivo del proyecto integrador:

### Código

```scala
import java.io.File
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import scala.math.sqrt

case class Movies(

                   budget: Int,
                   id: Int,
                   popularity: Double,
                   runtime: Option[Double],
                   vote_average: Double,
                   vote_count: Int,

                 )

object DataAnalysis extends App {
  val path2DataFile = "data/pi_movies_complete.csv"
  val dataSource = new File(path2DataFile).readCsv[List, Movies](rfc.withHeader.withCellSeparator(','))

  // Filtrar filas válidas
  val rows_ok = dataSource.collect { case Right(movie) => movie }

  //Filtrar longitud de filas correctas e incorrectas
  val len_rows_ok = rows_ok.length
  val len_rows_fail = rows_fall.length

  println(s"\nNúmero de datos correctos: ${len_rows_ok}")
  println(s"Número de datos incorrectos: ${len_rows_fail}")


  // Estadísticas básicas para una columna
  def calculoEstad[T: Int](data: Seq[T]): Unit = {
    val num = implicitly[Int[T]]
    val values = data.map(num.toDouble)
    val count = values.size
    val average = values.sum / count
    val minimo = values.min
    val maximo = values.max
    val desvEst = sqrt(values.map(x => math.pow(x - average, 2)).sum / count)

    println(f"Count: $count, average: $average%.2f, Min: $minimo, Max: $maximo, desvEst: $desvEst%.2f")
  }

  // Análisis de columnas numéricas
  println("Análisis de 'presupuesto':")
  calculoEstad(rows_ok.map(_.budget))

  println("Análisis de 'popularidad':")
  calculoEstad(rows_ok.map(_.popularity))

  println("Análisis de 'ingresos':")
  calculoEstad(rows_ok.map(_.revenue))

  println("Análisis de 'ejecución':")
  calculoEstad(rows_ok.flatMap(_.runtime)) // Filtrar None

  println("Análisis de 'promedio_votos':")
  calculoEstad(rows_ok.map(_.vote_average))

  println("Análisis de 'conteo_votos':")
  calculoEstad(rows_ok.map(_.vote_count))
}

```


## ANÁLISIS DE DATOS EN COLUMNAS DE TEXT
A continuación, presentamos el codigo a usar para el análisis del dataset ```pi_movies_complete.csv``` en dónde buscamos realizar un estudio de las columnas de tipo texto, en ésta ocación, las columnas que contienen datos de tipo texto que hemos encontrado son: ```language```,``genre```` y ```description```. Y finalmente, una parte para excluir los datos de tipo ```JSON```.

```scala
package Bim2.S9.datosS11

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import java.io.File

// Clase para representar las películas con columnas adicionales
case class Film(language: String, genre: String, description: String)

object MoviesFrequenciesApp extends App {
  // Proveer un decodificador implícito para Film
  implicit val filmDecoder: HeaderDecoder[Film] = HeaderDecoder.decoder("language", "genre", "description")(Film.apply)

  val path2DataFile2 = "C:/Users/Usuario/Downloads/pi_movies_complete.csv"

  // Configurar lectura del CSV con delimitador ';'
  val dataSource2 = new File(path2DataFile2)
    .readCsv[List, Film](rfc.withHeader.withCellSeparator(';'))

  // Filtrar filas válidas e inválidas
  val rows_ok = dataSource2.collect {
    case Right(film) => film
  }
  val rows_fall = dataSource2.collect {
    case Left(error) => error
  }

  val len_rows_ok = rows_ok.length
  val len_rows_fail = rows_fall.length

  println(s"\nNúmero de datos correctos: ${len_rows_ok}")
  println(s"Número de datos incorrectos: ${len_rows_fail}")

  // Función para analizar frecuencias de una columna de texto
  def analyzeTextColumn(columnData: List[String], columnName: String): Unit = {
    if (columnData.nonEmpty) {
      val frequency: Map[String, Int] = columnData.groupBy(identity).view.mapValues(_.size).toMap
      val total: Int = columnData.size
      val relativeFrequency: Map[String, String] = frequency.view.mapValues(count => f"${count.toDouble / total * 100}%.2f%%").toMap

      println(s"\n===== Resultados para $columnName =====")
      println(s"Frecuencia absoluta:")
      frequency.toSeq.sortBy(-_._2).foreach { case (value, count) => println(f"  $value%-15s -> $count") }
      println(s"\nFrecuencia relativa (%):")
      relativeFrequency.toSeq.sortBy(-_._2.stripSuffix("%").toDouble).foreach { case (value, relFreq) => println(f"  $value%-15s -> $relFreq") }
    } else {
      println(s"\nNo se encontraron datos en la columna $columnName. Esto podría indicar que todos los datos son inválidos o la columna está vacía.")
    }
  }

  // Analizar columnas de texto que no estén en formato JSON
  val languages = rows_ok.map(_.language).filterNot(_.trim.startsWith("{"))
  val genres = rows_ok.map(_.genre).filterNot(_.trim.startsWith("{"))
  val descriptions = rows_ok.map(_.description).filterNot(_.trim.startsWith("{"))

  analyzeTextColumn(languages, "language")
  analyzeTextColumn(genres, "genre")
  analyzeTextColumn(descriptions, "description")
}

```
Luego de la ejecución del código da cómo resultado lo siguiente:

![image](https://github.com/user-attachments/assets/c27a7ba0-3140-47ba-bd31-6ce26226e492)


## CONSULTA SOBRE LA LIBRERÍA PLAY-JSON  

**Play JSON** es una biblioteca de Scala que facilita el manejo de datos en formato JSON. Es una solución flexible y poderosa que permite serializar y deserializar objetos, así como realizar operaciones como la validación y manipulación de datos JSON. Es parte del marco de trabajo Play Framework, pero puede utilizarse de manera independiente.    

**Funcionalidades**  

1. **Conversión de JSON a clases Scala (Deserialización)**:  
   Permite transformar un objeto JSON en una clase de Scala.

2. **Conversión de clases Scala a JSON (Serialización)**:  
   Facilita la conversión de una clase de Scala a un formato JSON.

3. **Validación y Manipulación**:  
   Verifica que los datos cumplen con un formato esperado y son correctos o sino permite acceder y modificar valores específicos en una estructura JSON.
   
### PASOS A REALIZAR
- **sbt:**  
 Agregar la dependencia de Play JSON a tu proyecto en sbt (Scala Build Tool). Esto sirve para configurar el manejo de JSON en un proyecto que utiliza el marco de trabajo Play Framework en Scala.

```sbt
"com.typesafe.play" %% "play-json" % "2.9.4"
```
- **Librería:**  

### Tipos base en Play JSON

El tipo base en Play JSON es `play.api.libs.json.JsValue`, y tiene varios subtipos que representan diferentes tipos de JSON:

- **`JsObject`**: un objeto JSON, representado como un `Mapa`.  
  
- **`JsArray`**: una matriz JSON, que consta de una `Seq[JsValue]`.

- **`JsNumber`**: un número JSON, representado como `BigDecimal`.

- **`JsString`**: una cadena JSON.

- **`JsBoolean`**: un valor booleano JSON, ya sea `JsTrue` o `JsFalse`.

- **`JsNull`**: el valor JSON `null`.
  
El **`play.api.libs.json`** paquete incluye varias funciones para construir JSON desde cero, así como para convertir hacia y desde clases de caso.

#### Ejemplo de uso:  

El código utiliza la biblioteca Play JSON de Scala para trabajar con datos JSON. Define un objeto que analiza un JSON de muestra, extrae información específica de él, maneja datos opcionales y muestra cómo manejar errores durante la extracción de datos. Este ejemplo cubre conceptos clave como la manipulación de objetos y matrices JSON, el manejo de valores opcionales (Option), y la captura de errores utilizando Try.  

    
```scala
import play.api.libs.json._ // Importa la biblioteca Play JSON para manipular datos JSON.
import scala.util.{Try, Success, Failure} // Importa las clases necesarias para manejar errores (Try, Success, Failure).

// Define un objeto llamado `playjson` que hereda de `App`, permitiendo que todo el código dentro de él se ejecute directamente.
object playjson extends App {

  // Define un JSON como un objeto JsValue utilizando el método `Json.parse` para analizar el texto JSON proporcionado.
  val json: JsValue = Json.parse("""
    {
        "name" : "Watership Down",
        "location" : {
            "lat" : 51.235685,
            "long" : -1.309197
        },
        "residents" : [ {
             "name" : "Fiver",
             "age" : 4,
             "role" : null
        }, {
             "name" : "Bigwig",
             "age" : 6,
             "role" : "Owsla"
        } ]
     }
     """)

  // Convierte el objeto JSON en una cadena legible y lo imprime en la consola.
  println(Json.stringify(json))

  // Extrae el valor del campo "lat" (latitud) dentro del objeto "location" del JSON.
  val lat = (json \ "location" \ "lat").get
  println(lat) // Imprime el valor de la latitud.

  // Extrae todos los valores asociados a la clave "name" en el JSON, incluidos los de los objetos anidados.
  val names = json \\ "name"
  println(names) // Imprime todos los nombres encontrados en el JSON.

  /* 
   ¿Cuál es el tipo de dato de las variables `role0` y `role1`?
   Respuesta: Ambos son de tipo `Option[String]`, ya que pueden contener un valor (`Some`) o estar vacíos (`None`).
  */

  // Intenta extraer el campo "role" del primer residente; si no existe, devuelve `None`.
  val role0 = (json \ "residents" \ 0 \ "role").asOpt[String]

  // Intenta extraer el campo "role" del segundo residente; si no existe, devuelve `None`.
  val role1 = (json \ "residents" \ 1 \ "role").asOpt[String]

  // Define una función para convertir un valor de tipo `Option[String]` en un valor legible.
  def show(x: Option[String]) = x match {
    case Some(s) => s // Si `x` contiene un valor (`Some`), lo devuelve.
    case None => "Ninguno" // Si `x` no contiene un valor (`None`), devuelve "Ninguno".
  }

  println(show(role0)) // Imprime el valor del campo "role" del primer residente o "Ninguno" si está vacío.
  println(show(role1)) // Imprime el valor del campo "role" del segundo residente o "Ninguno" si está vacío.

  /*
    ¿Qué sucede con el programa si no uso `Try`?
    Si no uso `Try`, el programa podría arrojar una excepción en tiempo de ejecución si intento acceder a un campo inexistente.
    Con `Try`, manejo el error y puedo continuar la ejecución.
  */

  // Intenta extraer un campo inexistente "nema" del JSON usando `Try` para capturar posibles errores.
  val data = Try { (json \ "nema") }

  // Maneja el resultado del intento de extracción.
  data match {
    case Success(v) => v // Si el intento es exitoso, devuelve el valor extraído.
    case Failure(e) => println("Error") // Si ocurre un error, imprime "Error".
  }
}

```

_Dentro de la documentación del archivo README.md debe contener los siguientes puntos (algunos de estos ya fueron parte del avance anterior):_

**Documentar el proceso**

**Descripción de los datos (diccionario de datos)**

**Lectura del CSV**

**Análisis exploratorio (inferir conclusiones sobre los resultados estadísticos)**

**Limpieza**

**Trabajo con base de datos**

## TRATAMIENTO DE LA COLUMNA CREW  
La columna crew tiene una estructura de lista de objetos JSON (arreglo de objetos), donde cada objeto representa un miembro del equipo de producción.  
**Estructura detallada:**
- Tipo de dato: Array de objetos JSON.
- Formato: Lista de objetos con atributos específicos.  
**Atributos dentro de cada objeto en crew:**
  
  | **Atributo**  | **Tipo de dato** | **Descripción** |
|--------------|---------------|----------------|
| `credit_id`  | `String`       | ID único del crédito |
| `department` | `String`       | Departamento en el que trabaja la persona |
| `gender`     | `Int`          | Género (`0`: desconocido, `1`: femenino, `2`: masculino) |
| `id`         | `Int`          | ID único de la persona |
| `job`        | `String`       | Cargo dentro del departamento |


## CREACIÓN DE LA BASE DE DATOS PARA LAS PELICULAS
La base de datos para almacenar la información de las películas se llama movies_db:  
![image](https://github.com/user-attachments/assets/e8312b3c-78a0-4eee-9812-5182bcd75c27)  

![image](https://github.com/user-attachments/assets/8f82b09d-5a19-43ff-912c-7a681aec08a7)



## REFERENCIAS  
- Playframework. (s. f.). GitHub - playframework/play-json: The Play JSON library. GitHub. https://github.com/playframework/play-json
- Scala JSon - 2.8.x. (s. f.). Play Framework. https://www.playframework.com/documentation/2.8.x/ScalaJson
  


