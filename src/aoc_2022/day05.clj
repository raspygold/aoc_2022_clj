(ns aoc-2022.day05
  (:require [clojure.string :as str]))

(defn process-input [input]
  (->> (str/split-lines input)
       (remove #(not (str/starts-with? % "move")))))

(defn execute-individual-stack-move [stacks, [count, from, to]]
  (let [count (Long/valueOf count)
        crates-to-move (take-last count (get stacks from))
        updated-to (concat (get stacks to) (reverse crates-to-move))
        updated-from (drop-last count (get stacks from))]
    (reduce
     #(update-in %1 [(first %2)] (fn [_] (last %2)))
     stacks {to updated-to from updated-from})))

(defn execute-bulk-stack-move [stacks, [count, from, to]]
  (let [count (Long/valueOf count)
        crates-to-move (take-last count (get stacks from))
        updated-to (concat (get stacks to) crates-to-move)
        updated-from (drop-last count (get stacks from))]
    (reduce
     #(update-in %1 [(first %2)] (fn [_] (last %2)))
     stacks {to updated-to from updated-from})))

(defn part1 [input initial-stacks]
  (->> (process-input input)
       (map #(re-seq #"\d+" %))
       (reduce execute-individual-stack-move (into (sorted-map) initial-stacks))
       (map (fn [[_k v]] (last v)))
       (str/join)))

(defn part2 [input initial-stacks]
  (->> (process-input input)
       (map #(re-seq #"\d+" %))
       (reduce execute-bulk-stack-move (into (sorted-map) initial-stacks))
       (map (fn [[_k v]] (last v)))
       (str/join)))
