(ns aoc-2022.day01
  (:require [clojure.string :as str]
            [aoc-2022.utils :refer [parse-int]]))

(defn parse-elf-calories [input]
  (->> (str/split-lines input)
       (partition-by #(= "" %))
       (remove (partial = [""] ))
       (map #(map parse-int %))
       (map #(reduce + %))))

(defn part1 [input]
  (->> (parse-elf-calories input)
       (apply max)))

(defn part2 [input]
  (->> (parse-elf-calories input)
       (sort >)
       (take 3)
       (reduce +)))