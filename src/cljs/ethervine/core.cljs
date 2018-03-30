(ns ethervine.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [ethervine.events :as events]
            [ethervine.views :as views]
            [ethervine.config :as config]
            [ethervine.utils :as utils]
            [ethervine.db :as db]
            [ethervine.subs :as subs]


            [ajax.core :as ajax]
            [day8.re-frame.http-fx]

            [cljsjs.web3]
            [cljs-web3.core :as web3]
            [cljs-web3.bzz :as web3-bzz]
            [cljs-web3.db :as web3-db]
            [cljs-web3.eth :as web3-eth]
            [cljs-web3.evm :as web3-evm]
            [cljs-web3.net :as web3-net]
            [cljs-web3.personal :as web3-personal]
            [cljs-web3.settings :as web3-settings]
            [cljs-web3.shh :as web3-shh]

            [district0x.re-frame.web3-fx]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch-sync [:initialize])
  (dev-setup)
  (mount-root))

(comment
  (init)
  (rf/dispatch [:initialize]))
