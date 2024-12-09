(ns day4
  (:require utils
            [clojure.math.combinatorics :as combo]))

(defn xmas? [grid [x y] [dx dy]]
  (every? true? (map-indexed
                 (fn [i c] (= (get grid [(+ x (* (+ i 1) dx)) (+ y (* (+ i 1) dy))] \0) c))
                 [\M \A \S])))

(def cross-mas-ds [[-1 -1] [-1 1] [1 -1] [1 1]])

(defn cross-mas? [grid [[x y] _]]
  (let [f (fn [[dx dy]] (get grid [(+ x dx) (+ y dy)] \0))
        freq (frequencies (map f cross-mas-ds))]
    (and (= (get freq \M 0) 2)
         (= (get freq \S 0) 2)
         (not= (get grid [(- x 1) (- y 1)]) (get grid [(+ x 1) (+ y 1)])))))

(defn count-xmas-at [grid [[x y] _]]
  (count (filter (partial xmas? grid [x y])
                 (combo/cartesian-product [-1 0 1] [-1 0 1]))))

(defn count-xmas [grid]
  (apply + (map (partial count-xmas-at grid)
                (filter (fn [[_ v]] (= v \X)) grid))))

(defn count-cross-mas [grid]
  (count (filter (partial cross-mas? grid)
                 (filter (fn [[_ v]] (= v \A)) grid))))

(defn parse-file [filename]
  (let [inner-map (fn [y line] (map-indexed (fn [x c] [[x y] c]) line))]
    (into {} (apply concat (map-indexed inner-map (utils/get-lines filename))))))

(defn -main [& args]
  (let [grid (parse-file (or (first args) "data/day4.txt"))]
    (println (count-xmas grid))
    (println (count-cross-mas grid))))
