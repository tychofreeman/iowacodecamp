(ns com.cwfreeman.language-shootout.db
  (:require [clojure.contrib.sql :as sql]))

 (def db-conn
   {
    :classname   "org.h2.Driver"
    :subprotocol "h2:mem"
    :subname     "language_shootout"
    :user        "sa"
    :password    ""
   })

; And this is how to connect to it.
; (sql/with-connection db-conn (sql/with-query-results res ["select * from db"])))