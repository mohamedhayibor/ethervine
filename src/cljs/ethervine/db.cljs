(ns ethervine.db
  (:require [cljs-web3.core :as web3]))

(def default-db
  {:name "ethervine-db"
   :web3 (or (aget js/window "web3")
             ;; only on goog.DEBUG
             (web3/create-web3 "http://localhost:8545"))})
