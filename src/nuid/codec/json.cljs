(ns nuid.codec.json
  (:require
   [nuid.codec.proto :as proto]))

(defmethod proto/encode "application/json"
  ([_ data]      (js/JSON.stringify (clj->js data)))
  ([_ opts data] (js/JSON.stringify (clj->js data)
                                    (:replacer opts)
                                    (:space opts))))

(defmethod proto/decode "application/json"
  ([_ json]      (js->clj (js/JSON.parse json) :keywordize-keys true))
  ([_ opts json] (js->clj (js/JSON.parse json (:reviver opts)) :keywordize-keys true)))
