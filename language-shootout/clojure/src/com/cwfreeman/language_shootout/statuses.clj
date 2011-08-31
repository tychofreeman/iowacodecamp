(ns com.cwfreeman.language-shootout.statuses
  )

(def statuses
  [{:id 1 :text "I am awake" :name "Bob" :date :now}
   {:id 2 :text "I am asleep" :name "Bob" :date :earlier}])

(def replies
  [{:text "Me too!!" :name "Sam" :date :now :status 1}
   {:text "Me too!!" :name "Sam" :date :now :status 2}])

(defn get-statuses [req]
  statuses)

(defn get-replies [id]
      (filter #(= (:status %1) id) replies))

(defn get-status [id]
    {:status (filter #(= (:id %1) id) statuses)
     :replies [(get-replies id)]})