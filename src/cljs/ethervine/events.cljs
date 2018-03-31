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
            [district0x.re-frame.web3-fx]
            [taoensso.timbre :as timbre
             :refer-macros [info]]))

(def web3-event-scream "Firing event to web3")
(def web3 (web3/create-web3 "http://localhost:6777"))

(def interceptors [rf/trim-v])

(rf/reg-event-fx
 :initialize

 (fn  [_ _]
   (info "Initialized")
   {:db init-db/default-db}))


(rf/reg-event-db
  :blockchain/unlock-account
  interceptors
  (fn [{:keys [db] :as args} [address password]]
    (info web3-event-scream {:args args})
    ; (println "init-db: " init-db/default-db)
    ; (println "init-db/web3 " (:web3 init-db/default-db))
    (info "address: " address)
    (info "password: " password)
    (info "web3: " web3)


    {:web3/call {:web3 (:web3 init-db/default-db)
                 :fns  [{:fn web3-personal/unlock-account
                         :args [address password 99999]
                         :on-success [:blockchain/account-unlocked]
                         :on-error [:log-error]}]}}

    (info "Exited from web3-call")))

(rf/reg-event-db
  :blockchain/account-unlocked
  interceptors
  (fn [{:keys [db]}]
    (info "SUCCESS: account unlocked >>>>")
    (info  "DB: " db)))

(rf/reg-event-db
  :log-error
  interceptors
  (fn [{:keys [db]}]
    (info "ULTIMATE FAIL: unlock account unsuccessful")
    (info "DB: " db)))

(comment
  (rf/dispatch [:initialize])
  (rf/dispatch [:blockchain/unlock-account "0xd1c1db1ac98ae37005bf0752ed3458e418baa7f9" "try" 999999]))
