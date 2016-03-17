# Seqexec

This a possible project build structure for a standalone Seqexec application. This work comes from the idea of how a build could look like starting from scratch with the following goals:

- Attempt a tree-like structure for the build
- Remove the need for OSGi support
- Use mostly managed dependencies
- Support for web application using scala.js
- Support for common shared bundles

There are probably many improvements to this build and the whole issue of deploying is not presented, but this is a start.

## Structure
The project is structured with a root project containing 2 subtrees. One for common shared bundles and one for the web application. The latter has a further division containing the server side, the client side compiled with scala.js and a shared bundle.

Note that the code is functional and produces the Seqexec web demo showed during early 2016

```
root
 |
 +-- common
 |    |
 |    +-- edu.gemini.seqexec.server
 |
 +-- seqexec-web
      |
      +-- server
      +-- client
      +-- shared
```

`seqexec-web/client` is compiled via `scala.js` and `seqexec-web/shared` is compiled to both `scala.js` and to the JVM

## Versions

Versions are defined in the file `project/Settings.scala`

## Dependencies

All dependencies are managed coming from either maven central or the Gemini repository

Internall, `seqexec-web/server` depends on `common/edu.gemini.seqexec.server`. The dependency is declared on `seqexec-web/build.sbt` but other ways to declare intra-module dependencies could be devised.

## Running
On `seqexec-web/server` there is a standalone application that will launch a web server and the Seqexec server. All the static files and generated java script are served from the application

To launch the app you can do:

```
sbt
project seqexec_web_server
~re-start
```

Note that as part of this run command the scala.js application will be compiled to javascript. Modifying any file on server or client will make a restart of the server

## Testing

Sample tests using `scalatest` and `scalacheck` are included in each module. You can run `test` at the root level or per sub-project

IDEA doesn't support running tests on scala.js. See this [issue](https://github.com/scalatest/scalatest/issues/743)

## IDEA

IDEA can open directly sbt. Go to 

```
File -> New -> Project from Existing Sources...
```

And open the root build.sbt. IDEA shows some warnings but the final result works just fine.
