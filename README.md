# ping-server

A Clojure hack to ping servers or web sites to check if they are up and running.

## Usage

In REPL:

	(ping-websites '("http://www.github.com"))


Package with Leiningen

	lein uberjar

And then run with:

	java -jar target/ping-server-0.1.0-SNAPSHOT-standalone.jar http://www.clojure.org http://slashdot.org

Or with Leiningen:

	lein run  http://www.clojure.org http://slashdot.org

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
