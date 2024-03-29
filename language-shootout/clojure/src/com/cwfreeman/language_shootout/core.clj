(ns com.cwfreeman.language-shootout.core
  (:use
        [com.cwfreeman.language-shootout.statuses]
        [com.cwfreeman.language-shootout.db]
        [compojure.core :only (defroutes GET PUT POST)]
)
  (:require
        [compojure.route :as route]
        [ring.adapter.jetty :as ring]
        [ring.middleware.params :as params]
        [clojure.contrib.json :as json]
        [hiccup.core :as hiccup]
        [sandbar.auth :as auth]
        [sandbar.core :as sandbar]
        [sandbar.form-authentication :as fauth]
        [sandbar.stateful-session :as ss]
        [sandbar.validation]
        )
)

(def security-policy
  [
    #"/login" #{:any}
    #"/statuses" #{:any}
    #".*" #{:admin}
    ])

; this can be expanded to do whatever you want. :name and :roles are required, though.
(defn auth-fn [request]
  (println "auth-fn... for " request)
  (find-user [auth/current-username]))


(defn four-oh-four [req]
  (str "404 - You can't go there - " req " duh"))

(def login-screen
  (str  "<html>"
          "<head><title>Login</title>"
          "<body>"
            "<form method='post'>"
              "<label>Username</label><input type='text' name='username'>"
              "<br/>"
              "<label>Password</label><input type='password' name='password'>"
              "<input type='submit' name='login'></input>"
            "</form>"
          "</body>"
        "</head>"
    ))

(defn am-i-logged-in [p]
  "<html><body>Logged In!!</body></html>")

(defroutes routes
  (GET "/login" [] login-screen)
  (POST "/login" {params :params} (am-i-logged-in params))
  (GET "statuses" [] (json/json-str (get-statuses)))
  (GET "/replies/status=:id" [id] (json/json-str (get-replies (Integer/parseInt id))))
  (POST "/status" {params :params} (json/json-str (add-status params)))
  (GET "/status/:id" [id] (json/json-str (get-status (Integer/parseInt id))))
  (route/files "/")
  (GET "*" req (four-oh-four req))
  (POST "*" req (four-oh-four req))
  (route/not-found (four-oh-four "not found"))
)


(def app (-> routes
           (auth/with-security security-policy auth-fn) ; this MUST precede wrap-stateful-session
           params/wrap-params
           ss/wrap-stateful-session
           ))

(defn start [port]
    (println "Starting jetty...")
    (ring/run-jetty app {:port (or port 80) :join? false}))
