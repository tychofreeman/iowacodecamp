(ns com.cwfreeman.language-shootout.statuses
  )


(def curr-id (atom 0))
(defn next-id []
  (swap! curr-id + 1))

(defn status [user msg]
  {:id (next-id) :text msg :name user :date :now})


(def statuses
  (atom [
          (status "Bob" "I am awake")
          (status "Bob" "I am asleep")
          ]))

(def replies
  [{:text "Me too!!" :name "Sam" :date :now :status 1}
   {:text "Me too!!" :name "Sam" :date :now :status 2}])

(defn get-statuses [req]
  @statuses)

(defn get-replies [id]
      (filter #(= (:status %1) id) replies))

(defn get-status [id]
    {:status (filter #(= (:id %1) id) @statuses)
     :replies [(get-replies id)]})

(defn add-status [p]
  (let [s (status "Bob" (get p "statusText"))]
  (reset! statuses (conj @statuses s))
  (println @statuses)
  s))