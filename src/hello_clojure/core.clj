(ns hello-clojure.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [clj-http.client :as client]))

(defn github-api-for-user [user-name]
  (str "https://api.github.com/users/" user-name))

(defn -main
  "Query Github API for a user's real name"
  [& args]
  (let [user-name (first args)
        body (:body (client/get (github-api-for-user user-name)))
        result (json/read-str body)
        real-name (get result "name")]
    (if (nil? real-name)
      (println "This Github user has not made their name public")
      (println "This Github user is called" real-name))))


