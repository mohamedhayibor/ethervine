(ns ethervine.events
  (:require [re-frame.core :as rf]
            [ethervine.db :as db]
            [district0x.re-frame.web3-fx]
            [cljs-web3.personal :as web3-personal]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx]))

(def interceptors [rf/trim-v])

(rf/reg-event-fx
 :initialize

 (fn  [_ _]
   {:db db/default-db}))


(rf/reg-event-db
  :blockchain/unlock-account
  interceptors
  (fn [{:keys [db]} [address password]]
    (println "db: " db)
    (println "address: " address)
    (println "password: " password)

    (web3-personal/unlock-account address password)

    {:web3-fx.blockchain/fns
     {:web3 (:web3 db/default-db)
      :fns  [[web3-personal/unlock-account address password 9999999
              :blockchain/account-unlocked
              :log-error]]}}

    (println "exited from web3-call")))

(rf/reg-event-db
  :blockchain/account-unlocked
  interceptors
  (fn [{:keys [db]}]
    (println "account unlocked")
    (println  "db" db)))

(rf/reg-event-db
  :log-error
  (fn [{:keys [db]}]
    (println "ULTIMATE FAIL")))


