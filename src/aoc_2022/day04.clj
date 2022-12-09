(ns aoc-2022.day04
  (:require [clojure.string :as str]
            [clojure.set :refer [subset? superset? intersection]]))

(defn process-input [input]
  (->> (str/split-lines input)))

(defn section-to-set [section]
  (let [[start end] (str/split section #"-")
        start (Long/valueOf start)
        end (Long/valueOf end)]
    (set (range start (+ 1 end)))))

(defn sections-fully-overlap? [sections]
  (let [[a b] (str/split sections #",")
        a (section-to-set a)
        b (section-to-set b)]
    (or
     (subset? a b)
     (superset? a b))))

(defn sections-partially-overlap? [sections]
  (let [[a b] (str/split sections #",")
        a (section-to-set a)
        b (section-to-set b)]
    (> (count (intersection a b)) 0)))


(defn part1 [input]
  (->> (process-input input)
       (map sections-fully-overlap?)
       (filter identity)
       (count)))

(defn part2 [input]
  (->> (process-input input)
       (map sections-partially-overlap?)
       (filter identity)
       (count)))
