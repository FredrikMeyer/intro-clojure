^{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(ns intro02
  (:require
   [nextjournal.clerk :as clerk]))

;; # Kult i Clojure

;; ## Offisielle selling points

;; - **Dynamisk utvikling**
;;
;;   Litt som i Python - alltid et REPL åpent for å evaluere funksjoner, teste uttrykk, slå opp
;;   dokumentasjon, osv.
;; - **Funksjonell programming**
;;    * "First class"-funksjoner
;;    * Ikke-muterbare datastrukturer
;; - **Lisp**
;;
;;    Som matematiker synes jeg Lisper er veldig vakre 🧑‍🎓
;; - **Kjøretidspolymorfi**
;;
;;   Vil ikke si noe om dette, men Clojure mener å ha "løst _the expression problem_" (hvordan enkelt legge til nye datatyper). Se f.eks [denne bloggposten](https://eli.thegreenplace.net/2016/the-expression-problem-and-its-solutions/).
;;
;;   Litt da: Man kan lage egne type med `deftype` og `defrecord`, og også multi-metoder med `defmethod`. Men mer om det neste gang kanskje!?
;; - **Samtidig (_concurrent_) programmering**
;; - **På JVM - god Java-interop**.

;; ## Interaktiv utvikling

;; Noen andre språk har lignende muligheter, for eksempel Python:

;; ```shell
;; > python
;; Python 3.12.1 (main, Jan  6 2024, 14:18:24) [Clang 15.0.0 (clang-1500.1.0.2.5)] on darwin
;; Type "help", "copyright", "credits" or "license" for more information.
;; >>> def f(x):
;; ...   return x + 2
;; ...
;; >>> f(1)
;; 3
;; ```

;; Men i Clojure (og andre Lisp-språk) er det populært med "REPL-driven development":
;; - Definer en funksjon, og evaluer i editoren (C-M-x i Emacs)

^{:nextjournal.clerk/visibility {:result :hide}}
(defn my-fib-1 [n]
  2)

;;  - Test den i REPL (hopp til REPL ved C-c C-z i Emacs).
;;    Oj, den returnerer feil, la oss fikse:

^{:nextjournal.clerk/visibility {:result :hide}}
#_{:clj-kondo/ignore [:redefined-var]}
(defn my-fib-1
  ;; Usually bad practice to redefine function like this
  [n]
  (cond (< n 3)
        n
        :else
        (+ (my-fib-1 (dec (dec n))) (my-fib-1 (dec n)))))

;;  - Test i REPL... "Oj, den funker! Nice. Fortsett."

;; ## Lisp - "code is data"

;; Man kan "enkelt" utvide språket med makroer (jeg har aldri gjort det!).
;;
;; Eksempel:

(clerk/code (macroexpand-1 '(and 1337 4096)))

;; ## Debugging i Emacs

;; Evaluer funksjon med C-u C-M-x, og kjør.
;; Live-demo (vanskelig i en notebook 🙂)
