(ns sketch
  (:require
   [quil.core :as q]
   [quil.middleware :as m]))

(def w 600)
(def h 600)

(defn setup []
  ;; Color-mode hue saturation brightness
  (q/color-mode :hsb 100 100 100 100)
  (q/background 0)
  ;; Viktig: 20 betyr 20% alpha
  (q/stroke 100 20)
  (q/stroke-weight 1)
  (q/no-fill)
  {:radius (- (/ w 2) 100)
   :n 1000 ;; 20000 på stort bilde
   :left-center 0
   :right-center (/ q/PI 3)  ;; pi/4 1
   })

(defn rgauss
  "Sample from a Gaussian distribution with given mean and std."
  [mean std]
  (+ mean (* std (q/random-gaussian))))

(defn random-point-on-circle [x y radius mean]
    (let [theta (rgauss mean (* 0.35 q/PI))]  ;; 0.3
      [(+ x (* radius (Math/cos theta)))
       (+ y (* radius (Math/sin theta)))]))

(defn random-line-on-circle [x y radius mean]
    (let [[a b] (random-point-on-circle x y radius mean)
          [c d] (random-point-on-circle x y radius mean)]
      (q/line a b c d)))

(defn draw [state]
  (q/background 0 10)
  ;; TODO draw here
  ;; Skriv funksjoner random-point-on-circle, random-line-on-circle
  (doseq [_ (range 10000)]
    (random-line-on-circle 300 300 250 0))
  (q/no-loop)
  )

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn sketch []
  #_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
  (q/defsketch quil-drawings
    :title "You spin my circle right round"
    :size [w h]
    :setup setup
    :update identity
    :draw draw
    :features [:keep-on-top :no-bind-output :pause-on-error]
    :middleware [m/fun-mode m/pause-on-error]))

;; Nyttige funksjoner


(comment
  (defn random-point-on-circle [x y radius mean]
    (let [theta (rgauss mean (* 0.35 q/PI))]  ;; 0.3
      [(+ x (* radius (Math/cos theta)))
       (+ y (* radius (Math/sin theta)))]))

  (defn random-line-on-circle [x y radius mean]
    (let [[a b] (random-point-on-circle x y radius mean)
          [c d] (random-point-on-circle x y radius mean)]
      (q/line a b c d)))

  (defn draw-state [state]
    (q/background 0)
    (let [cx (* 0.5 (q/width))
          cy (* 0.5 (q/height))
          radius (:radius state)]
      (dotimes [_ (:n state)]
        (random-line-on-circle cx cy radius (:left-center state))
        (random-line-on-circle cx cy radius (:right-center state))))
    (println "Done")
    (q/no-loop)))

