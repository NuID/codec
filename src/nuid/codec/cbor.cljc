(ns nuid.codec.cbor
  #?@(:clj
      [(:require [clj-cbor.tags.clojure :as cbor.tags])]))

(def tagged-literal-read-handlers
  (atom {}))

(defn register-tagged-literal-read-handler!
  [{:as handler}]
  (swap! tagged-literal-read-handlers merge handler))

(defn- dispatch-tagged-literal-to-read-handler
  [tagged]
  (if-let [handler (@tagged-literal-read-handlers (:tag tagged))]
    (handler (:form tagged))
    tagged))

#?(:clj
   (def tagged-literal-read-handler
     {cbor.tags/generic-object-tag
      (comp dispatch-tagged-literal-to-read-handler
            cbor.tags/parse-tagged-literal)}))
