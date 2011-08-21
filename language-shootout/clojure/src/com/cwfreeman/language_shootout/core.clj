(ns com.cwfreeman.language-shootout.core
  (:use
       ; [ring.util.response :only (file-response resource-response)]
        [compojure.core :only (defroutes GET)])
  (:require
            [compojure.route :as route]
;            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            )
)


(defn get-statuses [req]
  (.toString
    [req 
     {:text "I am awake" :name "Bob" :date :now}
     {:text "I am asleep" :name "Bob" :date :earlier}]))


(defroutes routes 
           (GET "/" [] get-statuses)
           (route/resources "/")
)

; (def application (handler/site routes))


(defn start [port]
    (ring/run-jetty routes {:port (or port 80) :join? false}))
