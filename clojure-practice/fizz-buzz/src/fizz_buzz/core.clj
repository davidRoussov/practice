(ns fizz-buzz.core
  (:gen-class))

(defn fizzbuzz [n]
  (if (and (= (mod n 3) 0) (= (mod n 5) 0))
    (println "FizzBuzz")
    (if (= (mod n 5) 0)
      (println "Buzz")
      (if (= (mod n 3) 0)
        (println "Fizz")
        (println n)))))

(defn -main [& args] 
  (dorun (map fizzbuzz (range 1 101))))
