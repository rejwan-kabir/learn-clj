;; All Clojure code is written in a uniform structure. Clojure recognizes two kinds of structures:    
;; Literal representations of data structures (like numbers, strings, maps, and vectors)
1 ; Long
1. ; Double
22/7 ; Ratio
"string" ; String
;; Operations
(hash-set 1 2 3)
(sorted-set 5 3 1)
(+ 1 2 3)
;; control flow
(if true "true" -100)
(if false "true" -100)
(if (= 1 2) 100); nil

(if (even? (rand-int 10))
  (do (println "number was even")
    2)
  (do (println "number was odd")
    1))
;; when is combination of if and do
;; if the condition evaluates to true
;; nil otherwise
(when true
  (println "inside when")
  1001)
(when false
  (println "inside when")
  1001)
;; false and nil represent falsity
;; everything else is true
;; if is a general structure
(if nil "true" "false") ; false
(if 100 "true" "false") ; true
(nil? 1); false
(nil? nil); true
;; or returns the first true false or the last false value
(or nil 100 false) ; 100
(or false nil false) ; false
;; and return the first false value or the last true value
(and 100 200 false 500); false
(and 1 2 3 17); 17
;; defining variables
(def abc 100)
;; Data Structures
[1 2 3] ; Vector
'(1 2 3) ; List
#{1 2 3} ; Set
{:first-name "rejwan shamsul" :last-name "kabir"}; Map
(hash-map :add + :subtract - :division / :multiplication *)
;; access hap
(get {:a 1 :b 2} :a)
(get {:a 1 :b 2} :c); nil
(get {:a 1 :b 2} :c "default"); "default"
(:a {:a 1 :c 4}); 1
(:none {:a 1} "default"); "default"
({:a 1 :b 3} :b); 3
(get-in {:a 1 :b {:c "nested"}} [:b :c]); "nested"
;; Vector is better for indexed access
;; List is better for head::tail access
(get [1 2 3] 1); 2
(nth '(1 2 3) 1); 2
(= (vector 1 2 3) [1 2 3])
(= (vector 1 2 3) '(1 2 3))
(= (list 1 2 3) [1 2 3])
(= (list 1 2 3) '(1 2 3))
(conj [1 2 3] 4) ; [1 2 3 4]
(conj '(1 2 3) 4) ; '(4 1 2 3)
(:a #{:a :b :c}); :a
(:d #{:a :b :c}); nil
(set [1 2 3 4 3 2 1]); #{1 2 3 4}
(conj #{1 2 3} 3)
(defn list-to-set [ls]
  (reduce conj #{} ls))
(list-to-set '(1 2 3 4 3 2 1)); #{1 2 3 4}
(contains? #{1 2 3} 3); true
(contains? #{nil :a :b} nil); true
((or + -) 1 2 3); 6
;; desctructuring a list
(defn list-first [[first]]
  first)
(list-first '(1 2 3)); 1
(map inc [1 2 3]); [2 3 4]
(defn tail-call-factorial 
  ([num] 
   (tail-call-factorial num 1))
  ([num acc] 
   (if (zero? num)
     acc
     (tail-call-factorial (dec num) (* acc num)))))
(tail-call-factorial 0)
(tail-call-factorial 5)
(defn destructure-maps
  [{a :a b :b}]
  (str "a : " a ", b = " b))
(destructure-maps {:a 10 :b 15})
(defn destructure-maps
  [{:keys [a b] :as param-map}]
  (println a " , " b)
  (count param-map))
(destructure-maps {:a 100 :b -100 :c 0}); 3
(map (fn [a] (* a 2)) [1 2 3]); '(2 4 6)
(map #(* % 2) [1 2 3]); shortcut
(reduce #(+ %1 %2) (range 101)); %1 %2 for accessing variable
(defn inc-maker
  "increment by what we want"
  [inc-by]
  #(+ % inc-by))
((inc-maker 5) 15); 20
(defn cmpt
  [i]
  (let [i (+ i 1)
        j (* i 20)]
    (- j i)))
(cmpt 20); 20 * 21 - 21 = 399

(def res (let [a 3]
           (let [a (+ a 3)]
             a))) ; 6
(let [res (* res 10)]
  res); 60
res; 6
(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians]); ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
;; loop
(loop [iteration 0]
  (println (str "Iteration : " iteration))
  (if (> iteration 3)
    (println "Goodbye!!!")
    (recur (inc iteration))))
;; Iteration : 0
;; Iteration : 1
;; Iteration : 2
;; Iteration : 3
;; Iteration : 4
;; Goodbye!!!

;; Regular Expression
(re-find #"^left-" "left-eye"); "left-"
(re-find #"^eye" "left-eye"); nil
(re-find #"^right" "left-eye"); nil

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(matching-part {:name "left-eye" :size 1}); {:name "right-eye" :size 1}]
(matching-part {:name "head" :size 3}); {:name "head" :size 3}]

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining (into final-body-parts (set [part (matching-part part)])))))))
(symmetrize-body-parts asym-hobbit-body-parts)

(reduce + [1 2 3 4]); 10
(reduce #(+ %1 %2) 0 [1 2 3 4 5]); 15

(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts
              (set [part (matching-part part)])))
    []
    asym-body-parts))
(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand-int body-part-size-sum)]
    (loop [
           [part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)

(hit asym-hobbit-body-parts)
           
      
    
    
    
    























