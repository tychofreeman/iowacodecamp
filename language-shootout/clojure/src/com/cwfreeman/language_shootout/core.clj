(ns com.cwfreeman.language-shootout.core
  (:use
        [com.cwfreeman.language-shootout.statuses]
        [compojure.core :only (defroutes GET)])
  (:require
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [sandbar.core :as sandbar]
            [sandbar.stateful-session :as ss]
            [clojure.contrib.json :as json]
            )
)


(def four-oh-four "<html><head><title>404</title><head><body>You can't go there</body></html>")

(defroutes routes 
   (GET "/statuses" [] (json/json-str (get-statuses)))
   (GET "/replies/status=:id" [id] (json/json-str (get-replies (Integer/parseInt id))))
   (GET "/status/:id" [id] (json/json-str (get-status (Integer/parseInt id))))
   (route/resources "/")
   (route/not-found four-oh-four)
)

(def app (-> routes
           ss/wrap-stateful-session))

(defn start [port]
    (ring/run-jetty app {:port (or port 80) :join? false})
  (println "All done"))
