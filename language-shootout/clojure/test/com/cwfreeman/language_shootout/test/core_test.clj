(ns com.cwfreeman.language-shootout.test.core-test
    (:use clojure.test)
    (:use com.cwfreeman.language-shootout.core)
    (:use midje.sweet)
)

(fact (+ 1 1) => 5)
