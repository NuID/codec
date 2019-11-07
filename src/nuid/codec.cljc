(ns nuid.codec
  (:require
   [nuid.transit :as transit]
   [nuid.base64 :as base64]
   #?@(:clj
       [[clojure.data.json :as json]
        [clj-cbor.core :as cbor]])))

(defmulti encode
  (fn
    ([content-type _]   content-type)
    ([content-type _ _] content-type)))

(defmethod encode "application/transit+json"
  ([_ data]      (transit/write data))
  ([_ opts data] (transit/write opts data)))

#?(:clj
   (defmethod encode "application/json"
     ([_ data]      (json/write-str data))
     ([_ opts data] (apply json/write-str
                           data
                           (interleave
                            (keys opts)
                            (vals opts))))))

#?(:cljs
   (defmethod encode "application/json"
     ([_ data]      (js/JSON.stringify (clj->js data)))
     ([_ opts data] (js/JSON.stringify (clj->js data)
                                       (:replacer opts)
                                       (:space opts)))))

#?(:clj
   (defmethod encode "application/cbor"
     ([_ data]      (cbor/encode data))
     ([_ opts data] (cbor/encode opts data))))

(defmulti decode
  (fn
    ([content-type _]   content-type)
    ([content-type _ _] content-type)))

(defmethod decode "application/transit+json"
  ([_ transit]      (transit/read transit))
  ([_ opts transit] (transit/read opts transit)))

#?(:clj
   (defmethod decode "application/json"
     ([_ json]      (json/read-str json :key-fn keyword))
     ([_ opts json] (apply json/read-str
                           json
                           (interleave
                            (keys opts)
                            (vals opts))))))

#?(:cljs
   (defmethod decode "application/json"
     ([_ json]      (js->clj (js/JSON.parse json)))
     ([_ opts json] (js->clj (js/JSON.parse json (:reviver opts))))))

#?(:clj
   (defmethod decode "application/cbor"
     ([_ data]      (cbor/decode data))
     ([_ opts data] (cbor/decode opts data))))

#?(:cljs (def exports #js {}))
