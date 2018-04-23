http4s-index
============

http index server built with [http4s](http://http4s.org).

http-index lists the files and folders in a directory becoming a simple light weight web server.


Usage
---
Host the current directory as a http server like below. This uses [coursier](http://get-coursier.io) to download and start http4s-index. 

```
coursier launch com.stephenn:http4s-index_2.12:0.0.1
```

Publishing
---
1) sbt release with-defaults