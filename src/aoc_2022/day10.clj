(ns aoc-2022.day10
  (:require [clojure.string :as str]
            [aoc-2022.utils :refer [parse-int]]))

(defn process-input [input]
  (map
   #(str/split % #" ")
   (str/split-lines input)))

(defn process-instruction [[cycle-num x-register x-register-values] instruction]
  (let [[cmd v] instruction
        v (if v (parse-int v) v)
        addx? (= "addx" cmd)]

    [(if addx? (+ 2 cycle-num) (inc cycle-num))
     (if addx? (+ x-register v) x-register)
     (if
      addx?
       (assoc x-register-values cycle-num x-register (inc cycle-num) x-register)
       (assoc x-register-values cycle-num x-register))]))

(defn execute [input]
  (let [instructions (process-input input)]
    (reduce
     process-instruction
     [1 1 (sorted-map)]
     instructions)))

(defn part1 [input]
  (let [[_final-cycle-num
         _final-x-register
         x-register-values] (execute input)
        cycles-signals-to-add [20 60 100 140 180 220]
        selected-cycles (select-keys x-register-values cycles-signals-to-add)
        selected-cycles-with-signals (into {} (for [[k v] selected-cycles] [k (* k v)]))]

    (reduce + 0 (vals selected-cycles-with-signals))))

(def lit "#")
(def dark ".")

(defn generate-line [pixel-x-register-values]
  (map
   (fn [pixel]
     (let [x-val (nth pixel-x-register-values pixel)]
       (if (and
            (>= pixel (dec x-val))
            (<= pixel (inc x-val)))
         lit
         dark)))
   (range 0 40)))

(defn part2 [input]
  (let [[_final-cycle-num
         _final-x-register
         x-register-values] (execute input)
        pixel-x-register-value-rows (partition 40 40 (vals x-register-values))
        image (map
               (fn [pixel-x-register-values]
                 (generate-line pixel-x-register-values))
               pixel-x-register-value-rows)]

    (println)
    (println (str/join (nth image 0)))
    (println (str/join (nth image 1)))
    (println (str/join (nth image 2)))
    (println (str/join (nth image 3)))
    (println (str/join (nth image 4)))
    (println (str/join (nth image 5)))))
