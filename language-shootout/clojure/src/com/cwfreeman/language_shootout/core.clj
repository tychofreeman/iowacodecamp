(ns com.cwfreeman.language-shootout.core
  (:use
        [com.cwfreeman.language-shootout.statuses]
        [compojure.core :only (defroutes GET)])
  (:require
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            )
)


(def four-oh-four "<html><head><title>404</title><head><body>You can't go there</body></html>")

(defroutes routes 
           (GET "/" [] get-statuses)
           (route/resources "/")
           (route/not-found four-oh-four)
)

(defn start [port]
    (ring/run-jetty routes {:port (or port 80) :join? false}))
