(ns com.cwfreeman.language-shootout.db
  (:require [clojure.contrib.sql :as sql]))

 (def db-conn
   {
    :classname   "org.h2.Driver"
    :subprotocol "h2:mem"
    :subname     "language-shootout;DB_CLOSE_DELAY=-1"
    :user        "sa"
    :password    ""
   })

  (defn params-to-vec [query params]
          (vec (flatten (filter #(not (nil? %)) [[query] params]))))

(defn find-user [username]
  (sql/with-connection db-conn
    (sql/with-query-results res (params-to-vec "SELECT roles.name as role from users inner join userroles as ur on ur.userid = users.id inner join roles on ur.roleid = roles.id where users.name = ?" username)
      {:name username :roles (set (map #(:role %) res))})
  ))


  (defn init-db []
    (sql/with-connection db-conn
      (sql/create-table :users [:id "IDENTITY"] [:name "varchar"] [:password "varchar"])
      (sql/create-table :roles [:id "IDENTITY"] [:name "varchar"])
      (sql/create-table :userroles [:userid "integer references users(id)"] [:roleid "integer references roles(id)"])
      (sql/insert-rows :roles [1 "admin"] [2 "any"])
      (sql/insert-rows :users [1 :bob "admin"] [2 :amy "any"])
      (sql/insert-rows :userroles [1 1] [1 2] [2 1] [2 2])
      (println "Done with init-db -- looking for Bob on connection " (sql/find-connection))
      (println (find-user :bob))
      ))


;; --- Use sql/do-commands or do-prepared or something. with-connection will create a new connection