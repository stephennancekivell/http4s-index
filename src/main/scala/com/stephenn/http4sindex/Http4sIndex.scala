package com.stephenn.http4sindex

import java.io.File

import cats.effect.IO
import fs2.StreamApp
import org.http4s.{HttpService, Request, StaticFile}
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeBuilder
import scala.concurrent.ExecutionContext.Implicits.global

object Http4sIndex extends StreamApp[IO] with Http4sDsl[IO] {

  def stream(args: List[String], requestShutdown: IO[Unit]) =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(service, "/")
      .serve

  val service: HttpService[IO] = {
    HttpService[IO] {
      case req @ GET -> Root =>
        serve(".", req)
      case req @ GET -> uri =>
        serve(uri.toString, req)
    }
  }

  private def serve(path: String, req: Request[IO]) = {
    val f = new File("./" + path)

    if (f.isDirectory) {
      Ok(listDir(f, path)).withContentType(`Content-Type`(MediaType.`text/html`))
    } else {
      StaticFile.fromFile(f, Some(req))
        .getOrElseF(NotFound())
    }
  }

  private def listDir(f: File, base: String): String = {
    val paths = f.list.map { path =>
      s"""
         |<li>
         | <a href="${base + "/" + path}">$path</a>
         |</li>
       """.stripMargin
    }.mkString("\n")

    s"""
       |<html>
       |<body>
       |<ul>
       |$paths
       |</ul>
       |</body>
       |</html>
     """.stripMargin
  }
}
