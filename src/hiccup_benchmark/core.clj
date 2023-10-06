(ns hiccup-benchmark.core
  (:require hiccup2.core))

(defn random-string
  []
  (->> #(char (+ 65 (rand-int 25)))
       (repeatedly)
       (take 100)
       (apply str)))

(defn random-hashmap
  []
  (->> #(vector (keyword (random-string)) (random-string))
       (repeatedly)
       (take 8)
       (into {})))


(defn build-element-tag
  [options]
  (cond
    (and (options :tag-id)
         (options :tag-class))
    (keyword
      (str (random-string)
           "#" (random-string)
           "." (random-string)
           "." (random-string)
           "." (random-string)))

    (options :tag-class)
    (keyword
      (str (random-string)
           "." (random-string)
           "." (random-string)
           "." (random-string)))

    (options :tag-id)
    (keyword (str (random-string) "#" (random-string)))


    :else (keyword (random-string))))

(defn build-element-attributes
  [options]
  (when (or (options :attrs-id)
            (options :attrs-class)
            (options :attrs-styles))
    (merge
      (random-hashmap)
      (when (options :attrs-id)
        {:id (random-string)})
      (when (options :attrs-class)
        {:class (str (random-string)
                     "." (random-string)
                     "." (random-string))})
      (when (options :attrs-styles)
        {:styles (random-hashmap)}))))

(declare build-element)

(defn build-element-children
  [options]
  (some->
    (options :children)
    (repeat
     (if (some-> options :depth pos?)
       (apply build-element
              (cons (update (select-keys options [:depth :children]) :depth dec)
                    (keys (dissoc options :children :depth))))
       (random-string)))))

(defn build-element
  [& parameters]
  (let [options    (into {} (map #(if (map? %) % (hash-map % true)) parameters))
        tag        (build-element-tag options)
        attributes (build-element-attributes options)
        children   (build-element-children options)]
    (cond-> [tag]
      attributes (conj attributes)
      children   (into children))))

(defn html
  [hiccup]
  (hiccup2.core/html hiccup))

(comment
  (require 'clj-async-profiler.core)

  (def hiccup
    (build-element
      :tag-id
      :tag-class
      :attrs-id
      :attrs-class
      :attrs-styles
      {:children 1
       :depth    2}))

  (clj-async-profiler.core/serve-ui 8888)

  (clj-async-profiler.core/profile
    (dotimes [_ 100000]
      (hiccup2.core/html hiccup)))

  (clj-async-profiler.core/generate-diffgraph
    "/tmp/clj-async-profiler/results/"
    "/tmp/clj-async-profiler/results/"
    {})

  )
