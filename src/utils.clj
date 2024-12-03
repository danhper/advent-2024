(ns utils
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn get-lines [filename]
  (with-open [reader (io/reader filename)]
    (mapv str (line-seq reader))))

(defn parse-ints [line]
  (map parse-long (str/split line #"\s+")))
