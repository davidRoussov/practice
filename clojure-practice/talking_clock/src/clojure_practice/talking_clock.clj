(ns clojure_practice.talking_clock
  (:use [clojure.string :only [split join]]))

(def words {
  "00" ""
  "0" "twelve"
  "1" "one"
  "2" "two"
  "3" "three"
  "4" "four"
  "5" "five"
  "6" "six"
  "7" "seven"
  "8" "eight"
  "9" "nine"
  "10" "ten"
  "11" "eleven"
  "12" "twelve"
  "13" "thirteen"
  "14" "fourteen"
  "15" "fifteen"
  "16" "sixteen"
  "17" "seventeen"
  "18" "eighteen"
  "19" "nineteen"
  "20" "twenty"
  "30" "thirty"
  "40" "forty"
  "50" "fifty"})

(defn generate-word-minute [minutes]
  (if (contains? words minutes)
      (get words minutes)
      (do (def split-minutes (split minutes #""))
          (def second-part (get words (get split-minutes 1)))
          (if (= (get split-minutes 0) "0")
              (str " oh " second-part)
              (do (def first-part (get words (str (get split-minutes 0) "0")))
                  (str " " first-part " " second-part))))))

(defn talky [time] 
  (def hours-minutes (split time #":"))
  (def word-hour (get words (str (mod (read-string (get hours-minutes 0)) 12))))
  (def word-minute (generate-word-minute (get hours-minutes 1)))
  (def am-pm 
    (if (< (read-string (get hours-minutes 0)) 12)
        (str " am")
        (str " pm")))
  (str "It's " word-hour word-minute am-pm))

(defn talking_clock [args]
  (println "Enter the time (HH:MM):")
  (println (talky(read-line))))

; https://www.reddit.com/r/dailyprogrammer/comments/6jr76h/20170627_challenge_321_easy_talking_clock/