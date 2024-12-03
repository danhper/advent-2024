(ns day1
  (:require utils))

(defn parse-file [filename]
  (apply mapv (comp sort vector)
         (mapv utils/parse-ints (utils/get-lines filename))))

(defn part1 [a b]
  (apply + (map (fn [x y] (abs (- x y))) a b)))

(defn part2 [a b]
  (let [freq (frequencies b)]
    (apply + (map (fn [x] (* x (get freq x 0))) a))))

(defn -main [& args]
  (let [[a b] (parse-file (or (first args) "data/day1.txt"))]
    (println (part1 a b))
    (println (part2 a b))))
