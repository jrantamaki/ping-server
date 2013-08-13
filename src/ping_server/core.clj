(ns ping-server.core
    (:require  [clj-http.client :as http])
    (:gen-class))

;https://github.com/dakrone/clj-http

(defn http-get[address] 
  (try ((http/get address {:throw-exceptions false}):status)
    (catch Exception e -1)))

(defn website-up?[address]  
  (let [status (http-get address) ]
         (cond
           (>= status 500) "INTERNAL SERVER ERROR"
           (>= status 400) "CLIENT ERROR, probably not good."
           (>= status 300) "REDIRECTED, ok maybe?"
           (>= status 200) "OK"
           (== status -1) "Exception. Check the url and protocol?"
           :else "Unknown response")))


(defn get-urls[]
  '("http://www.clojure.org"
    "http://slashdot.org"
    "http://www.thedailywtf.com"
    "http://www.stackoverflow.com"
    "http://www.wikipedia.org"
    "http://www.infoq.com"
    "https://github.com/"
    "http://httpstat.us/404"
    "http://httpstat.us/401"
    "http://httpstat.us/500"
    "http://www.scala-lang.org/"
    "http://www.codinghorror.com"))

(defn ping-websites[urls]
  (doseq [site (pmap #(hash-map :url %  :status (website-up? %)) urls)]
    (prn (site :url) (site :status))))

(defn -main[& args] 
  (ping-websites args)
  (shutdown-agents)) 