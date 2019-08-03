(ns y-minutes.core
  (:gen-class))
(str "hello" " " "world")
(+ 1 2) ; 3
(* 2 5) ; 10
(= 1 1) ; true
(= 1 1.0) ; false
(not false) ; true
(- 1 (* 5 7)) ; -34
(class 1) ; java.lang.Long
(type 1.) ; java.lang.Double
(class [1 2 3]) ; clojure.lang.PersistentVector
(class '(1 2 3)) ; clojure.lang.PersistentList
(= (list 1 2 3) '(1 2 3)) ; true
(coll? '(1 2 3)) ; true
(coll? 1) ; false
(coll? [1 2 3]) ; true
(seq? '(1 2 3)) ; true
(seq? [1 2 3]) ; false
(range 4) ; '(0 1 2 3)
; (range) generates infinite list
(take 3 (range));
(cons 1 '(2 3)); '(1 2 3)
(cons 1 [2 3]); '(1 2 3)
; conj will add element in most effecient way
; for list it will insert in the beginning
(conj '(1 2 3) 4) ; '(4 1 2 3)
; for vector it will append
(conj [1 2 3] 4) ; [1 2 3 4]
(concat [1 2] '(3 4)) ; '(1 2 3 4)
(concat [1 2] [3 4]) ; '(1 2 3 4)
(inc 1) ; 2
(identity 1) ; 1
(map inc (take 3 (range))) ; '(1 2 3)
(filter even? (range 5)); '(0 2 4)
(reduce + (range 101)); 5050
(reduce + 100 (range 11)); 155, like folding
((fn [] "hello world")) ; applying function to "hello world"
(def x 100) ; defining a variable
(def hello-fn (fn [] "hello world"))
(hello-fn) ; "hello world"
(defn hello-fn2 [] "hello world")
(hello-fn2) ; "hello world"
(defn hello-var [name]
  (str "Hello " name))
(hello-var "shuvo") ; "hello shuvo"
(def hello2 #(str "Hello " %1))
(hello2 "arshi") ; Hello arshi
(defn hello3
  ([] "Hello Random guy")
  ([name] (str "Hello " name))
  ([first second] (str "Hello " first second)))
(hello3) ; "Hello Random guy"
(hello3 "emon") ; "Hello emon"
(hello3 "s " "madhurjo") ; "Hello s madhurjo"
(defn factorial [n]
  (if (= n 0)
    1
    (* (factorial (dec n)) n)))
(factorial 5)
(defn count-args [& args]
  (str "You passed " (count args) " args : " args))
(count-args 1 2 3 4) ; "You passes 4 args : 1 2 3 4"
(defn hello-count [name & args]
  (str "Hello " name ", you passed " (count args) " args " args))
(hello-count "s" 1 1.0 \d 10/14); "Hello s, you passed 4 args (1 1.0 \\d 5/7)"
(class {:a 1 :b 2 :c 3}); PersistentArrayMap
(class (hash-map :a 1 :b 2 :c 3)); PersistentHashMap
; HashMaps have faster lookup
; ArrayMaps can retain order
; Maps can use any Hashable types as Keys, but Keywords are better
(class :d); Keyword
(class 'd); Symbol
(def stringmap {"a" 1 "b" 10.3 "c" "another"})
stringmap
(stringmap "a")
(stringmap "no"); nil
; commas are always treated as whitespace and don't do anything
(def keymap {:a 1, :b 2, :c 10})
keymap
(keymap :c); 10
(:c keymap); 10
; this will not work with String
; ("a" stringmap) java.lang.String cannot be cast to clojure.lang.IFn(
(def newmap (assoc stringmap "d" "abc"))
newmap; adds d with stringmap
stringmap ; is unchanged
(dissoc keymap :a :b); {:c 10}
keymap; is unchanged
(class #{1 2 3}); PersistentHashSet
(def set1 #{1 2 3 4}); (def set1 #{1 2 3 4 3}) will throw exception for duplicated element
(def set2 (set [1 2 3 4 3 2 1]))
(= set1 set2); true
(= set1 (conj set1 5)); false
(not= set1 (disj set2 3)); true
(set1 1); 1
(set1 10); nil
(if true 10 -10); 10
(if false 19 -91); -91
(let [a 1 b 2]
  (> a b)); false
; (let (a 1 b 2) (< a b)); syntax error
(do 
  (println "hello world")
  100) ; 100
(defn id [p]
  (do
    (println "nothing")
    p))
(id 100); 100
(id "string"); "string"
; Functions have an implicit do
(defn dofactorial [number]
  (println "implicit do")
  (factorial number))
(dofactorial 6); 720
(defn inc-then-factorial [num]
  (def next (inc num))
  (factorial next))
(inc-then-factorial 6); 5040
; The "Thread-first" macro (->) inserts into each form the result of
; the previous, as the first argument (second item)
(-> 
    {:a 1 :b 2}
    (assoc :c 3)
    (dissoc :b)); {:a 1 :c 3}
; This expression could be written as:
; (dissoc (assoc {:a 1 :b 2} :c 3) :b)
; The double arrow does the same thing, but inserts the result of
; each line at the *end* of the form. This is useful for collection
; operations in particular:
(->>
     (range 10)
     (map inc)
     (filter odd?)
     (into [])); [1 3 5 7 9]
; When you are in a situation where you want more freedom as where to
; put the result of previous data transformations in an 
; expression, you can use the as-> macro. With it, you can assign a
; specific name to transformations' output and use it as a
; placeholder in your chained expressions:
(as-> 
      [1 2 3] input
      (map (fn [i] (* i 10)) input) ; '(10 20 30)
      (nth input 2); input is '(10 20 30); result is 30
      (conj [45] input 7)); input is 30; result is [45 30 7] 
;; Modules
; Use "use" to get all functions from the module
(use 'clojure.set)
; You can choose a subset of functions to import, too
; (use '[clojure.set :only [intersection]])
(intersection #{1 2 3} #{2 3 4}); #{3 2}
(difference #{1 2 3} #{3 4 5}); #{1 2}
(require 'clojure.string)
; Use / to call functions from a module
(clojure.string/blank? ""); true
(require '[clojure.string :as str])
(str/replace "this is a test." #"[a-o]" str/upper-case); tHIs Is A tEst
; You can use require (and use, but don't) from a namespace using :require.
; You don't need to quote your modules if you do it this way.
(ns test
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))
; (import java.util.Date)
(ns test
  (:import java.util.Date
           java.util.Calendar))
(Date.)
(. (Date.) getTime)
(.getTime (Date.))
(System/currentTimeMillis)
; Use doto to make dealing with (mutable) classes more tolerable
(doto (Calendar/getInstance)
  (.set 2000 1 1 0 0 0)
  .getTime); => A Date. set to 2000-01-01 00:00:00
; Software Transactional Memory is the mechanism clojure uses to handle
; persistent state. There are a few constructs in clojure that use this.
(def my-atom (atom {}))
; Update an atom with swap!.
; swap! takes a function and calls it with the current value of the atom
; as the first argument, and any trailing arguments as the second
(swap! my-atom assoc :a 1)
(swap! my-atom assoc :b 2)
(swap! my-atom dissoc :a)
my-atom
@my-atom ; {:b 2}
(def counter (atom 0))
(defn inc-counter []
  (swap! counter inc))
; everytime we call inc-counter, counter = inc(counter) happens
(inc-counter); returns 1
(inc-counter)
(inc-counter)
(inc-counter)
(inc-counter)
@counter ; 5

;(defn -main []
;  (println "Hello, World!"))
