(defproject language-shootout/clojure "1.0-SNAPSHOT"
            :description ""
            :dependencies [
                           [org.clojure/clojure "1.2.1"]
                           [org.clojure/clojure-contrib "1.2.0"]
                           [compojure "0.6.4"]
                           [ring/ring-core "0.2.0"]
                           [ring/ring-jetty-adapter "0.2.0"]
                           ]
            :dev-dependencies [
                               [midje "1.3-alpha1"]
                               [lein-midje "[1.0.0,)"]
                               [ring/ring-devel "[0.2.0]"]
                               ]
)
