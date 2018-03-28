(ns ethervine.db
  (:require [cljs-web3.core :as web3]))

(def default-db
  {:name "Ethervine-db >>>>>>> Connected"
   :web3 (or (aget js/window "cljs_web3")
             ;; only if goog.DEBUG
             (web3/create-web3 "http://localhost:8545"))})
             ;; (web3/create-web3 "https://morden.infura.io/metamask"))})


