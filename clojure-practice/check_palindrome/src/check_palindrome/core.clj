(ns check-palindrome.core
  (require [clojure.string :as s])
  (:gen-class))

(defn -main [& args]
  (println "enter a string")
  (def input (read-line))
  (println (= (s/reverse input) input)))
