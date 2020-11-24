;; Define namespace and import packages
(ns hello-clojure.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [clj-http.client :as client]))

;; Get the Github API url from the user name
(defn github-api-for-user [user-name]
  (str "https://api.github.com/users/" user-name))

(defn -main
  "Query Github API for a user's real name"
  [& args]
  ;; Get the user name from command line arguments
  (let [user-name (first args)
        ;; Query the github API and get the response body as JSON string
        body (:body (client/get (github-api-for-user user-name)))
        ;; Parse JSON string to map
        result (json/read-str body)
        ;; Get the name member from the map
        real-name (get result "name")]
    (if (nil? real-name)
      (println "This Github user has not made their name public")
      (println "This Github user is called" real-name))))


