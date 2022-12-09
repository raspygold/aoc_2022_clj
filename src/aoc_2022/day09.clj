(ns aoc-2022.day09
  (:require [clojure.string :as str]
            [aoc-2022.utils :refer [parse-int]]))

(defn process-input [input]
  (map
   #(str/split % #" ")
   (str/split-lines input)))

;; Expand a movement into it's individual movement steps
(defn expand-movement [[dir n]]
  (let [n (parse-int n)
        movement (case dir
                   "U" [(- 1) 0]
                   "R" [0 1]
                   "D" [1 0]
                   "L" [0 (- 1)])]
    (repeat n movement)))

;; Move the head by one step
(defn move-head [[hy hx] [my mx]]
  [(+ hy my) (+ hx mx)])

;; Move the second knot to keep it in step with the first
(defn move-knot [knot-pair]
  (let [[hy hx] (first knot-pair)
        [ty tx] (last knot-pair)
        dy (- hy ty)
        dx (- hx tx)]
    (if
     (and (= 0 dy) (< 1 (abs dx)))
      [ty (+ tx (int (/ dx 2)))]
      (if
       (and (= 0 dx) (< 1 (abs dy)))
        [(+ ty (int (/ dy 2))) tx]
        (if
         (and (< 1 (abs dy)) (< 1 (abs dx)))
          [(+ ty (int (/ dy 2))) (+ tx (int (/ dx 2)))]
          (if
           (or (< 1 (abs dy)) (< 1 (abs dx)))
            (if (< (abs dy) (abs dx))
              [(+ ty dy) (+ tx (int (/ dx 2)))]
              [(+ ty (int (/ dy 2))) (+ tx dx)])
            [ty tx]))))))

(defn move-step-1 [[[hy hx] [ty tx] t-visited] [my mx]]
  (let [[hy hx] (move-head [hy hx] [my mx])
        [ty tx] (move-knot [[hy hx] [ty tx]])]
    [[hy hx] [ty tx] (conj t-visited [ty tx])]))

;; Moves the head [hy hx] by the movements [[my mx]] and keeps the tail in step [ty tx]
(defn move-1 [[hy hx] [ty tx] t-visited move]
  (let [movement-steps (expand-movement move)]
    (reduce
     move-step-1
     [[hy hx] [ty tx] t-visited]
     movement-steps)))

(defn part1 [input]
  (let [h [0 0]
        t [0 0]
        movements (process-input input)
        [_final-h _final-t t-visited] (reduce
                                       (fn [[[hy hx] [ty tx] t-visited] movement]
                                         (move-1 [hy hx] [ty tx] t-visited movement))
                                       [h t #{}]
                                       movements)]
    (count t-visited)))

;; Start of part 2

(defn move-step-2 [[knots t-visited] [my mx]]
  (let [head (move-head (first knots) [my mx])
        moved-knots (reduce (fn [prev-moved-knots follower]
                              (conj prev-moved-knots (move-knot [(last prev-moved-knots) follower])))
                            [head]
                            (rest knots))]

    [moved-knots (conj t-visited (last moved-knots))]))

;; Moves the first knot by the movements and keeps the following knots in step
(defn move-2 [knots t-visited move]
  (let [movement-steps (expand-movement move)]
    (reduce
     move-step-2
     [knots t-visited]
     movement-steps)))


(defn part2 [input]
  (let [knots (repeat 10 [0 0])
        movements (process-input input)
        [_final-knots t-visited] (reduce
                                  (fn [[knots t-visited] movement]
                                    (move-2 knots t-visited movement))
                                  [knots #{}]
                                  movements)]
    (count t-visited)))
