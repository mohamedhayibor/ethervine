(ns ethervine.events
  (:require [re-frame.core :as rf]
            [ethervine.db :as init-db]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [cljsjs.web3]
            [cljs-web3.core :as web3]
            [cljs-web3.eth :as web3-eth]

            [cljs-web3.personal :as web3-personal]
            [day8.re-frame.http-fx]
            [district0x.re-frame.web3-fx]))

(def web3 (web3/create-web3 "http://localhost:6777"))

(def interceptors [rf/trim-v])

(rf/reg-event-fx
 :initialize

 (fn  [_ _]
   {:db init-db/default-db}))


(rf/reg-event-db
  :blockchain/unlock-account
  interceptors
  (fn [{:keys [db]} [address password]]
    (println "db: " db)
    ; (println "init-db: " init-db/default-db)
    ; (println "init-db/web3 " (:web3 init-db/default-db))
    (println "address: " address)
    (println "password: " password)
    (println "web3: " web3)


    {:web3/call {:web3 web3
                 :fns  [{:fn web3-personal/unlock-account
                         :args [address password 99999]
                         :on-success [:blockchain/account-unlocked]
                         :on-error [:log-error]}]}}

    (println "Exited from web3-call")))

(rf/reg-event-db
  :blockchain/account-unlocked
  interceptors
  (fn [{:keys [db]}]
    (println "SUCCESS: account unlocked >>>>")
    (println  "DB: " db)))

(rf/reg-event-db
  :log-error
  interceptors
  (fn [{:keys [db]}]
    (println "ULTIMATE FAIL: unlock account unsuccessful")
    (println "DB: " db)))

(comment
  (rf/dispatch [:initialize])
  (rf/dispatch [:blockchain/unlock-account "0xd1c1db1ac98ae37005bf0752ed3458e418baa7f9" "try" 999999]))
