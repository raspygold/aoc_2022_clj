(ns aoc-2022.day03
  (:require [clojure.string :as str]
            [clojure.set :refer [intersection]]
            [aoc-2022.utils :refer [char-range]]))

(def item-priorities
  (let [items (concat (char-range \a \z) (char-range \A \Z))]
    (into (sorted-map) (map vector items (range 1 53)))))

(defn process-input [input]
  (->> (str/split-lines input)))

(defn prioritize [items]
  (map #(get item-priorities %) items))

(defn divide-compartments [line]
  (let [line-contents (char-array line)
        rucksack-size (/ (count line) 2)
        rucksacks (partition rucksack-size line-contents)]
    rucksacks))

(defn part1-find-common-items [rucksacks]
  (let [first-rucksack-items-set (set (first rucksacks))
        second-rucksack-items-set (set (second rucksacks))
        common-items (intersection first-rucksack-items-set second-rucksack-items-set)]
    common-items))

(defn part1 [input]
  (->> (process-input input)
       (map divide-compartments)
       (map part1-find-common-items)
       (map prioritize)
       (flatten)
       (reduce + 0 )))

(defn part2-find-common-items [rucksacks]
  (let [[a b c] rucksacks
        a (set (char-array a))
        b (set (char-array b))
        c (set (char-array c))]
    (->> (intersection a b)
         (intersection c))))

(defn part2 [input]
  (let [processed-input (process-input input)
        rucksack-groups (partition 3 processed-input)]
    (->> rucksack-groups
         (map part2-find-common-items)
         (map prioritize)
         (flatten)
         (reduce + 0))))
