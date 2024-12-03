(ns day3
  (:require utils))

(def regexp #"(?:mul\((\d+),(\d+)\)|do\(\)|don't\(\))")

(defn compute-mul [match]
  (apply * (mapv parse-long (drop 1 match))))

(defn process-match [no-skip [state value] match]
  (case (first match)
    "don't()" [false value]
    "do()" [true value]
    [state (if (or no-skip state) (+ value (compute-mul match)) value)]))

(defn run [input no-skip]
  (let [matches (re-seq regexp input)]
    (second (reduce (partial process-match no-skip) [true 0] matches))))

(defn -main [& args]
  (let [input (slurp (or (first args) "data/day3.txt"))]
    (println (run input true))
    (println (run input false))))
