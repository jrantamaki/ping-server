(defproject ping-server "0.1.0-SNAPSHOT"
  :description "Small hack to ping/check if site is up."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [midje "1.5.1"]
                 [clj-http "0.7.6"]]
  :main ping-server.core)
