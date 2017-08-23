(ns clojure_practice.fibonacci)

(defn fib1 [n]
  (if (= n 1)
    0
    (if (= n 2)
      1
      (+ (fib1 (- n 1)) (fib1 (- n 2))))))

(def fib2
  ((fn rfib [a b]
    (lazy-seq (cons a (rfib b (+ a b)))))
  0 1))

(defn fibonacci [& args]
  (println "Enter desired length of sequence (q to quit):")
  (def response (read-line))
  (if (not= response "q")
    (do (println (fib1 (read-string response)))
        (fibonacci []))))

;0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, ...