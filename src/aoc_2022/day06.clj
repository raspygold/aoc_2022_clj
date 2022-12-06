(ns aoc-2022.day06)

(defn all-different? [chars]
  (= (count chars) (count (set chars))))

(defn indices [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(defn part1 [input]
  (->> input
       (partition 4 1)
       (indices all-different?)
       (first)
       (+ 4)))

(defn part2 [input]
  (->> input
       (partition 14 1)
       (indices all-different?)
       (first)
       (+ 14)))
