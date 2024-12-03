(ns day2
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-file [filename]
  (with-open [reader (io/reader filename)]
    (mapv (fn [v] (map parse-long (str/split v #"\s+"))) (line-seq reader))))

(defn safe? [v]
  (let [pairs (map vector v (drop 1 v))
        ascending (apply < (first pairs))]
    (every? (fn [[a b]] (and (= (< a b) ascending) (<= 1 (abs (- a b)) 3))) pairs)))

(defn almost-safe? [v]
  (some (fn [i] (safe? (concat (take i v) (drop (+ i 1) v)))) (range (count v))))

(defn -main [& args]
  (let [lines (parse-file (or (first args) "data/day2.txt"))]
    (println (count (filter safe? lines)))
    (println (count (filter almost-safe? lines)))))
