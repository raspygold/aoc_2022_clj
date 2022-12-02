(ns aoc-2022.day02
  (:require [clojure.string :as str]))

(defn process-input [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))))

(defn part1-score-pair [pair]
  (let [oponent (first pair)
        me (second pair)
        beats-me (+ me 1)
        beats-me (if (> beats-me 3) 1 beats-me)
        result (if (= oponent me) 3
                   (if (= beats-me oponent) 0 6))]
    (+ result me)))

(defn part1 [input]
  (->> (process-input input)
       (map #(replace {"A" 1 "X" 1 "B" 2 "Y" 2 "C" 3 "Z" 3} %))
       (map part1-score-pair)
       (reduce +)))

(defn part2-score-round [round]
  (let [my-move (reduce + round)
        my-move (if (> my-move 3) 1 (if (< my-move 1) 3 my-move))
        result (second round)
        result-score (get {-1 0 0 3 1 6} result)]
    (+ result-score my-move)))

(defn part2 [input]
  (->> (process-input input)
       (map #(replace {"A" 1 "X" -1 "B" 2 "Y" 0 "C" 3 "Z" 1} %))
       (map part2-score-round)
       (reduce +)))
