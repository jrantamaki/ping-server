(ns ping-server.core-test
  (:use clojure.test
        midje.sweet
        ping-server.core)
  (:require  [clj-http.client :as http]))

(facts "about http-get request"
   (fact "With correct url, will return the http status code."
     (http-get "my-correct-url") => 200
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) =>  {:status 200}))

   (fact "With wrong url, will catch exception and return -1"
     (http-get "broken-url") => -1
       (provided
         (http/get "broken-url" {:throw-exceptions false}) =throws=>  (java.net.UnknownHostException. "My test exception"))))
;;https://github.com/marick/Midje/wiki/Prerequisites-that-throw-exceptions

(facts "About converting the http status code to something more user friendly. websiteup?"
   (fact "with status 200, should return OK"
     (website-up? "my-correct-url") => "OK"
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) =>  {:status 200}))

   (fact "with status 300 , should return REDIRECTED"
     (website-up? "my-correct-url") => "REDIRECTED, ok maybe?"
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) => {:status 300}))

   (fact "with status 404 , should return OK"
     (website-up? "my-correct-url") => "CLIENT ERROR"
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) =>  {:status 404}))

   (fact "with status 503 , should return OK"
     (website-up? "my-correct-url") => "INTERNAL SERVER ERROR"
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) =>  {:status 503}))

   (fact "http-get throwing and exception"
     (website-up? "my-correct-url") => "Exception. Check the url and protocol?"
       (provided
         (http/get "my-correct-url" {:throw-exceptions false}) =>  {:status -1})))




