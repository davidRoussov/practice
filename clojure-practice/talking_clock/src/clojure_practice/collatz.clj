(ns clojure_practice.collatz)

(defn calculate-collatz [n]
    (println n)
    (if (not= n 1)
        (if (= (mod n 2) 0)
            (calculate-collatz (/ n 2))
            (calculate-collatz (+ (* 3 n) 1)))))

(defn collatz [& args]
    (println "Enter starting number:")
    (println (calculate-collatz (read-string (read-line)))))
