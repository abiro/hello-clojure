(ns hello-clojure.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [clj-http.client :as client]))

(def api-url "https://api.github.com/users/abiro")

(defn -main
  "Query an API and get a value from the JSON"
  [& args]
  (let [body (:body (client/get api-url))
        result (json/read-str body)]
    (println "This Github user is called" (get result "name"))))


