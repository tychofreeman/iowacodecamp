(ns main
  (:use com.cwfreeman.language-shootout.core
        com.cwfreeman.language-shootout.db))

(defn -main []
    (com.cwfreeman.language-shootout.db/init-db)
    (start 8999))
