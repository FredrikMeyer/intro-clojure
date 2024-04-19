^{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(ns intro03
  (:require
   [clojure.java.io :as io]
   [nextjournal.clerk :as clerk])
  (:import
   javax.imageio.ImageIO
   java.awt.image.BufferedImage
   java.awt.RenderingHints
   java.awt.Graphics2D))

;; # Generativ kunst

;; > “art programmed using a computer that intentionally introduces randomness as part of its creation process”

;; Vi skal prøve å gjenskape noe jeg lagde for lenge siden (får se litt Java interop i samme slengen):

(let [img (ImageIO/read (io/as-file "sphere_eksempel.jpg"))
      new-size 400
      resized (new BufferedImage new-size new-size BufferedImage/TRANSLUCENT)]
  (doto (.createGraphics resized)
    (.setRenderingHint RenderingHints/KEY_INTERPOLATION RenderingHints/VALUE_INTERPOLATION_BILINEAR)
    (.drawImage img 0 0 new-size new-size nil)
    (.dispose))
  resized)

;; ## Sketsj

;; Live, se `sketch.clj`.

;; Strategien er følgende:
;;  - Tegn streker på innsiden av en sirkel
;;  - Gjenta mange ganger.
;;  - Effekten fås ved å ha lav alpha.

;; Her er mange variasjoner av samme algoritme.

(let [images (->> "../quil-drawings/"
                  clojure.java.io/file
                  file-seq
                  (filter (fn [f] (re-matches #"s[0-9]+.+" (.getName f))))
                  (map #(ImageIO/read %))
                  (partition 3))]
  (apply clerk/row images))

;; ## Kode fra `sketch.clj`

(clerk/code (slurp "sketch.clj"))
