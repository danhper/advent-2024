(ns day1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn parse-line [line]
  (mapv parse-long (str/split line #"\s+")))

(defn parse-file [filename]
  (with-open [reader (io/reader filename)]
    (apply mapv (comp sort vector)
           (mapv parse-line (line-seq reader)))))

(defn part1 [a b]
  (apply + (map (fn [x y] (abs (- x y))) a b)))

(defn part2 [a b]
  (let [freq (frequencies b)]
    (apply + (map (fn [x] (* x (get freq x 0))) a))))

(defn -main [& args]
  (let [[a b] (parse-file (or (first args) "data/day1.txt"))]
    (println (part1 a b))
    (println (part2 a b))))
