;; # Clojure

;; Clojure er et funksjonelt språk i Lisp-familien som fokuserer
;; på ikke-muterbare datastrukturerer.

^{:nextjournal.clerk/visibility {:result :hide :code :hide}}
(ns intro01
  (:require
   [nextjournal.clerk :as clerk]))

;; ## Super-duper kort intro til Clojure

;;  Som i andre Lisper er operator først:

(+ 1 2)

;; Og folk liker å vitse om at det er så mange parenteser.

;; Bygd på Java, kan inspisere klasser:

(clerk/table (for [r '((class 1)
                       (class [1 2 3])
                       (class '(1 2 3))
                       (class #{1 2 3}))]
               [r (eval r)]))

;; Bygd på _persistente_ datastrukturer. Alt er immutable, men lite kopering skjer.

;; ###  Noen datastrukturerer

;; **Lister**

(let [my-list [1 2 3 4]]
  (reduce (fn [acc curr] (+ acc curr)) my-list))

;; **Maps**

(let [my-fridge {:items [{:type :butter
                          :name "Tine Something"
                          :old? true}
                         {:type :milk
                          :name "Q-Melk"
                          :old? false}]}]
  (-> my-fridge
      (get :items)
      (first)
      (get :name)))

;; Man oppfordres til å jobbe med _generiske_ datastrukturerer i stedet for å lage nye spesial-objekter. Man får gratis
;; mange funksjoner for å redigere og aksessere dataen.

;; For å sitere [Stuart Halloway](https://www.youtube.com/watch?v=bvI1BNgGp0k&t=1625s):
;; > when you create a Java class how many clients in the world can access that class' API... zero!
;; >
;; > [...] you just wrote a private language for accessing data, that nobody has ever seen...

;; ### Funksjoner og closure

;; Funksjoner defineres slik:

(defn point-on-circle [r a]
  (let [x (* r (Math/cos a))
        y (* r (Math/sin a))]
    [x y]))

;; Tar litt tid å venne seg til alle parentesene, men etterhvert innser man at notasjonen er
;; veldig enkel.

;; Evaluer slik:

(point-on-circle 2 (* Math/PI 0.33))

;; Closure oppfører seg likt som i andre Scheme-språk 😉
^{::clerk/visibility {:result :hide}}
(defn state-holder []
  (let [state (atom {:count 0})]
    (fn []
      (swap! state update :count inc))))

;; Hver gang vi kaller `state-holder`, økes telleren med èn.

(let [f (state-holder)]
  (f)
  (f)
  (f))

;; ## Java-interop
;; Alle biblioteker fra Java kan brukes.

^{::clerk/visibility {:result :hide}}
(import java.util.Calendar)
(doto (Calendar/getInstance)
  (.set 1989 2 13 0 0 0)
  .getTime)

;; Metode-kall funker også:
(.toUpperCase "Fredrik")

(.-x (java.awt.Point. 1 2))

(System/getProperty "java.vm.version")

;; osv osv... Jeg har tross alt bare 15 min!


^{::clerk/visibility {:result :hide :code :hide}}
(comment
  ;; Evaluer denne for å starte Clerk
  ;; I Emacs: C-x C-e
    (clerk/serve! {:browse true}))

