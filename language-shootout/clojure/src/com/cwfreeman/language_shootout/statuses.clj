(ns com.cwfreeman.language-shootout.statuses
  (:require [clojure.contrib.json :as json]))


(defn get-statuses [req]
  (json/json-str
    [
     {:text "I am awake" :name "Bob" :date :now}
     {:text "I am asleep" :name "Bob" :date :earlier}]))
