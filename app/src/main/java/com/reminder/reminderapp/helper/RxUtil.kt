package com.reminder.reminderapp.helper

import rx.Subscription

class RxUtil {
    fun unSubscribe(subscription: Subscription?) {
        if (subscription != null && !subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }
}